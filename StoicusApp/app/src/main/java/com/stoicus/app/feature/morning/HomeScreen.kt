package com.stoicus.app.feature.morning

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stoicus.app.core.domain.model.Pillar
import com.stoicus.app.core.ui.components.*
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

    PremiumBackground {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(top = 60.dp, bottom = 40.dp)
        ) {
            // ═══ HEADER: Brand + Date ═══
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "STOICUS",
                            style = MaterialTheme.typography.labelLarge,
                            color = ChampagneGold,
                            letterSpacing = 4.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = state.dateLine.ifEmpty { "Hoy" },
                            style = MaterialTheme.typography.bodySmall,
                            color = TextMuted
                        )
                    }
                    PremiumBadge(text = "DÍA ${state.currentStreak}", isActive = true)
                }
            }

            // ═══ HERO QUOTE ═══
            item {
                PremiumHeroQuote(
                    quote = "No pongas tu felicidad en las manos de otros.",
                    author = "Marco Aurelio"
                )
            }

            // ═══ GREETING ═══
            item {
                Column {
                    Text(
                        text = state.greeting,
                        style = MaterialTheme.typography.displayMedium,
                        fontFamily = StoicSerif,
                        color = TextPrimary,
                        fontWeight = FontWeight.Light
                    )
                    Text(
                        text = state.userName.ifEmpty { "Filósofo" },
                        style = MaterialTheme.typography.displayMedium,
                        fontFamily = StoicSerif,
                        color = ChampagneGold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Un día más para practicar la virtud",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )
                }
            }

            // ═══ SECTION: Rituales ═══
            item { SectionHeader(title = "Rituales del Día") }

            item {
                PremiumRitualCard(
                    title = "Ritual Matutino",
                    subtitle = "Fortalece tu mente para el día",
                    icon = Icons.Filled.WbSunny,
                    pillar = Pillar.MIND,
                    isCompleted = state.todayMorningCompleted,
                    onClick = onNavigateToMorning
                )
            }
            item {
                PremiumRitualCard(
                    title = "Micro-acciones",
                    subtitle = "${state.todayMicroActions} completadas hoy",
                    icon = Icons.Filled.Bolt,
                    pillar = Pillar.BODY,
                    isCompleted = false,
                    onClick = onNavigateToMicroActions
                )
            }
            item {
                PremiumRitualCard(
                    title = "Ritual Nocturno",
                    subtitle = "Reflexiona sobre tu día",
                    icon = Icons.Filled.NightsStay,
                    pillar = Pillar.SOUL,
                    isCompleted = state.todayEveningCompleted,
                    onClick = onNavigateToEvening
                )
            }

            // ═══ SECTION: Biblioteca ═══
            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionHeader(title = "Biblioteca")
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    PremiumFeatureCard(
                        title = "Progreso",
                        icon = Icons.Filled.Insights,
                        onClick = onNavigateToAnalytics,
                        modifier = Modifier.weight(1f)
                    )
                    PremiumFeatureCard(
                        title = "Filosofía",
                        icon = Icons.Filled.MenuBook,
                        onClick = onNavigateToPhilosophy,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    PremiumFeatureCard(
                        title = "Galería",
                        icon = Icons.Filled.PhotoLibrary,
                        onClick = onNavigateToGallery,
                        modifier = Modifier.weight(1f)
                    )
                    PremiumFeatureCard(
                        title = "Música",
                        icon = Icons.Filled.MusicNote,
                        onClick = onNavigateToMusic,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // ═══ FOOTER ═══
            item {
                Spacer(modifier = Modifier.height(24.dp))
                GoldOrnamentDivider()
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "“La felicidad de tu vida depende de la calidad de tus pensamientos.”",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMuted,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "— Marco Aurelio",
                    style = MaterialTheme.typography.labelSmall,
                    color = ChampagneGold.copy(alpha = 0.7f),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 4.dp, height = 18.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(ChampagneGold)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.labelLarge,
            color = TextPrimary,
            letterSpacing = 2.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun PremiumRitualCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    pillar: Pillar,
    isCompleted: Boolean,
    onClick: () -> Unit
) {
    val accent = when (pillar) {
        Pillar.MIND -> MindColor
        Pillar.BODY -> BodyColor
        Pillar.SOUL -> SoulColor
    }
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        accent.copy(alpha = if (isCompleted) 0.18f else 0.08f),
                        Charcoal,
                        Obsidian
                    )
                )
            )
            .border(
                width = 1.dp,
                color = if (isCompleted) accent.copy(alpha = 0.5f) else ChampagneGold.copy(alpha = 0.15f),
                shape = RoundedCornerShape(22.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(),
                onClick = onClick
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(accent.copy(alpha = 0.15f))
                    .border(1.dp, accent.copy(alpha = 0.4f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accent,
                    modifier = Modifier.size(26.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = StoicSerif,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
            if (isCompleted) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(Brush.horizontalGradient(listOf(GoldGradientStart, GoldGradientEnd))),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Completado",
                        tint = Obsidian,
                        modifier = Modifier.size(18.dp)
                    )
                }
            } else {
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = null,
                    tint = ChampagneGold.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@Composable
private fun PremiumFeatureCard(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .height(120.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Brush.verticalGradient(listOf(CardGradientStart, CardGradientEnd)))
            .border(1.dp, ChampagneGold.copy(alpha = 0.12f), RoundedCornerShape(20.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(),
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(ChampagneGold.copy(alpha = 0.1f))
                    .border(1.dp, ChampagneGold.copy(alpha = 0.25f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = ChampagneGold,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = TextPrimary,
                letterSpacing = 1.sp
            )
        }
    }
}