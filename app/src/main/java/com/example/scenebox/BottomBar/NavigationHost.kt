package com.example.scenebox.BottomBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.scenebox.screens.HomeScreen
import com.example.scenebox.screens.movies.presentation.MoviesScreen
import com.example.scenebox.screens.ProfileScreen

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home.route,
        modifier = modifier
    ) {
        composable(NavigationItem.Home.route) { HomeScreen() }
        composable(NavigationItem.Movies.route) { MoviesScreen() }
        composable(NavigationItem.Profile.route) { ProfileScreen() }
    }
}