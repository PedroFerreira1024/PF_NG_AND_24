package com.ng.challenge.moviesapp.detail_movie.presentation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.ng.challenge.moviesapp.R
import com.ng.challenge.moviesapp.core.presentation.components.common.MovieAppBar
import com.ng.challenge.moviesapp.detail_movie.presentation.component.MovieDetailContent
import com.ng.challenge.moviesapp.detail_movie.presentation.state.MovieDetailState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(
    uiState: MovieDetailState,
    backPressNavigation: () -> Unit,
    navigateToDetailMovie: (Int) -> Unit
) {

    val pagingMoviesSimilar = uiState.results.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MovieAppBar(
                title = R.string.movie_detail,
                backEnabled = true,
                backPress = backPressNavigation
            )
        },
        content = {
            MovieDetailContent(
                movieDetails = uiState.movieDetails,
                pagingMoviesSimilar = pagingMoviesSimilar,
                isLoading = uiState.isLoading,
                isError = uiState.error,
                navigateToDetailMovie = navigateToDetailMovie
            )
        }
    )
}