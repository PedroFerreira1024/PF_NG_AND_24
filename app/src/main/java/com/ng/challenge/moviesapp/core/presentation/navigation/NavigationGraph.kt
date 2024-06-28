package com.ng.challenge.moviesapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ng.challenge.moviesapp.movie_popular.presentation.MoviePopularScreen
import com.ng.challenge.moviesapp.movie_popular.presentation.MoviePopularViewModel
import com.ng.challenge.moviesapp.movie_popular.presentation.state.MoviePopularState
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

                })
        }
    }
}