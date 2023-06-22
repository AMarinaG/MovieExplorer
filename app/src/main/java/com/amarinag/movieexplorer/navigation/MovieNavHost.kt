package com.amarinag.movieexplorer.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amarinag.feature.favorite.FavoriteRoute

@Composable
fun MovieNavHost(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = "home"
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(startDestination) {
            FavoriteRoute()
        }
    }
}