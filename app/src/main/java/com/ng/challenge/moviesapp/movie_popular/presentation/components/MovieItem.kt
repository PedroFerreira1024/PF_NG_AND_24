package com.ng.challenge.moviesapp.movie_popular.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.core.presentation.components.common.AsyncImageUrl
import com.ng.challenge.moviesapp.core.presentation.components.common.BackgroundedText

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    showExtraData: Boolean = true,
    onClick: (id: Int) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        MovieRate(
            rate = movie.voteAvg,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .zIndex(2f)
                .padding(start = 6.dp, bottom = 8.dp)
        )
        if (showExtraData) {
            BackgroundedText(
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp)
                    .align(Alignment.TopStart)
                    .zIndex(2f),
                text = movie.title
            )
            BackgroundedText(
                modifier = Modifier
                    .padding(end = 8.dp, bottom = 8.dp)
                    .align(Alignment.BottomEnd)
                    .zIndex(2f),
                text = movie.releaseDate
            )
        }
        Card(
            modifier = modifier
                .fillMaxSize()
                .height(260.dp)
                .padding(4.dp)
                .clickable {
                    onClick(movie.id)
                },
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp
        ) {
            Box {
                AsyncImageUrl(
                    imageUrl = movie.imageUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(Color.Black)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(
        modifier = Modifier,
        movie = Movie(
            id = 1,
            title = "Movie",
            releaseDate = "22-2-2022",
            voteAvg = 3.7
        ),
        true,
        onClick = {})
}