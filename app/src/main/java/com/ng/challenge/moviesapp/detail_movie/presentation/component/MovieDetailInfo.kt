package com.ng.challenge.moviesapp.detail_movie.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ng.challenge.moviesapp.R
import com.ng.challenge.moviesapp.core.domain.model.MovieDetails

@Composable
fun MovieInfoContent(
    movieDetails: MovieDetails?,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        MovieDetailInfo(
            name = stringResource(id = R.string.duration),
            value = stringResource(
                id = R.string.duration_minutes,
                movieDetails?.duration.toString()
            )
        )
        MovieDetailInfo(
            name = stringResource(id = R.string.average_vote),
            value = movieDetails?.voteAvg.toString()
        )
        MovieDetailInfo(
            name = stringResource(id = R.string.release_date),
            value = movieDetails?.releaseDate.toString()
        )
    }
}
@Composable
fun MovieDetailInfo(
    name: String,
    value: String
) {
    Column {
        Text(
            text = name,
            style = MaterialTheme.typography.subtitle2.copy(fontSize = 13.sp, letterSpacing = 1.sp),
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.subtitle1.copy(fontSize = 13.sp, letterSpacing = 1.sp),
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 4.dp)
        )
    }
}

@Preview
@Composable
fun MovieFieldsPreview() {
    MovieInfoContent(
        modifier = Modifier,
        movieDetails = MovieDetails(
            id = 1,
            title = "A Movie",
            genres = listOf("Action","Suspense"),
            overview = null,
            backdropPathUrl = null,
            releaseDate = null,
            voteAvg = 4.3,
            duration = 90,
            voteCount = 102
        )
    )
}