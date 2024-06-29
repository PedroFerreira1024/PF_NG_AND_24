package com.ng.challenge.moviesapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ng.challenge.moviesapp.core.util.Constants
import com.ng.challenge.moviesapp.detail_movie.presentation.MovieDetailScreen
import com.ng.challenge.moviesapp.detail_movie.presentation.MovieDetailViewModel
import com.ng.challenge.moviesapp.movie_popular.presentation.MoviePopularScreen
import com.ng.challenge.moviesapp.movie_popular.presentation.MoviePopularViewModel
import com.ng.challenge.moviesapp.search_movie.presentation.MovieSearchEvent
import com.ng.challenge.moviesapp.search_movie.presentation.MovieSearchScreen
import com.ng.challenge.moviesapp.search_movie.presentation.MovieSearchViewModel

@Composable
fun NavigationGraph(modifier: Modifier = Modifier,
                    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route)
    {
        composable(BottomNavItem.MoviePopular.route) {
            val viewModel: MoviePopularViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            MoviePopularScreen(
                uiState = uiState,
                navigateToDetailMovie = {
                    navController.navigate(DetailScreenNav.DetailScreen.passMovieId(movieId = it))
                })
        }
        composable(BottomNavItem.MovieSearch.route) {
            val viewModel: MovieSearchViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val onEvent: (MovieSearchEvent) -> Unit = viewModel::event
            val onFetch: (String) -> Unit = viewModel::fetch

            MovieSearchScreen(
                uiState = uiState,
                onEvent = onEvent,
                onFetch = onFetch,
                navigateToDetailMovie = {
                    navController.navigate(DetailScreenNav.DetailScreen.passMovieId(movieId = it))
                })
        }
        composable(
            route = DetailScreenNav.DetailScreen.route,
            arguments = listOf(
                navArgument(Constants.MOVIE_DETAIL_ARGUMENT) {
                    type= NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            val viewModel: MovieDetailViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            MovieDetailScreen(
                uiState = uiState,
            )
        }
    }
}