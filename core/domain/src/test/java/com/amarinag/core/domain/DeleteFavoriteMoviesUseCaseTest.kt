package com.amarinag.core.domain

import com.amarinag.core.data.repository.MovieRepository
import com.amarinag.core.testing.data.moviesTestData
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DeleteFavoriteMoviesUseCaseTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var movieRepository: MovieRepository

    private lateinit var deleteFavoriteMoviesUseCase: DeleteFavoriteMoviesUseCase

    @Before
    fun setUp() {
        deleteFavoriteMoviesUseCase = DeleteFavoriteMoviesUseCase(movieRepository)
    }

    @Test
    fun `deleteFavorite happy path`() = runTest {
        givenDeleteFavoriteSuccess()

        val expected = deleteFavoriteMoviesUseCase(ANY_PARAMS)
        coVerify(exactly = 1) { movieRepository.deleteFavorite(ANY_MOVIE) }
        Truth.assertThat(expected).isEqualTo(Unit)
    }

    @Test
    fun `deleteFavorite throws error`() = runTest {
        givenDeleteFavoriteError()
        try {
            deleteFavoriteMoviesUseCase(ANY_PARAMS)
        } catch (ex: Exception) {
            Truth.assertThat(ex.localizedMessage).isEqualTo(ANY_ERROR_MESSAGE)
        }
    }

    private fun givenDeleteFavoriteSuccess() {
        coEvery { movieRepository.deleteFavorite(any()) } returns Unit
    }

    private fun givenDeleteFavoriteError() {
        coEvery { movieRepository.deleteFavorite(any()) } throws ANY_ERROR
    }

    private companion object {
        const val ANY_ERROR_MESSAGE = "ANY_ERROR_MESSAGE"
        val ANY_ERROR = RuntimeException(ANY_ERROR_MESSAGE)
        val ANY_MOVIE = moviesTestData.first()
        val ANY_PARAMS = DeleteFavoriteMoviesUseCase.Params(ANY_MOVIE)
    }
}