package com.ng.challenge.moviesapp.movie_popular.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ng.challenge.moviesapp.movie_popular.presentation.toRoundedDecimals
import com.ng.challenge.moviesapp.ui.theme.BlackTransparent

@Composable
fun MovieRate (
    modifier: Modifier,
    rate: Double,
    backgroundColor: Color = BlackTransparent,
    cornerRadius: Dp = 8.dp,)
{
    Box(
        modifier = modifier
            //.padding(top = 8.dp) // adding some space to the label
            .background(
                backgroundColor,
                // rounded corner to match with the OutlinedTextField
                shape = RoundedCornerShape(cornerRadius)
            )
            .padding(2.dp)
            .clip(RoundedCornerShape(cornerRadius))
    ) {
        Row(
            modifier = Modifier.widthIn(max = 60.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.Yellow,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = rate.toRoundedDecimals(),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}

@Preview
@Composable
fun MovieRatePreview() {
    MovieRate(
        rate = 7.5,
        modifier = Modifier
    )
}