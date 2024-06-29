package com.ng.challenge.moviesapp.detail_movie.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
fun MovieDetailBackdropImagePreview () {
    MovieDetailBackdropImage(
        backdropImageUrl = "",
        modifier = Modifier.fillMaxWidth()
    )
}