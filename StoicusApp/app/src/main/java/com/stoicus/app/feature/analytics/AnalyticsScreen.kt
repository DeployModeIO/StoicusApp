package com.stoicus.app.feature.analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.stoicus.app.core.ui.components.ProgressRing
import com.stoicus.app.core.ui.components.StreakCounter
import com.stoicus.app.core.ui.theme.*

@Composable
fun AnalyticsScreen(
    onNavigateBack: () -> Unit,
    viewModel: AnalyticsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "📊 Analytics",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
        }

        // Time range selector
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TimeRange.values().forEach { range ->
                    FilterChip(
                        selected = state.selectedTimeRange == range,
                        onClick = { viewModel.selectTimeRange(range) },
                        label = { Text(range.displayName) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Bronze.copy(alpha = 0.2f),
                            selectedLabelColor = Bronze
                        )
                    )
                }
            }
        }

        // Stats cards
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    title = "Sesiones",
                    value = "${state.data.totalSessions}",
                    icon = "🎯",
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "Racha",
                    value = "${state.data.currentStreak} días",
                    icon = "🔥",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    title = "Ánimo",
                    value = "${String.format("%.1f", state.data.averageMood)}/10",
                    icon = "😊",
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "Estrés",
                    value = "${String.format("%.1f", state.data.averageStress)}/10",
                    icon = "😰",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Virtue scores
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Virtudes",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        VirtueRing("Sabiduría", 0.75f, WisdomColor)
                        VirtueRing("Justicia", 0.60f, JusticeColor)
                        VirtueRing("Valor", 0.80f, CourageColor)
                        VirtueRing("Templanza", 0.70f, TemperanceColor)
                    }
                }
            }
        }

        // Pillar distribution
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Distribución por Pilar",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    val mindCount = state.data.sessionsByPillar["MIND"] ?: 0
                    val bodyCount = state.data.sessionsByPillar["BODY"] ?: 0
                    val soulCount = state.data.sessionsByPillar["SOUL"] ?: 0
                    val total = (mindCount + bodyCount + soulCount).coerceAtLeast(1)

                    PillarBar("🧠 Mente", mindCount, total, MindColor)
                    Spacer(modifier = Modifier.height(8.dp))
                    PillarBar("💪 Cuerpo", bodyCount, total, BodyColor)
                    Spacer(modifier = Modifier.height(8.dp))
                    PillarBar("🛡️ Alma", soulCount, total, SoulColor)
                }
            }
        }

        // Mood trend
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Tendencia de Ánimo",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    if (state.data.moodTrend.isNotEmpty()) {
                        MoodChart(
                            data = state.data.moodTrend,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                        )
                    } else {
                        Text(
                            text = "Comienza a registrar tu ánimo para ver la tendencia",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextGrey
                        )
                    }
                }
            }
        }

        // Insights
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Bronze.copy(alpha = 0.1f))
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "💡 Insights Estoicos",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Bronze
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Tu consistencia matutina mejoró un 23% esta semana.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextWhite
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "El pilar que más necesitas es Justicia — interacción con otros.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextWhite
                    )
                }
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    icon: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = icon, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = TextGrey
            )
        }
    }
}

@Composable
private fun VirtueRing(
    name: String,
    score: Float,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProgressRing(
            progress = score,
            size = 60.dp,
            strokeWidth = 6.dp,
            color = color
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.bodySmall,
            color = TextGrey
        )
    }
}

@Composable
private fun PillarBar(
    label: String,
    count: Int,
    total: Int,
    color: Color
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, color = TextWhite, style = MaterialTheme.typography.bodyMedium)
            Text(text = "$count", color = TextGrey, style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { count.toFloat() / total },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = color,
            trackColor = StoneLight
        )
    }
}

@Composable
private fun MoodChart(
    data: List<Float>,
    modifier: Modifier = Modifier
) {
    // Simple bar chart
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        data.forEach { value ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 2.dp)
                    .fillMaxHeight(value / 10f)
                    .background(Bronze.copy(alpha = 0.6f), RoundedCornerShape(4.dp))
            )
        }
    }
}
