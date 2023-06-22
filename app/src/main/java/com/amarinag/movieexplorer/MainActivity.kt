package com.amarinag.movieexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.amarinag.core.designsystem.theme.MovieExplorerTheme
import com.amarinag.feature.favorite.FavoriteRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MovieExplorerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { padding ->
                    FavoriteRoute(modifier = Modifier.padding(padding))
                }
            }
        }
    }
}