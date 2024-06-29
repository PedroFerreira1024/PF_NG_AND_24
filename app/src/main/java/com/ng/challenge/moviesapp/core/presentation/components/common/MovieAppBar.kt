package com.ng.challenge.moviesapp.core.presentation.components.common

import androidx.annotation.StringRes
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun MovieAppBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    backEnabled: Boolean = false,
    backPress: () -> Unit = {},
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
        navigationIcon =
            if (backEnabled) {
                @Composable {
                    IconButton(
                        onClick = backPress
                    ) {
                        Icon(
                            modifier = Modifier,
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            tint = textColor,
                            contentDescription = ""
                        )
                    }
                }
            } else null
        ,
        backgroundColor = backgroundColor,
        modifier = modifier
    )
}