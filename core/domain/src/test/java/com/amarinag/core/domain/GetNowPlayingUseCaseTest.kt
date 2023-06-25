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


class GetNowPlayingUseCaseTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var movieRepository: MovieRepository

    private lateinit var getNowPlayingUseCase: GetNowPlayingUseCase

    @Before
    fun setup() {
        getNowPlayingUseCase = GetNowPlayingUseCase(movieRepository)
    }

    @Test
    fun `verify returned movie as favorite success`() = runTest {
        givenMovieSuccess()
        givenFavoritesMoviesSuccess()
        val expected = getNowPlayingUseCase().first()
        coVerify(exactly = 1) { movieRepository.getFavoriteMovies() }
        coVerify(exactly = 1) { movieRepository.getPlayNowMovies() }

        assertThat(expected).hasSize(moviesTestData.size)
        assertThat(expected.first().movie).isEqualTo(ANY_MOVIES.first())
        assertThat(expected.first().isFavorite).isEqualTo(true)
    }

    @Test
    fun `verify returned movie without favorites success`() = runTest {
        givenMovieSuccess()
        givenFavoritesMoviesSuccess(0)
        val expected = getNowPlayingUseCase().first()

        coVerify(exactly = 1) { movieRepository.getFavoriteMovies() }
        coVerify(exactly = 1) { movieRepository.getPlayNowMovies() }
        assertThat(expected).hasSize(moviesTestData.size)
        assertThat(expected.first().movie).isEqualTo(ANY_MOVIES.first())
        assertThat(expected.first().isFavorite).isEqualTo(false)

    }

    @Test
    fun `favorites movies throws error`() = runTest {
        givenMovieSuccess()
        givenFavoritesMoviesError()
        try {
            getNowPlayingUseCase().first()
        } catch (ex: Exception) {
            assertThat(ex.localizedMessage).isEqualTo(ANY_ERROR_MESSAGE)
        }
        coVerify(exactly = 1) { movieRepository.getFavoriteMovies() }
        coVerify(exactly = 1) { movieRepository.getPlayNowMovies() }
    }

    @Test
    fun `getById movie throws error`() = runTest {
        givenMovieError()
        givenFavoritesMoviesError()
        try {
            getNowPlayingUseCase().first()
        } catch (ex: Exception) {
            assertThat(ex.localizedMessage).isEqualTo(ANY_ERROR_MESSAGE)
        }
        coVerify(exactly = 1) { movieRepository.getPlayNowMovies() }
        coVerify(exactly = 0) { movieRepository.getFavoriteMovies() }
    }

    private fun givenMovieSuccess() {
        coEvery { movieRepository.getPlayNowMovies() } returns flowOf(ANY_MOVIES)
    }

    private fun givenFavoritesMoviesSuccess(movieCount: Int = moviesTestData.size) {
        coEvery { movieRepository.getFavoriteMovies() } returns flowOf(
            moviesTestData.take(movieCount)
        )
    }

    private fun givenMovieError() {
        coEvery { movieRepository.getPlayNowMovies() } throws ANY_ERROR
    }

    private fun givenFavoritesMoviesError() {
        coEvery { movieRepository.getFavoriteMovies() } throws ANY_ERROR
    }

    private companion object {
        const val ANY_ERROR_MESSAGE = "ANY_ERROR_MESSAGE"
        val ANY_ERROR = RuntimeException(ANY_ERROR_MESSAGE)
        val ANY_MOVIES = moviesTestData
    }
}