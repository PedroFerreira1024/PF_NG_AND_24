package com.ng.challenge.moviesapp.movie_popular.di

import com.ng.challenge.moviesapp.core.data.remote.MovieService
import com.ng.challenge.moviesapp.movie_popular.data.repository.MoviePopularRepository
import com.ng.challenge.moviesapp.movie_popular.data.source.MoviePopularDataSource
import com.ng.challenge.moviesapp.movie_popular.domain.repository.IMoviePopularRepository
import com.ng.challenge.moviesapp.movie_popular.domain.source.IMoviePopularDataSource
import com.ng.challenge.moviesapp.movie_popular.domain.usecase.GetPopularMoviesUseCase
import com.ng.challenge.moviesapp.movie_popular.domain.usecase.IGetPopularMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // Contains dependency definitions
@InstallIn(SingletonComponent::class) // As a Singleton to hold the info during the life of the App
object MoviePopularFeatureModule {

    @Provides // Providing the instance of the indicated object
    @Singleton // Marking the above instance should be a Singleton
    fun provideMovieDataSource(service: MovieService): IMoviePopularDataSource {
        return MoviePopularDataSource(service = service)
    }
    @Provides // Providing the instance of the indicated object
    @Singleton // Marking the above instance should be a Singleton
    fun provideMovieRepository(remoteDataSource: IMoviePopularDataSource): IMoviePopularRepository {
        return MoviePopularRepository(remoteDataSource = remoteDataSource)
    }
    @Provides // Providing the instance of the indicated object
    @Singleton // Marking the above instance should be a Singleton
    fun provideGetMoviesPopularUseCase(moviePopularRepository: IMoviePopularRepository): IGetPopularMoviesUseCase {
        return GetPopularMoviesUseCase(repository = moviePopularRepository)
    }
}