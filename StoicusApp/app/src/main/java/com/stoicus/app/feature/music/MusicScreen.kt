package com.stoicus.app.feature.music

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stoicus.app.core.ui.components.BackTopBar
import com.stoicus.app.core.ui.theme.*

@Composable
fun MusicScreen(
    context: Context,
    onNavigateBack: () -> Unit,
    viewModel: MusicViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val filteredTracks = if (state.selectedCategory != null) {
        state.tracks.filter { it.category == state.selectedCategory }
    } else {
        state.tracks
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            BackTopBar(title = "Música Estoica", onBack = onNavigateBack)
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "🎵 Música Estoica",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Música ambiental para meditación y enfoque",
                style = MaterialTheme.typography.bodyLarge,
                color = TextGrey
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Category filter chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = state.selectedCategory == null,
                    onClick = { viewModel.filterByCategory(null) },
                    label = { Text("Todas") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Bronze.copy(alpha = 0.2f),
                        selectedLabelColor = Bronze
                    )
                )
                FilterChip(
                    selected = state.selectedCategory == "meditation",
                    onClick = { viewModel.filterByCategory("meditation") },
                    label = { Text("🧘 Meditación") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MindColor.copy(alpha = 0.2f),
                        selectedLabelColor = MindColor
                    )
                )
                FilterChip(
                    selected = state.selectedCategory == "ambient",
                    onClick = { viewModel.filterByCategory("ambient") },
                    label = { Text("🌊 Ambient") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = BodyColor.copy(alpha = 0.2f),
                        selectedLabelColor = BodyColor
                    )
                )
                FilterChip(
                    selected = state.selectedCategory == "focus",
                    onClick = { viewModel.filterByCategory("focus") },
                    label = { Text("🎯 Enfoque") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = SoulColor.copy(alpha = 0.2f),
                        selectedLabelColor = SoulColor
                    )
                )
                FilterChip(
                    selected = state.selectedCategory == "sleep",
                    onClick = { viewModel.filterByCategory("sleep") },
                    label = { Text("😴 Sueño") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Bronze.copy(alpha = 0.2f),
                        selectedLabelColor = Bronze
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tracks list
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredTracks) { track ->
                    TrackItem(
                        track = track,
                        isPlaying = state.currentTrack?.id == track.id && state.isPlaying,
                        onPlay = { viewModel.playTrack(context, track) },
                        onPause = { viewModel.pauseTrack() },
                        onStop = { viewModel.stopTrack() },
                        onToggleFavorite = { viewModel.toggleFavorite(track) }
                    )
                }

                // Add space for player bar
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }

        // Now Playing Bar
        state.currentTrack?.let { track ->
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                NowPlayingBar(
                    track = track,
                    isPlaying = state.isPlaying,
                    onPlayPause = {
                        if (state.isPlaying) {
                            viewModel.pauseTrack()
                        } else {
                            viewModel.resumeTrack()
                        }
                    },
                    onStop = { viewModel.stopTrack() }
                )
            }
        }
    }
}

@Composable
private fun TrackItem(
    track: com.stoicus.app.core.data.local.entity.StoicMusicTrack,
    isPlaying: Boolean,
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onStop: () -> Unit,
    onToggleFavorite: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isPlaying) Bronze.copy(alpha = 0.2f) else DarkSurfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Music icon
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Bronze.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.MusicNote,
                    contentDescription = null,
                    tint = Bronze,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Track info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = track.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = track.artist,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextGrey
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "${track.duration / 60}:${(track.duration % 60).toString().padStart(2, '0')}",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                    Text(
                        text = track.category.replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.labelSmall,
                        color = Bronze
                    )
                }
            }

            // Actions
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Favorite",
                    tint = if (track.favorited) Color.Yellow else TextMuted,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(onClick = onToggleFavorite)
                )

                if (isPlaying) {
                    Icon(
                        imageVector = Icons.Default.Pause,
                        contentDescription = "Pause",
                        tint = Bronze,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable(onClick = onPause)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        tint = Bronze,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable(onClick = onPlay)
                    )
                }
            }
        }
    }
}

@Composable
private fun NowPlayingBar(
    track: com.stoicus.app.core.data.local.entity.StoicMusicTrack,
    isPlaying: Boolean,
    onPlayPause: () -> Unit,
    onStop: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = DarkSurfaceVariant.copy(alpha = 0.95f)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Track info
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Reproduciendo ahora:",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextMuted
                        )
                        Text(
                            text = track.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Bronze
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Stop,
                            contentDescription = "Stop",
                            tint = TextGrey,
                            modifier = Modifier
                                .size(28.dp)
                                .clickable(onClick = onStop)
                        )
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) "Pause" else "Play",
                            tint = Bronze,
                            modifier = Modifier
                                .size(36.dp)
                                .clickable(onClick = onPlayPause)
                        )
                    }
                }
            }
        }
    }
}
