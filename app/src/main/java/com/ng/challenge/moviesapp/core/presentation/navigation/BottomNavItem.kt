package com.ng.challenge.moviesapp.core.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) {
    object MoviePopular: BottomNavItem(
        title = "Popular",
        icon = Icons.Default.Movie,
        route = "movie_popular_screen"
    )

    object MovieSearch: BottomNavItem(
        title = "Search",
        icon = Icons.Default.Search,
        route = "movie_search_screen"
    )
}