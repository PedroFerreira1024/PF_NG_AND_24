package com.ng.challenge.moviesapp.core.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val voteAvg: Double = 0.0,
    val imageUrl: String = ""
)
