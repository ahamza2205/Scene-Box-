package com.example.scenebox.screens.moviesdetails.ui

import AccentYellow
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
            YellowStar(modifier = Modifier.size(32.dp))
        }
        if (partialStar > 0) {
            YellowStar(modifier = Modifier.size(32.dp), fillFraction = partialStar)
        }
        repeat(emptyStars) {
            YellowStar(modifier = Modifier.size(32.dp), isFilled = false)
        }
    }
}

@Composable
fun YellowStar(
    modifier: Modifier = Modifier,
    isFilled: Boolean = true,
    fillFraction: Float = 1.0f
) {
    Canvas(modifier = modifier) {
        val yellowColor = AccentYellow

        scale(scale = size.minDimension / 100f) {
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
                drawPath(path, color = yellowColor)
                if (fillFraction < 1.0f) {
                    drawRect(
                        color = Color.Black.copy(alpha = 0.5f),
                        topLeft = Offset(x = size.width * fillFraction, y = 0f),
                        size = Size(size.width * (1 - fillFraction), size.height),
                    )
                }
            } else {
                drawPath(path, color = Color.Gray) // Empty star color
            }
        }
    }
}
