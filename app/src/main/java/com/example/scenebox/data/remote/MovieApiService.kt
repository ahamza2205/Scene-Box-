package com.example.scenebox.data.remote

import com.example.scenebox.screens.movies.domain.MovieResponse
import com.example.scenebox.screens.moviesdetails.domain.MovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("with_genres") genreId: String? = null,
        @Query("vote_average.gte") minRating: Double? = null
    ): MovieResponse

    @GET("movie/popular")
   suspend fun getPopularMovies(
        @Query("api_key") apiKey: String ,
        @Query("page") page: Int,
        @Query("with_genres") genreId: String? = null,
        @Query("vote_average.gte") minRating: Double? = null
    ): MovieResponse

    @GET("movie/top_rated")
     suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("with_genres") genreId: String? = null,
        @Query("vote_average.gte") minRating: Double? = null
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("with_genres") genreId: String? = null,
        @Query("vote_average.gte") minRating: Double? = null
    ): MovieResponse


    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailsResponse
}
