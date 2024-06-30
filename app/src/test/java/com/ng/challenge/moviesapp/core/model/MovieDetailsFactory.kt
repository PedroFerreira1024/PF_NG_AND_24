package com.ng.challenge.moviesapp.core.model

import com.ng.challenge.moviesapp.core.domain.model.MovieDetails

class MovieDetailsFactory {

    fun create(poster: Poster) = when (poster) {
        Poster.Avengers -> {
            MovieDetails(
                id = 1,
                title = "Avengers",
                voteAvg = 7.2,
                genres = listOf("Adventure", "Action", "Science Fiction"),
                overview = "overview",
                backdropPathUrl = "url",
                releaseDate = "02-03-2012",
                duration = 135,
                voteCount = 7
            )
        }

        Poster.InsideOut -> {
            MovieDetails(
                id = 1,
                title = "InsideOut",
                voteAvg = 7.2,
                genres = listOf("Adventure", "Comdey", "Animation", "Drama"),
                overview = "overview",
                backdropPathUrl = "url",
                releaseDate = "02-03-2012",
                duration = 135,
                voteCount = 7
            )
        }
    }

    sealed class Poster {
        object Avengers : Poster()
        object InsideOut : Poster()
    }
}