package com.example.scenebox.dependencyinjection

import com.example.scenebox.data.remote.MovieApiService
import com.example.scenebox.screens.movies.data.MovieRepositoryImpl
import com.example.scenebox.screens.movies.domain.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMovieRepository(apiService: MovieApiService): MovieRepository {
        return MovieRepositoryImpl(apiService)
    }
}
