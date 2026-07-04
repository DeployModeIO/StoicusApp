package com.stoicus.app.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stoicus.app.core.ui.theme.*

/**
 * Fondo premium con gradiente obsidian → charcoal y glow dorado sutil en la parte superior.
 */
@Composable
fun PremiumBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(HeroGradientStart, Obsidian, Obsidian)
                )
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(280.dp)
                .align(Alignment.TopCenter)
                .blur(80.dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            ChampagneGold.copy(alpha = 0.15f),
                            Color.Transparent
                        )
                    )
                )
        )
        content()
    }
}

/**
 * Tarjeta premium con borde dorado sutil y fondo gradiente.
 */
@Composable
fun PremiumCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val baseModifier = modifier
        .clip(RoundedCornerShape(20.dp))
        .background(
            Brush.linearGradient(
                colors = listOf(CardGradientStart, CardGradientEnd),
                start = Offset(0f, 0f),
                end = Offset.Infinite
            )
        )
        .border(
            width = 1.dp,
            brush = Brush.linearGradient(
                colors = listOf(
                    ChampagneGold.copy(alpha = 0.25f),
                    Color.Transparent,
                    ChampagneGold.copy(alpha = 0.1f)
                )
            ),
            shape = RoundedCornerShape(20.dp)
        )

    Column(
        modifier = if (onClick != null) {
            baseModifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = rippleIndication(),
                    onClick = onClick
                )
                .padding(20.dp)
        } else {
            baseModifier.padding(20.dp)
        },
        content = content
    )
}

/**
 * Tarjeta Hero grande con cita estoica y fondo gradiente dorado.
 */
@Composable
fun PremiumHeroQuote(
    quote: String,
    author: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        ChampagneDeep.copy(alpha = 0.18f),
                        Charcoal,
                        Obsidian
                    )
                )
            )
            .border(
                width = 1.dp,
                color = ChampagneGold.copy(alpha = 0.3f),
                shape = RoundedCornerShape(24.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(120.dp)
                .align(Alignment.TopEnd)
                .blur(60.dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            ChampagneGold.copy(alpha = 0.2f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(28.dp)
        ) {
            Text(
                text = "“",
                style = MaterialTheme.typography.displayLarge,
                color = ChampagneGold.copy(alpha = 0.6f),
                fontSize = 48.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = quote,
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = StoicSerif,
                color = TextPrimary,
                lineHeight = 30.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                modifier = Modifier.width(60.dp),
                thickness = 1.dp,
                color = ChampagneGold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "— $author",
                style = MaterialTheme.typography.labelLarge,
                color = ChampagneGold,
                letterSpacing = 1.sp
            )
        }
    }
}

/**
 * Top App Bar premium con botón back estilo iOS y título serif.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PremiumTopBar(
    title: String,
    onBack: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                fontFamily = StoicSerif,
                color = TextPrimary
            )
        },
        navigationIcon = {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = "Volver",
                        tint = ChampagneGold
                    )
                }
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

/**
 * Chip / Badge dorado pequeño para etiquetas.
 */
@Composable
fun PremiumBadge(
    text: String,
    modifier: Modifier = Modifier,
    isActive: Boolean = false
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(
                if (isActive) {
                    Brush.horizontalGradient(listOf(GoldGradientStart, GoldGradientEnd))
                } else {
                    Brush.horizontalGradient(
                        listOf(
                            ChampagneGold.copy(alpha = 0.12f),
                            ChampagneGold.copy(alpha = 0.06f)
                        )
                    )
                }
            )
            .border(
                width = 1.dp,
                color = if (isActive) Color.Transparent else ChampagneGold.copy(alpha = 0.3f),
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = if (isActive) Obsidian else ChampagneGold,
            fontWeight = FontWeight.SemiBold
        )
    }
}

/**
 * Botón primario dorado premium.
 */
@Composable
fun PremiumGoldButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (enabled) {
                    Brush.horizontalGradient(listOf(GoldGradientStart, GoldGradientEnd))
                } else {
                    Brush.horizontalGradient(
                        listOf(
                            ChampagneGold.copy(alpha = 0.3f),
                            ChampagneGold.copy(alpha = 0.2f)
                        )
                    )
                }
            )
            .clickable(
                interactionSource = interactionSource,
                indication = rippleIndication(),
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = if (enabled) Obsidian else TextMuted,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.sp
        )
    }
}

/**
 * Línea decorativa dorada con ornamento central (estilo clásico).
 */
@Composable
fun GoldOrnamentDivider(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Divider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = ChampagneGold.copy(alpha = 0.4f)
        )
        Text(
            text = "✦",
            color = ChampagneGold,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Divider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = ChampagneGold.copy(alpha = 0.4f)
        )
    }
}

@Composable
private fun rippleIndication() = rememberRipple()
