package com.ng.challenge.moviesapp.core.presentation.components.common

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ng.challenge.moviesapp.R

@Composable
fun AsyncImageUrl(
    modifier: Modifier = Modifier,
    imageUrl: String,
    crossFadeEnable: Boolean = true,
    @DrawableRes errorImage: Int = R.drawable.image_error,
    @DrawableRes placeholderImage: Int = R.drawable.image_placeholder,
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(crossFadeEnable)
            .error(errorImage)
            .placeholder(placeholderImage)
            .build(),
        contentDescription = "",
        contentScale = contentScale,
        modifier = modifier)
}