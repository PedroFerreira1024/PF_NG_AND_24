package com.ng.challenge.moviesapp.movie_popular.presentation

import java.text.DecimalFormat

fun Double?.toRoundedDecimals() = DecimalFormat("#.#").format(this ?: 0f) ?: "0.0"