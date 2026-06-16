package com.stoicus.app.feature.micro

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stoicus.app.core.ui.components.ProgressRing
import com.stoicus.app.core.ui.theme.*

@Composable
fun MicroActionsScreen(
    onNavigateBack: () -> Unit,
    viewModel: MicroActionsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var showAddDialog by remember { mutableStateOf(false) }
    var newActionText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = "Micro-acciones",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Pequeñas acciones estoicas para hoy",
                style = MaterialTheme.typography.bodyLarge,
                color = TextGrey
            )
            Spacer(modifier = Modifier.height(24.dp))
            
            // Progress
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProgressRing(
                        progress = state.progress,
                        size = 80.dp,
                        strokeWidth = 8.dp
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Column {
                        Text(
                            text = "${state.completedCount} de ${state.totalCount}",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = TextWhite
                        )
                        Text(
                            text = "acciones completadas",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextGrey
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Actions list
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.todayActions) { action ->
                    ActionItem(
                        action = action,
                        onToggle = { viewModel.toggleAction(action) },
                        onDelete = { viewModel.deleteAction(action) }
                    )
                }
            }
            
            // Add button
            FloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = Bronze,
                contentColor = DarkBackground,
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar acción")
            }
        }
    }

    // Add action dialog
    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Nueva micro-acción", color = TextWhite) },
            text = {
                OutlinedTextField(
                    value = newActionText,
                    onValueChange = { newActionText = it },
                    label = { Text("Describe tu acción") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Bronze,
                        unfocusedBorderColor = StoneLight,
                        cursorColor = Bronze
                    )
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newActionText.isNotBlank()) {
                            viewModel.addAction(newActionText, "custom")
                            newActionText = ""
                            showAddDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Bronze)
                ) {
                    Text("Agregar", color = DarkBackground)
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) {
                    Text("Cancelar", color = TextGrey)
                }
            },
            containerColor = DarkSurfaceVariant
        )
    }
}

@Composable
private fun ActionItem(
    action: com.stoicus.app.core.data.local.entity.MicroAction,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (action.completed) MindColor.copy(alpha = 0.1f) else DarkSurfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (action.completed) MindColor else StoneLight)
                    .clickable(onClick = onToggle),
                contentAlignment = Alignment.Center
            ) {
                if (action.completed) {
                    Text("✓", color = TextWhite, fontSize = 14.sp)
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = action.description,
                style = MaterialTheme.typography.bodyLarge,
                color = if (action.completed) TextGrey else TextWhite,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
