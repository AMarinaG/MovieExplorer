package com.amarinag.movieexplorer.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.amarinag.core.designsystem.component.MovieNavigationBar
import com.amarinag.core.designsystem.component.MovieNavigationBarItem
import com.amarinag.movieexplorer.navigation.MovieNavHost
import com.amarinag.movieexplorer.navigation.TopLevelDestination

@Composable
fun MovieApp(
    windowSizeClass: WindowSizeClass,
    appState: MovieAppState = rememberMovieAppState(windowSizeClass = windowSizeClass)
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            MovieBottomBar(
                destinations = appState.topLevelDestination,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
                currentDestination = appState.currentDestination
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            MovieNavHost(appState = appState)
        }
    }
}

@Composable
private fun MovieBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    MovieNavigationBar(
        modifier = modifier,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            MovieNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        imageVector = destination.unselectedIcon,
                        contentDescription = null
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = destination.selectedIcon,
                        contentDescription = null
                    )
                },
                label = { Text(text = stringResource(id = destination.iconTextId)) },
                modifier = modifier
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false