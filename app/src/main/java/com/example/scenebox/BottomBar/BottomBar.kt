package com.example.scenebox.BottomBar

import DarkBackground
import GrayText
import OrangeGradient
import PrimaryOrange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.scenebox.R

@Composable
fun MainScreenWithAnchoredBottomBar() {
    val navController = rememberNavController()

    // Set the default screen as Movies
    var selectedItem by remember { mutableStateOf(NavigationItem.Movies.route) }

    // Navigate to Movies screen initially
    LaunchedEffect(Unit) {
        navController.navigate(NavigationItem.Movies.route) {
            // This will clear the back stack and make sure Movies is the only screen
            popUpTo = navController.graph.startDestinationId
            launchSingleTop = true
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(DarkBackground)) {
        Column(modifier = Modifier.fillMaxSize().padding(bottom = 80.dp)) {
            NavigationHost(navController, modifier = Modifier.weight(1f))
        }
        ConcaveBottomNavigationBarWithFab(
            selectedItem = selectedItem,
            navController = navController,
            onItemSelected = { selectedItem = it },
            onFabClick = { navController.navigate(NavigationItem.Movies.route) },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun ConcaveBottomNavigationBarWithFab(
    selectedItem: String,
    navController: NavController,
    onItemSelected: (String) -> Unit,
    onFabClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawConcaveShape()
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BottomNavigationItem(
                item = NavigationItem.Home,
                isSelected = selectedItem == NavigationItem.Home.route,
                onClick = {
                    navController.navigate(NavigationItem.Home.route)
                    onItemSelected(NavigationItem.Home.route)
                }
            )
            BottomNavigationItem(
                item = NavigationItem.Profile,
                isSelected = selectedItem == NavigationItem.Profile.route,
                onClick = {
                    navController.navigate(NavigationItem.Profile.route)
                    onItemSelected(NavigationItem.Profile.route)
                }
            )
        }

        CenteredFab(onClick = {
            navController.navigate(NavigationItem.Movies.route) {
                // This will clear the back stack and make sure the MoviesScreen is the only screen
                popUpTo = navController.graph.startDestinationId
                launchSingleTop = true
            }
            onItemSelected(NavigationItem.Movies.route)  // Reset selectedItem to Movies
        })
    }
}


@Composable
fun CenteredFab(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomCenter)
            .offset(y = -50.dp)
    ) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier.size(56.dp),
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(8.dp),
            backgroundColor = Color.Transparent
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(brush = OrangeGradient, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.movies),
                    contentDescription = "Add",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun BottomNavigationItem(
    item: NavigationItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 8.dp)) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(
                        brush = if (isSelected) OrangeGradient else Brush.linearGradient(colors = listOf(Color.Transparent, Color.Transparent)),
                        shape = CircleShape
                    )
                    .padding(4.dp)
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = item.title,
                color = if (isSelected) PrimaryOrange else GrayText,
                fontSize = 10.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

fun DrawScope.drawConcaveShape() {
    val path = Path().apply {
        val width = size.width
        val height = size.height

        moveTo(0f, 0f)
        lineTo(width * 0.35f, 0f)

        cubicTo(
            width * 0.4f, 0f,
            width * 0.4f, height * 0.5f,
            width * 0.5f, height * 0.5f
        )
        cubicTo(
            width * 0.6f, height * 0.5f,
            width * 0.6f, 0f,
            width * 0.65f, 0f
        )

        lineTo(width, 0f)
        lineTo(width, height)
        lineTo(0f, height)
        close()
    }
    drawPath(path, Color(0xFF424242))
}