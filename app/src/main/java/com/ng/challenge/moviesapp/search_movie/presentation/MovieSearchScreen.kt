package com.ng.challenge.moviesapp.search_movie.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.ng.challenge.moviesapp.R
import com.ng.challenge.moviesapp.core.presentation.components.common.MovieAppBar
import com.ng.challenge.moviesapp.search_movie.presentation.components.SearchContent
import com.ng.challenge.moviesapp.search_movie.presentation.state.MovieSearchState

@Composable
fun MovieSearchScreen(
    uiState: MovieSearchState,
    onEvent: (MovieSearchEvent) -> Unit,
    onFetch: (String) -> Unit,
    navigateToDetailMovie: (Int) -> Unit
) {

    val pagingMovies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MovieAppBar(
                title = R.string.search_movies
            )
        },
        content = {paddingValues ->
            SearchContent(
                paddingValues = paddingValues,
                pagingMovies = pagingMovies,
                query = uiState.query,
                onSearch = {
                    onFetch(it)
                },
                onEvent = {
                    onEvent(it)
                },
                onDetail = { movieId ->
                    navigateToDetailMovie(movieId)
                }
            )
        }
    )
}