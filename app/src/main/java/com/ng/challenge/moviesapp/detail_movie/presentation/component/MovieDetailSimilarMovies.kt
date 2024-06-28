package com.ng.challenge.moviesapp.detail_movie.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.core.presentation.components.common.ErrorScreen
import com.ng.challenge.moviesapp.core.presentation.components.common.LoadingView
import com.ng.challenge.moviesapp.movie_popular.presentation.components.MovieItem

@Composable
fun MovieDetailSimilarMovies (
    pagingMoviesSimilar : LazyPagingItems<Movie>,
    modifier: Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        items(pagingMoviesSimilar.itemCount) { index ->
            val movie = pagingMoviesSimilar[index]
            movie?.let {
                MovieItem(
                    voteAvg = it.voteAvg,
                    imageUrl = it.imageUrl,
                    id = it.id,
                    onClick = {

                    })
            }
        }
        pagingMoviesSimilar.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        LoadingView()
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        LoadingView()
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    item {
                        ErrorScreen(
                            message = "Verify your Internet Connection",
                            retry = {
                                retry()
                            })
                    }
                }
                loadState.append is LoadState.Error -> {
                    item {
                        ErrorScreen(
                            message = "Verify your Internet Connection",
                            retry = {
                                retry()
                            })
                    }
                }
            }
        }
    }
}