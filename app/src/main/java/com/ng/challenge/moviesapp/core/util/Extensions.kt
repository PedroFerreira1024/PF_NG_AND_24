package com.ng.challenge.moviesapp.core.util

import com.ng.challenge.moviesapp.BuildConfig
import com.ng.challenge.moviesapp.movie_popular.data.mapper.IMAGE_QUALITY_HIGH
import com.ng.challenge.moviesapp.movie_popular.data.mapper.IMAGE_QUALITY_LOW

fun String?.toPostUrl(quality: String = IMAGE_QUALITY_LOW) = "${BuildConfig.BASE_URL_IMAGE+quality}$this"
fun String?.toBackdropUrl() = "${BuildConfig.BASE_URL_IMAGE + IMAGE_QUALITY_HIGH}$this"