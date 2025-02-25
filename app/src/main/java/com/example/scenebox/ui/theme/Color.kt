// Color.kt
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val DarkBackground = Color(0xFF1C1B29)   // Background color
val DarkSurface = Color(0xFF242238)      // Surface color for cards
val PrimaryOrange = Color(0xFFFF5A5F)    // Highlight color for titles
val GrayText = Color(0xFFB4B4C4)         // Text color for secondary information
val AccentYellow = Color(0xFFFFD700)     // Rating badge color
// Gradient brush for orange text
val OrangeGradient = Brush.linearGradient(
    colors = listOf(
        Color(0xFFFF8A65), // Lighter orange
        Color(0xFFFF5A5F)  // Darker orange
    )
)