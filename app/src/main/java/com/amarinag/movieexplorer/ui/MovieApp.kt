package com.amarinag.movieexplorer.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.amarinag.feature.favorite.navigation.navigateToFavorite
import com.amarinag.feature.nowplaying.navigation.navigateToNowPlaying
import com.amarinag.movieexplorer.navigation.MovieNavHost
import com.amarinag.movieexplorer.navigation.TopLevelDestination

@Composable
fun MovieApp() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            MovieBottomBar(
                TopLevelDestination.values().toList(),
                { navController.navigateToTopLevelDestination(it) },
                navController.currentDestination()
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            MovieNavHost(navController = navController)
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

@Composable
fun NavController.currentDestination(): NavDestination? {
    return currentBackStackEntryAsState().value?.destination
}

private fun NavController.navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
    val topLevelNavOptions = navOptions {
        popUpTo(this@navigateToTopLevelDestination.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

    when (topLevelDestination) {
        TopLevelDestination.NOW_PLAYING -> this.navigateToNowPlaying(topLevelNavOptions)
        TopLevelDestination.FAVORITE -> this.navigateToFavorite(topLevelNavOptions)
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false