package com.ng.challenge.moviesapp.detail_movie.di

import com.ng.challenge.moviesapp.core.data.remote.MovieService
import com.ng.challenge.moviesapp.detail_movie.data.repository.MovieDetailsRepository
import com.ng.challenge.moviesapp.detail_movie.data.source.MovieDetailDataSource
import com.ng.challenge.moviesapp.detail_movie.domain.repository.IMovieDetailsRepository
import com.ng.challenge.moviesapp.detail_movie.domain.source.IMovieDetailDataSource
import com.ng.challenge.moviesapp.detail_movie.domain.usecase.GetMovieDetailsUseCase
import com.ng.challenge.moviesapp.detail_movie.domain.usecase.IGetMovieDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // Contains dependency definitions
@InstallIn(SingletonComponent::class) // As a Singleton to hold the info during the life of the App
object MovieDetailsModule {

    @Provides // Providing the instance of the indicated object
    @Singleton // Marking the above instance should be a Singleton
    fun provideMovieDetailsDataSource(service: MovieService): IMovieDetailDataSource {
        return MovieDetailDataSource(service = service)
    }

    @Provides // Providing the instance of the indicated object
    @Singleton // Marking the above instance should be a Singleton
    fun provideMovieDetailsRepository(remoteDataSource: IMovieDetailDataSource): IMovieDetailsRepository {
        return MovieDetailsRepository(remoteDataSource = remoteDataSource)
    }

    @Provides // Providing the instance of the indicated object
    @Singleton // Marking the above instance should be a Singleton
    fun provideGetMovieDetailsUseCase(repository: IMovieDetailsRepository): IGetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(repository = repository)
    }
}