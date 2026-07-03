package com.stoicus.app.feature.morning

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stoicus.app.core.domain.model.Pillar
import com.stoicus.app.core.ui.components.QuoteCard
import com.stoicus.app.core.ui.components.StreakCounter
import com.stoicus.app.core.ui.theme.*

@Composable
fun HomeScreen(
    onNavigateToMorning: () -> Unit,
    onNavigateToMicroActions: () -> Unit,
    onNavigateToEvening: () -> Unit,
    onNavigateToAnalytics: () -> Unit,
    onNavigateToPhilosophy: () -> Unit,
    onNavigateToGallery: () -> Unit,
    onNavigateToMusic: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Greeting
        item {
            Column(modifier = Modifier.padding(top = 32.dp)) {
                Text(
                    text = "${state.greeting}, ${state.userName}",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Hoy es un buen día para practicar la virtud",
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextGrey
                )
            }
        }

        // Streak
        item {
            StreakCounter(currentStreak = state.currentStreak)
        }

        // Daily Quote
        item {
            QuoteCard(
                quote = "No pongas tu felicidad en las manos de otros.",
                author = "Marco Aurelio"
            )
        }

        // Quick Actions
        item {
            Text(
                text = "Hoy",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
        }

        // Morning Ritual Card
        item {
            RitualCard(
                title = "Ritual Matutino",
                subtitle = "Fortalece tu mente para el día",
                icon = "🌅",
                pillar = Pillar.MIND,
                isCompleted = state.todayMorningCompleted,
                onClick = onNavigateToMorning
            )
        }

        // Micro Actions Card
        item {
            RitualCard(
                title = "Micro-acciones",
                subtitle = "${state.todayMicroActions} completadas hoy",
                icon = "⚡",
                pillar = Pillar.BODY,
                isCompleted = false,
                onClick = onNavigateToMicroActions
            )
        }

        // Evening Ritual Card
        item {
            RitualCard(
                title = "Ritual Nocturno",
                subtitle = "Reflexiona sobre tu día",
                icon = "🌙",
                pillar = Pillar.SOUL,
                isCompleted = state.todayEveningCompleted,
                onClick = onNavigateToEvening
            )
        }

        // Bottom actions
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                BottomActionCard(
                    title = "Analytics",
                    icon = "📊",
                    onClick = onNavigateToAnalytics,
                    modifier = Modifier.weight(1f)
                )
                BottomActionCard(
                    title = "Filosofía",
                    icon = "📚",
                    onClick = onNavigateToPhilosophy,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Additional features
        item {
            Text(
                text = "Recursos",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                BottomActionCard(
                    title = "Galería",
                    icon = "🎨",
                    onClick = onNavigateToGallery,
                    modifier = Modifier.weight(1f)
                )
                BottomActionCard(
                    title = "Música",
                    icon = "🎵",
                    onClick = onNavigateToMusic,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Bottom spacer
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun RitualCard(
    title: String,
    subtitle: String,
    icon: String,
    pillar: Pillar,
    isCompleted: Boolean,
    onClick: () -> Unit
) {
    val (gradientStart, gradientEnd) = when (pillar) {
        Pillar.MIND -> MindColor to DeepBlueLight
        Pillar.BODY -> BodyColor to BronzeDark
        Pillar.SOUL -> SoulColor to StoicRed
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isCompleted) gradientStart.copy(alpha = 0.2f) else DarkSurfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = icon, fontSize = 32.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextGrey
                )
            }
            if (isCompleted) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MindColor
                ) {
                    Text(
                        text = "✓",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        color = TextWhite,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomActionCard(
    title: String,
    icon: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = icon, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = TextWhite
            )
        }
    }
}
