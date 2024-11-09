package com.example.scenebox.screens.moviesdetails

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.scale

@Composable
fun StarRatingBar(userScore: Int) {
    val totalStars = 5
    val filledStars = userScore / 20
    val partialStar = (userScore % 20) / 20f
    val emptyStars = totalStars - filledStars - if (partialStar > 0) 1 else 0
    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(filledStars) {
            GradientStar(modifier = Modifier.size(32.dp))
        }
        if (partialStar > 0) {
            GradientStar(modifier = Modifier.size(32.dp), fillFraction = partialStar)
        }
        repeat(emptyStars) {
            GradientStar(modifier = Modifier.size(32.dp), isFilled = false)
        }
    }
}
@Composable
fun GradientStar(
    modifier: Modifier = Modifier,
    isFilled: Boolean = true,
    fillFraction: Float = 1.0f
) {
    Canvas(modifier = modifier) {
        val gradientBrush = Brush.linearGradient(
            colors = listOf(Color(0xFFFF8A65), Color(0xFFFF5A5F))
        )

        scale(scale = size.minDimension / 100f) {
            // شكل النجمة
            val path = Path().apply {
                moveTo(50f, 0f)
                lineTo(61f, 35f)
                lineTo(98f, 35f)
                lineTo(68f, 57f)
                lineTo(79f, 91f)
                lineTo(50f, 70f)
                lineTo(21f, 91f)
                lineTo(32f, 57f)
                lineTo(2f, 35f)
                lineTo(39f, 35f)
                close()
            }
            if (isFilled) {
                drawPath(path, brush = gradientBrush)
                if (fillFraction < 1.0f) {
                    drawRect(
                        color = Color.Black.copy(alpha = 0.5f),
                        topLeft = Offset(x = size.width * fillFraction, y = 0f),
                        size = Size(size.width * (1 - fillFraction), size.height),
                    )
                }
            } else {
                drawPath(path, color = Color.Gray)
            }
        }
    }
}
