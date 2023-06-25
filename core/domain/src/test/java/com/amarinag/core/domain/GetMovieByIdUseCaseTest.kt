package com.amarinag.core.domain

import com.amarinag.core.data.repository.MovieRepository
import com.amarinag.core.testing.data.moviesTestData
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class GetMovieByIdUseCaseTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var movieRepository: MovieRepository

    private lateinit var getMovieByIdUseCase: GetMovieByIdUseCase

    @Before
    fun setup() {
        getMovieByIdUseCase = GetMovieByIdUseCase(movieRepository)
    }

    @Test
    fun `verify returned movie as favorite success`() = runTest {
        givenMovieSuccess()
        givenFavoritesMoviesSuccess()
        val expected = getMovieByIdUseCase(GetMovieByIdUseCase.Params(ANY_MOVIE_ID)).first()
        coVerify(exactly = 1) { movieRepository.getFavoriteMovies() }
        coVerify(exactly = 1) { movieRepository.getMovieById(any()) }

        assertThat(expected.movie).isEqualTo(ANY_MOVIE)
        assertThat(expected.isFavorite).isEqualTo(true)
    }

    @Test
    fun `verify returned movie without favorites success`() = runTest {
        givenMovieSuccess()
        givenFavoritesMoviesSuccess(0)
        val expected = getMovieByIdUseCase(GetMovieByIdUseCase.Params(ANY_MOVIE_ID)).first()

        coVerify(exactly = 1) { movieRepository.getFavoriteMovies() }
        coVerify(exactly = 1) { movieRepository.getMovieById(any()) }
        assertThat(expected.movie).isEqualTo(ANY_MOVIE)
        assertThat(expected.isFavorite).isEqualTo(false)

    }

    @Test
    fun `favorites movies throws error`() = runTest {
        givenMovieSuccess()
        givenFavoritesMoviesError()
        try {
            getMovieByIdUseCase(GetMovieByIdUseCase.Params(ANY_MOVIE_ID)).first()
        } catch (ex: Exception) {
            assertThat(ex.localizedMessage).isEqualTo(ANY_ERROR_MESSAGE)
        }
        coVerify(exactly = 1) { movieRepository.getFavoriteMovies() }
        coVerify(exactly = 1) { movieRepository.getMovieById(any()) }
    }

    @Test
    fun `getById movie throws error`() = runTest {
        givenMovieError()
        givenFavoritesMoviesError()
        try {
            getMovieByIdUseCase(GetMovieByIdUseCase.Params(ANY_MOVIE_ID)).first()
        } catch (ex: Exception) {
            assertThat(ex.localizedMessage).isEqualTo(ANY_ERROR_MESSAGE)
        }
        coVerify(exactly = 1) { movieRepository.getMovieById(any()) }
        coVerify(exactly = 0) { movieRepository.getFavoriteMovies() }
    }

    private fun givenMovieSuccess() {
        coEvery { movieRepository.getMovieById(ANY_MOVIE_ID) } returns flowOf(ANY_MOVIE)
    }

    private fun givenFavoritesMoviesSuccess(movieCount: Int = moviesTestData.size) {
        coEvery { movieRepository.getFavoriteMovies() } returns flowOf(
            moviesTestData.take(movieCount)
        )
    }

    private fun givenMovieError() {
        coEvery { movieRepository.getMovieById(ANY_MOVIE_ID) } throws ANY_ERROR
    }

    private fun givenFavoritesMoviesError() {
        coEvery { movieRepository.getFavoriteMovies() } throws ANY_ERROR
    }

    private companion object {
        const val ANY_MOVIE_ID = 1
        const val ANY_ERROR_MESSAGE = "ANY_ERROR_MESSAGE"
        val ANY_ERROR = RuntimeException(ANY_ERROR_MESSAGE)
        val ANY_MOVIE = moviesTestData.first()
    }
}