package com.example.scenebox

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.scenebox.screens.movies.presentation.MoviesScreen
import com.example.scenebox.screens.moviesdetails.presentation.MovieDetailsScreen

@Composable
fun MainNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "moviesScreen") {
        composable("moviesScreen") {
            MoviesScreen(
                onMovieClick = { movieId ->
                    navController.navigate("movieDetails/$movieId")
                }
            )
        }
        composable(
            route = "movieDetails/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: return@composable
            MovieDetailsScreen(
                movieId = movieId,
                onBackClick = { navController.popBackStack() },
                onFavoriteClick = {
                    // Define your favorite click logic here
                }
            )
        }
    }
}

