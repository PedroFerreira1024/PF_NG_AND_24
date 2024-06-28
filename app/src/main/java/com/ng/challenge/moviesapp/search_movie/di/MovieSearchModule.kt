package com.ng.challenge.moviesapp.search_movie.di

import com.ng.challenge.moviesapp.core.data.remote.MovieService
import com.ng.challenge.moviesapp.search_movie.data.repository.MovieSearchRepository
import com.ng.challenge.moviesapp.search_movie.data.source.MovieSearchDataSource
import com.ng.challenge.moviesapp.search_movie.domain.repository.IMovieSearchRepository
import com.ng.challenge.moviesapp.search_movie.domain.source.IMovieSearchDataSource
import com.ng.challenge.moviesapp.search_movie.domain.usecase.GetMovieSearchUseCase
import com.ng.challenge.moviesapp.search_movie.domain.usecase.IGetMovieSearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // Contains dependency definitions
@InstallIn(SingletonComponent::class) // As a Singleton to hold the info during the life of the App
object MovieSearchModule {

    @Provides // Providing the instance of the indicated object
    @Singleton // Marking the above instance should be a Singleton
    fun provideMovieSearchDataSource(service: MovieService): IMovieSearchDataSource {
        return MovieSearchDataSource(service = service)
    }
    @Provides // Providing the instance of the indicated object
    @Singleton // Marking the above instance should be a Singleton
    fun provideMovieSearchRepository(remoteDataSource: IMovieSearchDataSource): IMovieSearchRepository {
        return MovieSearchRepository(remoteDataSource = remoteDataSource)
    }

    @Provides // Providing the instance of the indicated object
    @Singleton // Marking the above instance should be a Singleton
    fun provideGetMovieSearchUseCase(repository: IMovieSearchRepository): IGetMovieSearchUseCase {
        return GetMovieSearchUseCase(repository = repository)
    }
}