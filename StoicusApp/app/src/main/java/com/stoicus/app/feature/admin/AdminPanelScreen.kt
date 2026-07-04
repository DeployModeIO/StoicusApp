package com.stoicus.app.feature.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stoicus.app.core.ui.theme.*

/**
 * Panel de administración.
 *
 * Acciones disponibles:
 * - Resetear contador de usos (para pruebas).
 * - Simular compra de Gumroad (inyecta licencia falsa).
 * - Cerrar sesión.
 *
 * Solo accesible si la sesión admin está activa (verificación en [AdminViewModel]).
 */
@Composable
fun AdminPanelScreen(
    onNavigateBack: () -> Unit,
    viewModel: AdminViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Panel Admin") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkSurface,
                    titleContentColor = TextWhite
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBackground)
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Estado actual
            item {
                AdminStatusCard(state = state)
            }

            // Acciones de prueba
            item {
                Text(
                    "Acciones de prueba",
                    style = MaterialTheme.typography.titleMedium,
                    color = TextWhite,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                AdminActionCard(
                    title = "Resetear contador de usos",
                    subtitle = "Vuelve el contador a 0 para seguir probando.",
                    buttonText = "Resetear",
                    isLoading = state.isLoading,
                    onClick = { viewModel.resetUsageCounter() }
                )
            }

            item {
                AdminActionCard(
                    title = "Simular compra Gumroad",
                    subtitle = "Inyecta una licencia ficticia sin llamar a la API.",
                    buttonText = "Simular \$10 USD",
                    isLoading = state.isLoading,
                    onClick = { viewModel.simulatePurchase() }
                )
            }

            // Mensaje de feedback
            item {
                state.message?.let { msg ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
                    ) {
                        Text(
                            text = msg,
                            modifier = Modifier.padding(16.dp),
                            color = MindColor
                        )
                    }
                }
            }

            // Logout
            item {
                Spacer(modifier = Modifier.height(24.dp))
                OutlinedButton(
                    onClick = { viewModel.logout() },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Cerrar sesión admin", color = StoicRed)
                }
            }
        }
    }
}

@Composable
private fun AdminStatusCard(state: AdminUiState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                "Estado del dispositivo",
                style = MaterialTheme.typography.titleMedium,
                color = TextWhite,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            StatusRow(label = "Usos restantes", value = "${state.remainingUses}/5")
            StatusRow(
                label = "Licencia activa",
                value = if (state.isLicensed) "Sí" else "No"
            )
            StatusRow(
                label = "Sesión admin",
                value = if (state.isLoggedIn) "Activa" else "Inactiva"
            )
        }
    }
}

@Composable
private fun StatusRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = TextGrey)
        Text(value, color = TextWhite, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun AdminActionCard(
    title: String,
    subtitle: String,
    buttonText: String,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(title, color = TextWhite, fontWeight = FontWeight.Bold)
            Text(subtitle, color = TextGrey, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onClick,
                enabled = !isLoading,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MindColor)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        strokeWidth = 2.dp,
                        color = TextWhite
                    )
                } else {
                    Text(buttonText)
                }
            }
        }
    }
}