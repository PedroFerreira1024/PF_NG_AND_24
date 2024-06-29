package com.ng.challenge.moviesapp.core.presentation.navigation

import com.ng.challenge.moviesapp.core.util.Constants

sealed class DetailScreenNav(val route: String) {
    object DetailScreen : DetailScreenNav(
        route = "movie_detail_destination?${Constants.MOVIE_DETAIL_ARGUMENT}=" +
                "{${Constants.MOVIE_DETAIL_ARGUMENT}}"
    ) {
        fun passMovieId(movieId: Int) =
            "movie_detail_destination?${Constants.MOVIE_DETAIL_ARGUMENT}=$movieId"
    }
}