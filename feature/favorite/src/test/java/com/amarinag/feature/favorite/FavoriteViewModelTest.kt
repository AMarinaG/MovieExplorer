package com.amarinag.feature.favorite

import app.cash.turbine.test
import com.amarinag.core.domain.DeleteFavoriteMoviesUseCase
import com.amarinag.core.domain.GetFavoriteMoviesUseCase
import com.amarinag.core.testing.data.moviesTestData
import com.amarinag.core.testing.util.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test


class FavoriteViewModelTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase

    @MockK
    private lateinit var deleteFavoriteMoviesUseCase: DeleteFavoriteMoviesUseCase

    private lateinit var viewModel: FavoriteViewModel


    @Test
    fun `favoriteUiState_whenInitialized_thenShowLoading`() = runTest {
        assertThat(viewModel.favoriteUiState.value).isInstanceOf(FavoriteUiState.Loading::class.java)
    }

    @Test
    fun `favoriteUiState_whenSuccess_thenResultSuccessMovies`() = runTest {
        initViewModel { givenGetFavoriteSuccess() }
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.favoriteUiState.collect()
        }
        viewModel.favoriteUiState.test {
            val result = awaitItem()
            assertThat(result).isInstanceOf(FavoriteUiState.Success::class.java)
            assertThat((result as FavoriteUiState.Success).movies).isEqualTo(moviesTestData)
        }
    }

    @Test
    fun `favoriteUiState_whenError_thenResultSuccessMovies`() = runTest {
        try {
            initViewModel { givenGetFavoriteFailure() }
        } catch (ex: Exception) {
            assertThat(ex).isInstanceOf(RuntimeException::class.java)
        }
    }

    private fun initViewModel(block: () -> Unit) {
        block()
        viewModel = FavoriteViewModel(
            getFavoriteMoviesUseCase = getFavoriteMoviesUseCase,
            deleteFavoriteMoviesUseCase = deleteFavoriteMoviesUseCase
        )
    }

    private fun givenGetFavoriteSuccess() {
        coEvery { getFavoriteMoviesUseCase.invoke() } returns flowOf(moviesTestData)
    }

    private fun givenGetFavoriteFailure() {
        coEvery { getFavoriteMoviesUseCase.invoke() } throws RuntimeException("pum")
    }

}