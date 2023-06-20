package com.amarinag.feature.favorite

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FavoriteRoute() {
    FavoriteScreen()
}

@Composable
internal fun FavoriteScreen() {
    Text(text = "Ventana de favoritos!")
}