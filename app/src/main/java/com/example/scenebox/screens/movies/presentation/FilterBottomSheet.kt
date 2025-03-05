package com.example.scenebox.screens.movies.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    genres: List<String>,
    selectedGenres: List<String>,
    onGenreSelected: (String) -> Unit,
    rating: Float,
    onRatingChanged: (Float) -> Unit,
    onApplyFilter: () -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
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

            Button(onClick = {
                onApplyFilter()
                onDismiss()
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Apply Filter")
            }
        }
    }
}