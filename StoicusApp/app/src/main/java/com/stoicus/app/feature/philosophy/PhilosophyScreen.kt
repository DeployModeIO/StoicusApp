package com.stoicus.app.feature.philosophy

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stoicus.app.core.ui.components.BackTopBar
import com.stoicus.app.core.ui.theme.*

@Composable
fun PhilosophyScreen(
    onNavigateBack: () -> Unit,
    viewModel: PhilosophyViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val filteredQuotes = if (state.selectedPillar != null) {
        state.quotes.filter { it.pillar == state.selectedPillar }
    } else {
        state.quotes
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            BackTopBar(title = "Filosofía", onBack = onNavigateBack)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "📚 Filosofía",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Sabiduría de los estoicos",
                style = MaterialTheme.typography.bodyLarge,
                color = TextGrey
            )
        }

        // Filter chips
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = state.selectedPillar == null,
                    onClick = { viewModel.filterByPillar(null) },
                    label = { Text("Todos") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Bronze.copy(alpha = 0.2f),
                        selectedLabelColor = Bronze
                    )
                )
                FilterChip(
                    selected = state.selectedPillar == "MIND",
                    onClick = { viewModel.filterByPillar("MIND") },
                    label = { Text("Mente") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MindColor.copy(alpha = 0.2f),
                        selectedLabelColor = MindColor
                    )
                )
                FilterChip(
                    selected = state.selectedPillar == "BODY",
                    onClick = { viewModel.filterByPillar("BODY") },
                    label = { Text("Cuerpo") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = BodyColor.copy(alpha = 0.2f),
                        selectedLabelColor = BodyColor
                    )
                )
                FilterChip(
                    selected = state.selectedPillar == "SOUL",
                    onClick = { viewModel.filterByPillar("SOUL") },
                    label = { Text("Alma") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = SoulColor.copy(alpha = 0.2f),
                        selectedLabelColor = SoulColor
                    )
                )
            }
        }

        items(filteredQuotes) { quote ->
            QuoteItem(
                quote = quote,
                onToggleFavorite = { viewModel.toggleFavorite(quote) }
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

@Composable
private fun QuoteItem(
    quote: com.stoicus.app.core.data.local.entity.PhilosophyQuote,
    onToggleFavorite: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = when (quote.pillar) {
                            "MIND" -> "🧠"
                            "BODY" -> "💪"
                            else -> "🛡️"
                        },
                        fontSize = 20.sp
                    )
                    Text(
                        text = "— ${quote.era}",
                        style = MaterialTheme.typography.labelMedium,
                        color = TextMuted
                    )
                }
                Text(
                    text = if (quote.favorited) "⭐" else "☆",
                    fontSize = 20.sp,
                    modifier = Modifier.clickable(onClick = onToggleFavorite)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "\"${quote.text}\"",
                style = MaterialTheme.typography.bodyLarge,
                fontStyle = FontStyle.Italic,
                color = TextWhite
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "— ${quote.author}",
                style = MaterialTheme.typography.bodyMedium,
                color = Bronze,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}
