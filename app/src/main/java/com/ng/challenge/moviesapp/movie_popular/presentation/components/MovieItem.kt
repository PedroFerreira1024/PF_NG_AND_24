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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ng.challenge.moviesapp.R

@Composable
fun MovieItem (
    modifier: Modifier = Modifier,
    voteAvg: Double,
    imageUrl: String,
    id: Int,
    onClick: (id: Int) -> Unit
){
    Box(
        modifier = modifier
    ) {
        MovieRate(
            rate = voteAvg,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .zIndex(2f)
                .padding(start = 6.dp, bottom = 8.dp)
                .background(Color.Black)
        )
        Card(
            modifier = modifier
                .fillMaxSize()
                .height(260.dp)
                .padding(4.dp)
                .clickable {
                    onClick(id)
                },
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp
        ) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .error(R.drawable.image_error)
                        .placeholder(R.drawable.image_placeholder)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
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
            voteAvg = 4.7,
            imageUrl = "",
            id = 1,
            onClick = {})
}