import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Define dark color scheme
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryOrange,     // Main accent color (orange)
    secondary = DarkSurface,      // Secondary color for surface elements
    tertiary = AccentYellow,      // Tertiary color for highlights (yellow)
    background = DarkBackground,  // Dark theme background
    surface = DarkSurface,        // Dark surface color
    onPrimary = Color.White,      // Text/icon color on primary elements
    onSecondary = GrayText,       // Text color on secondary elements
    onBackground = Color.White,   // Text color on background
    onSurface = Color.White       // Text color on surface
)

// Define light color scheme
private val LightColorScheme = lightColorScheme(
    primary = PrimaryOrange,      // Main accent color (orange)
    secondary = DarkSurface,      // Secondary color for surface elements
    tertiary = AccentYellow,      // Tertiary color for highlights (yellow)
    background = Color.White,     // Light theme background
    surface = Color.LightGray,    // Light surface color
    onPrimary = Color.Black,      // Text/icon color on primary elements
    onSecondary = GrayText,       // Text color on secondary elements
    onBackground = Color.Black,   // Text color on background
    onSurface = Color.Black       // Text color on surface
)

// Theme composable function
@Composable
fun SceneBoxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Detect system theme
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // Choose color scheme based on theme
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    // Apply MaterialTheme with selected color scheme and typography
    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
