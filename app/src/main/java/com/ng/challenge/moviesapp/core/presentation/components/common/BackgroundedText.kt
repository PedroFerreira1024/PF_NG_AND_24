package com.ng.challenge.moviesapp.core.presentation.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.ng.challenge.moviesapp.ui.theme.BlackTransparent
import com.ng.challenge.moviesapp.ui.theme.YellowSecondary

@Composable
fun BackgroundedText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 12.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = YellowSecondary,
    backgroundColor: Color = BlackTransparent,
    cornerRadius: Dp = 8.dp
) {
    Box(
        modifier = modifier
            //.padding(top = 8.dp) // adding some space to the label
            .background(
                backgroundColor,
                // rounded corner to match with the OutlinedTextField
                shape = RoundedCornerShape(cornerRadius)
            )
            .clip(RoundedCornerShape(cornerRadius))
    ) {
        Text(
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.Center)
                .zIndex(2f),
            text = text,
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = color,
        )
    }
}