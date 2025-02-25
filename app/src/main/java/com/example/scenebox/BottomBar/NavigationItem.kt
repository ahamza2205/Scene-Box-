package com.example.scenebox.BottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val route: String, val icon: ImageVector, val title: String) {
    object Home : NavigationItem("home", Icons.Default.Home, "Home")
    object Movies : NavigationItem("movies", Icons.Default.Add, "Movies")
    object Profile : NavigationItem("profile", Icons.Default.Person, "Profile")
}
