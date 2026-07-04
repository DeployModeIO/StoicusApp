package com.stoicus.app.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stoicus.app.core.ui.theme.Bronze
import com.stoicus.app.core.ui.theme.ChampagneGold
import com.stoicus.app.core.ui.theme.DarkBackground

/**
 * Top bar reutilizable con botón "back" circular accesible (contentDescription = "Volver").
 * Pensada para pantallas internas que reciben [onBack] desde el NavGraph.
 *
 * @param title   Título visible de la pantalla.
 * @param onBack  Lambda de navegación inversa (normalmente `navController.popBackStack()`).
 * @param tint    Color de icono/título (por defecto ChampagneGold).
 */
@Composable
fun BackTopBar(
    title: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = ChampagneGold,
    subtitle: String? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(44.dp)
                .clip(CircleShape)
                .background(ChampagneGold.copy(alpha = 0.10f))
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                contentDescription = "Volver",
                tint = tint
            )
        }

        androidx.compose.foundation.layout.Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 60.dp, top = 4.dp)
        ) {
            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    color = tint,
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}