package com.stoicus.app.feature.legal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.stoicus.app.R
import com.stoicus.app.core.ui.theme.DarkBackground
import com.stoicus.app.core.ui.theme.DarkSurface
import com.stoicus.app.core.ui.theme.DarkSurfaceVariant
import com.stoicus.app.core.ui.theme.TextWhite
import java.io.BufferedReader

/**
 * Pantalla genérica para mostrar documentos legales.
 *
 * Lee los textos desde res/raw según el [LegalType] indicado.
 */
@Composable
fun LegalTextsScreen(
    legalType: LegalTextsScreen.LegalType,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val (title, rawRes) = when (legalType) {
        LegalTextsScreen.LegalType.PRIVACY_POLICY -> "Política de Privacidad" to R.raw.privacy_policy
        LegalTextsScreen.LegalType.TERMS_OF_SERVICE -> "Términos y Condiciones" to R.raw.terms_of_service
        LegalTextsScreen.LegalType.COOKIE_POLICY -> "Política de Cookies" to R.raw.cookie_policy
    }

    val legalText = remember(legalType) {
        context.resources.openRawResource(rawRes).bufferedReader().use(BufferedReader::readText)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
            ) {
                Text(
                    text = legalText,
                    modifier = Modifier.padding(20.dp),
                    color = TextWhite,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

object LegalTextsScreen {
    enum class LegalType { PRIVACY_POLICY, TERMS_OF_SERVICE, COOKIE_POLICY }
}