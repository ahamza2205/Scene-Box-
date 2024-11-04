import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scenebox.R
import com.example.scenebox.ui.theme.GradientText

@Composable
fun MainScreenWithAnchoredBottomBar() {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf(NavigationItem.Home.route) }

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

        CenteredFab(onClick = onFabClick)
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

sealed class NavigationItem(val route: String, val icon: ImageVector, val title: String) {
    object Home : NavigationItem("home", Icons.Default.Home, "Home")
    object Movies : NavigationItem("movies", Icons.Default.Add, "Movies")
    object Profile : NavigationItem("profile", Icons.Default.Person, "Profile")
}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        GradientText("Home")
    }
}

@Composable
fun MoviesScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        GradientText("Movies")
    }
}

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        GradientText("Profile")
    }
}
