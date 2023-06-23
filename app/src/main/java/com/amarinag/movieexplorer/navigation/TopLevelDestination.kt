/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amarinag.movieexplorer.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArtTrack
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.outlined.Upcoming
import androidx.compose.material.icons.rounded.ArtTrack
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.ui.graphics.vector.ImageVector
import com.amarinag.movieexplorer.R

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    NOW_PLAYING(
        selectedIcon = Icons.Rounded.ArtTrack,
        unselectedIcon = Icons.Outlined.ArtTrack,
        iconTextId = R.string.now_playing,
        titleTextId = R.string.app_name

    ),
    FAVORITE(
        selectedIcon = Icons.Rounded.Star,
        unselectedIcon = Icons.Outlined.StarOutline,
        iconTextId = R.string.favorites,
        titleTextId = R.string.favorites
    )
}
