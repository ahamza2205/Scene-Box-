package com.example.scenebox.BottomBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.scenebox.screens.HomeScreen
import com.example.scenebox.screens.movies.presentation.MoviesScreen
import com.example.scenebox.screens.ProfileScreen
import com.example.scenebox.screens.moviesdetails.presentation.MovieDetailsScreen


@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home.route,
        modifier = modifier
    ) {
        composable(NavigationItem.Home.route) { HomeScreen() }

        composable(NavigationItem.Movies.route) {
            MoviesScreen(
                onMovieClick = { movieId ->
                    navController.navigate("movieDetails/$movieId")
                }
            )
        }

        composable(NavigationItem.Profile.route) { ProfileScreen() }

        composable(
            route = "movieDetails/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: return@composable
            MovieDetailsScreen(
                movieId = movieId,
                onBackClick = { navController.popBackStack() },
                onFavoriteClick = { /* Handle favorite click logic here */ }
            )
        }
    }
}
