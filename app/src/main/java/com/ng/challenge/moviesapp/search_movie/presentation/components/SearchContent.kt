package com.ng.challenge.moviesapp.search_movie.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ng.challenge.moviesapp.core.domain.model.MovieDetails
import com.ng.challenge.moviesapp.core.domain.model.MovieSearch
import com.ng.challenge.moviesapp.core.presentation.components.common.ErrorScreen
import com.ng.challenge.moviesapp.core.presentation.components.common.LoadingView
import com.ng.challenge.moviesapp.movie_popular.presentation.components.MovieItem
import com.ng.challenge.moviesapp.search_movie.presentation.MovieSearchEvent

@Composable
fun SearchContent(

    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    pagingMovies: LazyPagingItems<MovieSearch>,
    query: String,
    onSearch: (String) -> Unit,
    onEvent: (MovieSearchEvent) -> Unit,
    onDetail: (movieId: Int) -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        SearchComponent(
            query = query,
            onSearch = {
                isLoading = true
                onSearch(it)
            },
            onQueryChangeEvent = {
                onEvent(it)
            },
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            items(pagingMovies.itemCount) { index ->
                val movie = pagingMovies[index]
                movie?.let {
                    MovieItem(
                        modifier = Modifier
                            .height(190.dp),
                        voteAvg = it.voteAvg,
                        imageUrl = it.imageUrl,
                        id = it.id
                    ) { movieId ->
                      onDetail(movieId)
                    }
                }
                isLoading = false
            }
            pagingMovies.apply {
                when {
                    isLoading -> {
                        item(
                            span = {
                                GridItemSpan(maxLineSpan)
                            }
                        ) {
                            LoadingView()
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        isLoading = false // to stop provoking a general loading
                        item(
                            span = {
                                GridItemSpan(maxLineSpan)
                            }
                        ) {
                            ErrorScreen(
                                message = "Verify your Internet Connection",
                                retry = {
                                    retry()
                                })
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        isLoading = false // to stop provoking a general loading
                        item(
                            span = {
                                GridItemSpan(maxLineSpan)
                            }
                        ) {
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
}