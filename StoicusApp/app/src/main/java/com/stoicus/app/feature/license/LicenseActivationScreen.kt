package com.stoicus.app.feature.license

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
 * Pantalla de activación de licencia Gumroad.
 *
 * - Muestra instrucciones de compra ($10 USD en Gumroad).
 * - Campo para ingresar la clave de licencia.
 * - Llama a la API de Gumroad para verificar.
 */
@Composable
fun LicenseActivationScreen(
    onNavigateBack: () -> Unit,
    onLicenseActivated: () -> Unit,
    viewModel: LicenseViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    // Auto-navegar al activar exitosamente
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            kotlinx.coroutines.delay(1500) // mostrar mensaje de éxito 1.5s
            onLicenseActivated()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Activar Licencia") },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBackground)
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("🔑", fontSize = 48.sp)
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Desbloquea Stoicus completo",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Pago único de \$10 USD vía Gumroad. Acceso ilimitado de por vida.",
                style = MaterialTheme.typography.bodyMedium,
                color = TextGrey
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Card de instrucciones
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "¿Cómo activar?",
                        color = MindColor,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("1. Compra tu licencia en Gumroad.", color = TextWhite)
                    Text("2. Recibe tu clave por email.", color = TextWhite)
                    Text("3. Ingrésala abajo y pulsa 'Activar'.", color = TextWhite)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = state.licenseKey,
                onValueChange = viewModel::onLicenseKeyChange,
                label = { Text("Clave de licencia Gumroad") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = TextWhite,
                    unfocusedTextColor = TextWhite,
                    focusedLabelColor = MindColor,
                    cursorColor = MindColor
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            state.message?.let { msg ->
                Text(
                    text = msg,
                    color = if (state.isSuccess) MindColor else StoicRed,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Button(
                onClick = viewModel::verify,
                enabled = !state.isLoading && state.licenseKey.isNotBlank(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MindColor)
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                        color = TextWhite
                    )
                } else {
                    Text("Activar", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}