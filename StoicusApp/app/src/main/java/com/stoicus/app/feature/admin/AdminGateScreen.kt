package com.stoicus.app.feature.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stoicus.app.core.ui.theme.*

/**
 * Pantalla de acceso al panel admin.
 *
 * Muestra un campo de PIN. Si el panel no está habilitado en este build
 * (release), muestra un mensaje de "no disponible".
 */
@Composable
fun AdminGateScreen(
    onAdminAuthorized: () -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: AdminViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var pin by remember { mutableStateOf("") }

    // Si no es un build con panel admin habilitado, no se puede acceder.
    if (!state.isAdminEnabled) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBackground),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("🔒", fontSize = 48.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Panel no disponible en este build.",
                    color = TextWhite,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "El panel de administrador está deshabilitado por seguridad.",
                    color = TextGrey,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = onNavigateBack) { Text("Volver") }
            }
        }
        return
    }

    // Auto-navegar al panel si la sesión ya está activa
    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) onAdminAuthorized()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🔐", fontSize = 48.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Acceso Administrador",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = TextWhite
        )
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = pin,
            onValueChange = { if (it.length <= 8 && it.all(Char::isDigit)) pin = it },
            label = { Text("PIN de admin") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = TextWhite,
                unfocusedTextColor = TextWhite,
                focusedLabelColor = MindColor,
                cursorColor = MindColor
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        state.message?.let {
            Text(it, color = if (state.isLoggedIn) MindColor else StoicRed)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = { viewModel.login(pin) },
            enabled = pin.length >= 4,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Entrar")
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onNavigateBack) {
            Text("Volver", color = TextGrey)
        }
    }
}