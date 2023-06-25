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


class GetFavoriteMoviesUseCaseTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var movieRepository: MovieRepository

    private lateinit var getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase

    @Before
    fun setUp() {
        getFavoriteMoviesUseCase = GetFavoriteMoviesUseCase(movieRepository)
    }


    @Test
    fun `get favorites happy path`() = runTest {
        givenAddFavoriteSuccess()

        val expected = getFavoriteMoviesUseCase().first()
        coVerify(exactly = 1) { movieRepository.getFavoriteMovies() }
        assertThat(expected).hasSize(moviesTestData.size)
    }

    @Test
    fun `get favorites throws error`() = runTest {
        givenAddFavoriteError()
        try {
            getFavoriteMoviesUseCase()
        } catch (ex: Exception) {
            assertThat(ex.localizedMessage).isEqualTo(ANY_ERROR_MESSAGE)
        }
    }

    private fun givenAddFavoriteSuccess() {
        coEvery { movieRepository.getFavoriteMovies() } returns flowOf(ANY_MOVIES)
    }

    private fun givenAddFavoriteError() {
        coEvery { movieRepository.getFavoriteMovies() } throws ANY_ERROR
    }

    private companion object {
        const val ANY_ERROR_MESSAGE = "ANY_ERROR_MESSAGE"
        val ANY_ERROR = RuntimeException(ANY_ERROR_MESSAGE)
        val ANY_MOVIES = moviesTestData
    }

}