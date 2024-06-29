package com.ng.challenge.moviesapp.detail_movie.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ng.challenge.moviesapp.R
import com.ng.challenge.moviesapp.core.presentation.components.common.AsyncImageUrl

@Composable
fun MovieDetailBackdropImage (
    backdropImageUrl: String,
    modifier: Modifier
){
    Box(modifier = modifier) {
        AsyncImageUrl(
            imageUrl = backdropImageUrl,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun MovieDetailBackdropImage () {
    MovieDetailBackdropImage(
        backdropImageUrl = "",
        modifier = Modifier.fillMaxWidth()
    )
}