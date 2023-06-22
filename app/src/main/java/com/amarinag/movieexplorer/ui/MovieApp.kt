package com.amarinag.movieexplorer.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
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
    NavigationBar(
        modifier = modifier,
        tonalElevation = 0.dp
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    if (selected) {
                        Icon(
                            imageVector = destination.selectedIcon,
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            imageVector = destination.unselectedIcon,
                            contentDescription = null
                        )
                    }
                },
                label = {
                    Text(text = stringResource(id = destination.iconTextId))
                })
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false