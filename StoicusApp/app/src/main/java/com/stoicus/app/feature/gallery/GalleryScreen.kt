package com.stoicus.app.feature.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.stoicus.app.core.ui.components.BackTopBar
import com.stoicus.app.core.ui.theme.*

@Composable
fun GalleryScreen(
    onNavigateBack: () -> Unit,
    viewModel: GalleryViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val filteredImages = if (state.selectedCategory != null) {
        state.images.filter { it.category == state.selectedCategory }
    } else {
        state.images
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
            BackTopBar(title = "Galería Estoica", onBack = onNavigateBack)
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "🎨 Galería Estoica",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Arte y símbolos de la filosofía estoica",
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
                    selected = state.selectedCategory == "statues",
                    onClick = { viewModel.filterByCategory("statues") },
                    label = { Text("🗿 Estatuas") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MindColor.copy(alpha = 0.2f),
                        selectedLabelColor = MindColor
                    )
                )
                FilterChip(
                    selected = state.selectedCategory == "artwork",
                    onClick = { viewModel.filterByCategory("artwork") },
                    label = { Text("🎨 Arte") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = BodyColor.copy(alpha = 0.2f),
                        selectedLabelColor = BodyColor
                    )
                )
                FilterChip(
                    selected = state.selectedCategory == "symbols",
                    onClick = { viewModel.filterByCategory("symbols") },
                    label = { Text("✡️ Símbolos") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = SoulColor.copy(alpha = 0.2f),
                        selectedLabelColor = SoulColor
                    )
                )
                FilterChip(
                    selected = state.selectedCategory == "manuscripts",
                    onClick = { viewModel.filterByCategory("manuscripts") },
                    label = { Text("📜 Manuscritos") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Bronze.copy(alpha = 0.2f),
                        selectedLabelColor = Bronze
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Images grid
            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Bronze)
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(filteredImages) { image ->
                        ImageCard(
                            image = image,
                            onToggleFavorite = { viewModel.toggleFavorite(image) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ImageCard(
    image: com.stoicus.app.core.data.local.entity.StoicImage,
    onToggleFavorite: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
    ) {
        Column {
            // Image placeholder (in production, use Coil/Glide to load actual images)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .background(Bronze.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder for image
                Text(
                    text = "🖼️",
                    fontSize = 48.sp,
                    color = TextWhite
                )
            }

            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = image.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = if (image.favorited) "⭐" else "☆",
                        fontSize = 18.sp,
                        modifier = Modifier.clickable(onClick = onToggleFavorite)
                    )
                }
                
                if (image.philosopher != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "— ${image.philosopher}",
                        style = MaterialTheme.typography.labelMedium,
                        color = Bronze
                    )
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = image.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextGrey,
                    maxLines = 2
                )
            }
        }
    }
}
