package com.amarinag.movieexplorer.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.amarinag.movieexplorer.navigation.MovieNavHost

@Composable
fun MovieApp(windowSizeClass: WindowSizeClass) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Column(Modifier.padding(padding)) {
            MovieNavHost(windowSizeClass = windowSizeClass, navController = navController)
        }
    }
}