package com.amarinag.feature.nowplaying.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.amarinag.feature.nowplaying.NowPlayingRoute

const val nowPlayingRoute = "now_playing_route"

fun NavController.navigateToNowPlaying(navOptions: NavOptions? = null) {
    navigate(nowPlayingRoute, navOptions)
}

fun NavGraphBuilder.nowPlayingScreen() {
    composable(nowPlayingRoute) {
        NowPlayingRoute()
    }
}