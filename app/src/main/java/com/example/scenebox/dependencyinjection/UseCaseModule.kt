package com.example.scenebox.dependencyinjection

import com.example.scenebox.screens.movies.domain.GetNowPlayingMoviesUseCase
import com.example.scenebox.screens.movies.domain.GetPopularMoviesUesCase
import com.example.scenebox.screens.movies.domain.GetTopRatedMoviesUseCase
import com.example.scenebox.screens.movies.domain.GetUpcomingMoviesUseCase
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
    fun provideGetNowPlayingMoviesUseCase(repository: MovieRepository): GetNowPlayingMoviesUseCase {
        return GetNowPlayingMoviesUseCase(repository)
    }

    @Provides
    fun provideGetPopularMoviesUesCase(repository: MovieRepository): GetPopularMoviesUesCase {
        return GetPopularMoviesUesCase(repository)
    }

    @Provides
    fun provideGetUpcomingMoviesUseCase(repository: MovieRepository): GetUpcomingMoviesUseCase {
        return GetUpcomingMoviesUseCase(repository)
    }

    @Provides
    fun provideGetTopRatedMoviesUseCase(repository: MovieRepository): GetTopRatedMoviesUseCase {
        return GetTopRatedMoviesUseCase(repository)
    }

    @Provides
    fun provideGetMovieDetailsUseCase(repository: MovieDetailsRepository) : GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(repository)
    }
}