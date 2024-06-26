package com.ng.challenge.moviesapp.core.util

import com.ng.challenge.moviesapp.BuildConfig

fun String?.toPostUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"
fun String?.toBackdropUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"