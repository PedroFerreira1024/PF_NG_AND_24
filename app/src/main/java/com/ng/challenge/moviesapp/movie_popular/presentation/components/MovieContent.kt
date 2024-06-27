package com.ng.challenge.moviesapp.movie_popular.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.ng.challenge.moviesapp.core.domain.model.Movie

@Composable
fun MovieContent(
    modifier : Modifier = Modifier,
    pagingMovies: LazyPagingItems<Movie>,
    paddingValues: PaddingValues,
    onClick: (movieId: Int) -> Int
){
    Box(
        modifier = modifier.background(Color.Black)
    ) {
       LazyVerticalGrid(
           columns = GridCells.Fixed(2),
           contentPadding = paddingValues,
           horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally),
           verticalArrangement = Arrangement.Center,
           modifier =  Modifier.fillMaxSize()
       ){
           items(pagingMovies.itemCount) {index ->
               val movie = pagingMovies[index]
               movie?.let {
                   MovieItem(
                       voteAvg = it.voteAvg,
                       imageUrl = it.imageUrl,
                       id = it.id,
                       onClick = {movieId ->
                           onClick(movieId)
                       }
                   )
               }
           }
       }
    }
}