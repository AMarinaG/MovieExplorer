package com.amarinag.feature.favorite

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.amarinag.core.testing.data.moviesTestData
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var emptyState: String

    @Before
    fun setup() {
        composeTestRule.activity.apply {
            emptyState = getString(R.string.no_favorite_movies)
        }
    }

    @Test
    fun loadingState_whenRequestIsLoading() {
        composeTestRule.setContent {
            BoxWithConstraints {
                FavoriteScreen(favoriteUiState = FavoriteUiState.Loading, onMovieClick = {})
            }
        }
        composeTestRule.onNodeWithText("Loading").assertIsDisplayed()
    }

    @Test
    fun showEmptyState_whenNoFavorites() {
        composeTestRule.setContent {
            BoxWithConstraints {
                FavoriteScreen(
                    favoriteUiState = FavoriteUiState.Success(emptyList()),
                    onMovieClick = {})
            }
        }
        composeTestRule.onNodeWithText(emptyState).assertExists()
    }

    @Test
    fun showMovieList_whenFavoritesReturned() {
        composeTestRule.setContent {
            BoxWithConstraints {
                FavoriteScreen(
                    favoriteUiState = FavoriteUiState.Success(movies = moviesTestData),
                    onMovieClick = {})
            }
        }
        moviesTestData.forEach { testMovie ->
            composeTestRule.onNodeWithText(testMovie.title)
                .assertExists()
        }
    }
}