package com.amarinag.feature.movie

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.amarinag.core.domain.AddFavoriteMoviesUseCase
import com.amarinag.core.domain.DeleteFavoriteMoviesUseCase
import com.amarinag.core.domain.GetMovieByIdUseCase
import com.amarinag.core.model.UserMovie
import com.amarinag.core.testing.data.moviesTestData
import com.amarinag.core.testing.util.MainDispatcherRule
import com.amarinag.feature.movie.navigation.movieIdArg
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class MovieViewModelTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var getMovieByIdUseCase: GetMovieByIdUseCase

    @MockK
    private lateinit var addFavoriteMoviesUseCase: AddFavoriteMoviesUseCase

    @MockK
    private lateinit var deleteFavoriteMoviesUseCase: DeleteFavoriteMoviesUseCase

    @MockK
    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var viewModel: MovieViewModel

    @Test
    fun `movieUiState emit correct loading and success states`() = runTest {
        initViewModel {
            givenSavedStateHandleSuccess()
            givenFavoriteUserMovieSuccess()
        }
        assertThat(viewModel.movieUiState.value).isInstanceOf(MovieUiState.Loading::class.java)
        viewModel.movieUiState.test {
            val result = awaitItem()
            assertThat(result).isInstanceOf(MovieUiState.Success::class.java)
            assertThat((result as MovieUiState.Success).movie).isInstanceOf(UserMovie::class.java)
            assertThat(result.movie.movie).isEqualTo(ANY_MOVIE)
            assertThat(result.movie.isFavorite).isEqualTo(true)
        }
    }

    @Test
    fun `movieUiState Fail`() = runTest {
        initViewModel({
            assertThat(it).isInstanceOf(RuntimeException::class.java)
        }) {
            givenSavedStateHandleSuccess()
            givenFavoriteUserMovieFailure()
        }
    }

    private inline fun initViewModel(catchBlock: ((Throwable) -> Unit) = {}, block: () -> Unit) {
        block()
        try {
            viewModel = MovieViewModel(
                getMovieByIdUseCase = getMovieByIdUseCase,
                savedStateHandle = savedStateHandle,
                addFavoriteMoviesUseCase = addFavoriteMoviesUseCase,
                deleteFavoriteMoviesUseCase = deleteFavoriteMoviesUseCase
            )
        } catch (ex: Exception) {
            catchBlock.invoke(ex)
        }
    }

    private fun givenSavedStateHandleSuccess() {
        every { savedStateHandle.get<Int>(movieIdArg) } returns ANY_MOVIE_ID
    }

    private fun givenFavoriteUserMovieSuccess() {
        every { getMovieByIdUseCase(ANY_GET_MOVIE_ID_PARAMS) } returns flowOf(
            ANY_USER_MOVIE_FAVORITE
        )
    }

    private fun givenFavoriteUserMovieFailure() {
        every { getMovieByIdUseCase(ANY_GET_MOVIE_ID_PARAMS) } throws RuntimeException()
    }

    private fun givenNonFavoriteUserMovie() {
        every { getMovieByIdUseCase(ANY_GET_MOVIE_ID_PARAMS) } returns flowOf(
            ANY_USER_MOVIE_NON_FAVORITE
        )
    }

    private companion object {
        val ANY_MOVIE = moviesTestData.first()
        val ANY_MOVIE_ID = ANY_MOVIE.id
        val ANY_USER_MOVIE_FAVORITE = UserMovie(movie = ANY_MOVIE, isFavorite = true)
        val ANY_USER_MOVIE_NON_FAVORITE = UserMovie(movie = ANY_MOVIE, isFavorite = false)
        val ANY_GET_MOVIE_ID_PARAMS = GetMovieByIdUseCase.Params(ANY_MOVIE_ID)
    }
}