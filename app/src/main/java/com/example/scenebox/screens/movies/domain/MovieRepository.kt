package com.example.scenebox.screens.movies.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(category: String, apiKey: String): Flow<PagingData<Movie>>
}
