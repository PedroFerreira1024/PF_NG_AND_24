package com.ng.challenge.moviesapp.core.data.remote

import com.ng.challenge.moviesapp.core.data.remote.response.MovieDetailResponse
import com.ng.challenge.moviesapp.core.data.remote.response.MovieResponse
import com.ng.challenge.moviesapp.core.data.remote.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): MovieResponse

    @GET("search/multi")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("query") query: String
    ): SearchResponse

    @GET("movie/{movie_id}}")
    suspend fun getMovie(
        @Query("movie_id") movieId: Int
    ): MovieDetailResponse

    @GET("movie/{movieId}/similar")
    suspend fun getMoviesSimilar(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): MovieResponse
}