package com.ng.challenge.moviesapp.core.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.ng.challenge.moviesapp.core.util.Constants.MOVIE_DETAIL_ARGUMENT

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

    object MovieDetail: BottomNavItem(
        title = "Details",
        icon = Icons.Default.Details,
        route = "movie_detail_destination?$MOVIE_DETAIL_ARGUMENT=" +
                "{$MOVIE_DETAIL_ARGUMENT}"
    ){
        fun passMovieId(movieId: Int) =
            "movie_detail_destination?$MOVIE_DETAIL_ARGUMENT=$movieId"
    }
}