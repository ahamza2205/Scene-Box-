package com.example.scenebox.screens.movies.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterScreen(
    genres: List<String>,
    selectedGenres: List<String>,
    onGenreSelected: (String) -> Unit,
    rating: Float,
    onRatingChanged: (Float) -> Unit,
    onApplyFilter: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Filter Movies", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Select Genre:")
        LazyRow {
            items(genres) { genre ->
                FilterChip(
                    selected = selectedGenres.contains(genre),
                    onClick = { onGenreSelected(genre) },
                    label = { Text(genre) }
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Minimum Rating: ${rating.toInt()}")
        Slider(
            value = rating,
            onValueChange = onRatingChanged,
            valueRange = 0f..10f,
            steps = 9
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onApplyFilter, modifier = Modifier.fillMaxWidth()) {
            Text("Apply Filter")
        }
    }
}
