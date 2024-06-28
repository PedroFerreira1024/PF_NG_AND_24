package com.ng.challenge.moviesapp.search_movie.presentation

sealed class MovieSearchEvent {
    data class EnteredQuery(val value: String) : MovieSearchEvent()
}