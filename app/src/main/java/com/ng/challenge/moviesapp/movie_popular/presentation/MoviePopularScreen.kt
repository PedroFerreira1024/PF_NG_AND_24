package com.ng.challenge.moviesapp.movie_popular.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.ng.challenge.moviesapp.R
import com.ng.challenge.moviesapp.core.presentation.components.common.MovieAppBar
import com.ng.challenge.moviesapp.core.util.UtilFunctions
import com.ng.challenge.moviesapp.movie_popular.presentation.components.MovieContent
import com.ng.challenge.moviesapp.movie_popular.presentation.state.MoviePopularState

@Composable
fun MoviePopularScreen (
    uiState: MoviePopularState,
    navigateToDetailMovie: (Int) -> Unit
) {

    val movies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MovieAppBar(
                title = R.string.popular_movies
            )
        },
        content = { paddingValues ->
            MovieContent(
                pagingMovies = movies,
                paddingValues = paddingValues) { movieId ->
                    UtilFunctions.logInfo("MOVIE_ID", movieId.toString())
                    navigateToDetailMovie(movieId)
            }
        }
    )
}