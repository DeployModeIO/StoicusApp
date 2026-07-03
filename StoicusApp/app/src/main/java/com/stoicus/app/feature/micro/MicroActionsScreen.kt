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
import androidx.compose.ui.graphics.Color
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
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    val filteredActions = if (selectedCategory != null) {
        state.todayActions.filter { it.actionType == selectedCategory }
    } else {
        state.todayActions
    }

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
                text = "📋 Tareas Diarias",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Mejora tu mente, cuerpo y alma",
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
                            text = "tareas completadas hoy",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextGrey
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Category filter chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedCategory == null,
                    onClick = { selectedCategory = null },
                    label = { Text("Todas") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Bronze.copy(alpha = 0.2f),
                        selectedLabelColor = Bronze
                    )
                )
                FilterChip(
                    selected = selectedCategory == "mind",
                    onClick = { selectedCategory = "mind" },
                    label = { Text("🧠 Mente") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MindColor.copy(alpha = 0.2f),
                        selectedLabelColor = MindColor
                    )
                )
                FilterChip(
                    selected = selectedCategory == "body",
                    onClick = { selectedCategory = "body" },
                    label = { Text("💪 Cuerpo") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = BodyColor.copy(alpha = 0.2f),
                        selectedLabelColor = BodyColor
                    )
                )
                FilterChip(
                    selected = selectedCategory == "soul",
                    onClick = { selectedCategory = "soul" },
                    label = { Text("🛡️ Alma") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = SoulColor.copy(alpha = 0.2f),
                        selectedLabelColor = SoulColor
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Section headers for each category when showing all
            if (selectedCategory == null) {
                val mindActions = state.todayActions.filter { it.actionType == "mind" }
                val bodyActions = state.todayActions.filter { it.actionType == "body" }
                val soulActions = state.todayActions.filter { it.actionType == "soul" }

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (mindActions.isNotEmpty()) {
                        item {
                            SectionHeader(title = "🧠 Mente", count = mindActions.count { it.completed }, total = mindActions.size)
                        }
                        items(mindActions) { action ->
                            ActionItem(
                                action = action,
                                color = MindColor,
                                onToggle = { viewModel.toggleAction(action) },
                                onDelete = { viewModel.deleteAction(action) }
                            )
                        }
                    }

                    if (bodyActions.isNotEmpty()) {
                        item {
                            SectionHeader(title = "💪 Cuerpo", count = bodyActions.count { it.completed }, total = bodyActions.size)
                        }
                        items(bodyActions) { action ->
                            ActionItem(
                                action = action,
                                color = BodyColor,
                                onToggle = { viewModel.toggleAction(action) },
                                onDelete = { viewModel.deleteAction(action) }
                            )
                        }
                    }

                    if (soulActions.isNotEmpty()) {
                        item {
                            SectionHeader(title = "🛡️ Alma", count = soulActions.count { it.completed }, total = soulActions.size)
                        }
                        items(soulActions) { action ->
                            ActionItem(
                                action = action,
                                color = SoulColor,
                                onToggle = { viewModel.toggleAction(action) },
                                onDelete = { viewModel.deleteAction(action) }
                            )
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            } else {
                // Show filtered list
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredActions) { action ->
                        val color = when (action.actionType) {
                            "mind" -> MindColor
                            "body" -> BodyColor
                            else -> SoulColor
                        }
                        ActionItem(
                            action = action,
                            color = color,
                            onToggle = { viewModel.toggleAction(action) },
                            onDelete = { viewModel.deleteAction(action) }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }

            // Add button
            FloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = Bronze,
                contentColor = DarkBackground,
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar tarea")
            }
        }
    }

    // Add action dialog
    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Nueva tarea diaria", color = TextWhite) },
            text = {
                Column {
                    OutlinedTextField(
                        value = newActionText,
                        onValueChange = { newActionText = it },
                        label = { Text("Describe tu tarea") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Bronze,
                            unfocusedBorderColor = StoneLight,
                            cursorColor = Bronze
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
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
private fun SectionHeader(
    title: String,
    count: Int,
    total: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = TextWhite
        )
        Text(
            text = "$count/$total completadas",
            style = MaterialTheme.typography.bodyMedium,
            color = TextGrey
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun ActionItem(
    action: com.stoicus.app.core.data.local.entity.MicroAction,
    color: Color,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (action.completed) color.copy(alpha = 0.1f) else DarkSurfaceVariant
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
                    .background(if (action.completed) color else StoneLight)
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
