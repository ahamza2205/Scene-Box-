package com.example.scenebox.dependencyinjection

import com.example.scenebox.screens.movies.domain.GetMoviesUseCase
import com.example.scenebox.screens.movies.domain.MovieRepository
import com.example.scenebox.screens.moviesdetails.domain.GetMovieDetailsUseCase
import com.example.scenebox.screens.moviesdetails.domain.MovieDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {


    @Provides
    fun provideGetMoviesUseCase(repository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(repository)
    }

    @Provides
    fun provideGetMovieDetailsUseCase(repository: MovieDetailsRepository) : GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(repository)
    }
}