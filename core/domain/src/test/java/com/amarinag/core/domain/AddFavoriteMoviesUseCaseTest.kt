package com.amarinag.core.domain

import com.amarinag.core.data.repository.MovieRepository
import com.amarinag.core.testing.data.moviesTestData
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class AddFavoriteMoviesUseCaseTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var movieRepository: MovieRepository

    private lateinit var addFavoriteMoviesUseCase: AddFavoriteMoviesUseCase

    @Before
    fun setUp() {
        addFavoriteMoviesUseCase = AddFavoriteMoviesUseCase(movieRepository)
    }

    @Test
    fun `addFavorite happy path`() = runTest {
        givenAddFavoriteSuccess()

        val expected = addFavoriteMoviesUseCase(ANY_PARAMS)
        coVerify(exactly = 1) { movieRepository.addFavorite(ANY_MOVIE) }
        assertThat(expected).isEqualTo(Unit)
    }

    @Test
    fun `addFavorite throws error`() = runTest {
        givenAddFavoriteError()
        try {
            addFavoriteMoviesUseCase(ANY_PARAMS)
        } catch (ex: Exception) {
            assertThat(ex.localizedMessage).isEqualTo(ANY_ERROR_MESSAGE)
        }
    }

    private fun givenAddFavoriteSuccess() {
        coEvery { movieRepository.addFavorite(any()) } returns Unit
    }

    private fun givenAddFavoriteError() {
        coEvery { movieRepository.addFavorite(any()) } throws ANY_ERROR
    }

    private companion object {
        const val ANY_ERROR_MESSAGE = "ANY_ERROR_MESSAGE"
        val ANY_ERROR = RuntimeException(ANY_ERROR_MESSAGE)
        val ANY_MOVIE = moviesTestData.first()
        val ANY_PARAMS = AddFavoriteMoviesUseCase.Params(ANY_MOVIE)
    }

}