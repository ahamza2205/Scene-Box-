package com.example.scenebox.screens.movies.ui

import GrayText
import PrimaryOrange
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.scenebox.R
import com.example.scenebox.ui.theme.OrangeGradient

@Composable
fun TabButton(title: String, selectedTab: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            val iconRes = when (title) {
                "Now Playing" -> R.drawable.nowplaying
                "Popular" -> R.drawable.popular
                "Top Rated" -> R.drawable.toprated
                "Upcoming" -> R.drawable.upcoming
                else -> R.drawable.nowplaying
            }
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "$title Icon",
                tint = if (title == selectedTab) PrimaryOrange else GrayText,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            // Use the OrangeGradient here
            Text(
                text = title,
                color = if (title == selectedTab) PrimaryOrange else GrayText,
                fontWeight = FontWeight.Bold,
                style = if (title == selectedTab) androidx.compose.ui.text.TextStyle(
                    brush = OrangeGradient
                ) else androidx.compose.ui.text.TextStyle(color = GrayText) // Apply gradient only for selected tab
            )
        }
        if (title == selectedTab) {
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(2.dp)
                    .background(PrimaryOrange)
            )
        }
    }
}

@Composable
fun TabRow(selectedTab: String, onTabSelected: (String) -> Unit) {
    val tabs = listOf("Now Playing", "Popular", "Top Rated", "Upcoming")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        tabs.forEach { title ->
            TabButton(
                title = title,
                selectedTab = selectedTab,
                onClick = { onTabSelected(title) }
            )
        }
    }
}



