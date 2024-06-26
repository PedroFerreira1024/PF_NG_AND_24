package com.ng.challenge.moviesapp.core.domain.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val genres: List<String>,
    val overview: String?,
    val backdropPathUrl: String?,
    val releaseDate: String?,
    val voteAvg: Double = 0.0,
    val duration: Int = 0,
    val voteCount: Int = 0
)
