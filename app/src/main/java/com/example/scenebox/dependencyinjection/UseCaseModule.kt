package com.example.scenebox.dependencyinjection

import com.example.scenebox.screens.movies.domain.GetNowPlayingMoviesUseCase
import com.example.scenebox.screens.movies.domain.GetPopularMoviesUesCase
import com.example.scenebox.screens.movies.domain.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetNowPlayingMoviesUseCase(repository: MovieRepository): GetNowPlayingMoviesUseCase {
        return GetNowPlayingMoviesUseCase(repository)
    }

    @Provides
    fun provideGetPopularMoviesUesCase(repository: MovieRepository): GetPopularMoviesUesCase {
        return GetPopularMoviesUesCase(repository)
    }
}