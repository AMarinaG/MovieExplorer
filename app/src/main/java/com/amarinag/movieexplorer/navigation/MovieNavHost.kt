package com.amarinag.movieexplorer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.amarinag.feature.favorite.navigation.favoriteRoute
import com.amarinag.feature.favorite.navigation.favoriteScreen

@Composable
fun MovieNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = favoriteRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        favoriteScreen()
    }
}