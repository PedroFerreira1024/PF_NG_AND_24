package com.ng.challenge.moviesapp.core.util

import com.ng.challenge.moviesapp.BuildConfig

fun String?.toPostUrl(quality: String) = "${BuildConfig.BASE_URL_IMAGE+quality}$this"
fun String?.toBackdropUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"