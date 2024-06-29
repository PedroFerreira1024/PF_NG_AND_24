package com.ng.challenge.moviesapp.core.presentation.components.common

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun MovieAppBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    textColor: Color = MaterialTheme.colors.onPrimary,
    backgroundColor: Color = MaterialTheme.colors.primary
) {
    TopAppBar(
        title = {
                Text(
                    text = stringResource(id = title),
                    color = textColor
                )
        },
        backgroundColor = backgroundColor,
        modifier = modifier
    )
}