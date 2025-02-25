package com.example.scenebox.BottomBar

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    var selectedTab by remember { mutableStateOf("Now Playing") }

    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home.route,
        modifier = modifier
    ) {
        composable(NavigationItem.Home.route) { HomeScreen() }

        composable(NavigationItem.Movies.route) {
            MoviesScreen(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                onMovieClick = { movieId ->
                    navController.navigate("movieDetails/$movieId/$selectedTab")
                }
            )
        }

        composable(NavigationItem.Profile.route) { ProfileScreen() }

        // Transition for movie details screen
        composable(
            route = "movieDetails/{movieId}/{selectedTab}",
            arguments = listOf(
                navArgument("movieId") { type = NavType.IntType },
                navArgument("selectedTab") { type = NavType.StringType }
            ),
            enterTransition = {
                slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }) + fadeIn(tween(400))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth }) + fadeOut(
                    tween(
                        400
                    )
                )
            }
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: return@composable
            val selectedTab = backStackEntry.arguments?.getString("selectedTab") ?: "Now Playing"

            MovieDetailsScreen(
                movieId = movieId,
                selectedTab = selectedTab,
                onBackClick = { navController.popBackStack() },
                onFavoriteClick = { /* Handle favorite click logic here */ }
            )
        }
    }
}




