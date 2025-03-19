package com.example.scenebox.ui.theme

import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Define your OrangeGradient as a Brush
val OrangeGradient = Brush.linearGradient(
    colors = listOf(Color(0xFFFF8A65), Color(0xFFFF5A5F)), // Example gradient colors
    start = Offset(0f, 0f),
    end = Offset(100f, 100f)
)

@Composable
fun GradientText(text: String, fontSize: Float) {
    BasicText(
        text = text,
        style = TextStyle(
            fontSize = fontSize.sp, // Convert Float to TextUnit using sp()
            fontWeight = FontWeight.Bold,
            brush = OrangeGradient
        ),
        modifier = Modifier
    )
}
