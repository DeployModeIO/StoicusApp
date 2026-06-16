package com.stoicus.app.feature.morning

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stoicus.app.core.ui.components.StepIndicator
import com.stoicus.app.core.ui.theme.*

@Composable
fun MorningRitualScreen(
    onNavigateBack: () -> Unit,
    viewModel: MorningRitualViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isSessionComplete) {
        if (state.isSessionComplete) {
            // Show completion
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        if (state.isSessionActive && state.currentExercise != null) {
            // Active session view
            ActiveSessionView(
                exercise = state.currentExercise!!,
                currentStep = state.currentStep,
                completedSteps = state.completedSteps,
                progress = state.sessionProgress,
                reflection = state.reflection,
                onReflectionChanged = viewModel::updateReflection,
                onCompleteStep = viewModel::completeStep,
                onCompleteSession = viewModel::saveSession,
                onBack = viewModel::goBack
            )
        } else if (state.isSessionComplete) {
            // Completion view
            CompletionView(
                onBack = {
                    viewModel.goBack()
                    onNavigateBack()
                }
            )
        } else {
            // Exercise selection view
            ExerciseSelectionView(
                exercises = state.exercises,
                onSelectExercise = viewModel::selectExercise,
                onBack = onNavigateBack
            )
        }
    }
}

@Composable
private fun ExerciseSelectionView(
    exercises: List<com.stoicus.app.core.domain.model.RitualExercise>,
    onSelectExercise: (com.stoicus.app.core.domain.model.RitualExercise) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "Ritual Matutino",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = TextWhite
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Elige tu ejercicio para hoy",
            style = MaterialTheme.typography.bodyLarge,
            color = TextGrey
        )
        Spacer(modifier = Modifier.height(24.dp))
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(exercises) { exercise ->
                ExerciseCard(
                    exercise = exercise,
                    onClick = { onSelectExercise(exercise) }
                )
            }
        }
    }
}

@Composable
private fun ExerciseCard(
    exercise: com.stoicus.app.core.domain.model.RitualExercise,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = exercise.philosopher,
                    style = MaterialTheme.typography.bodySmall,
                    color = Bronze
                )
                Text(
                    text = "${exercise.durationMinutes} min",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextGrey
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = exercise.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = exercise.description,
                style = MaterialTheme.typography.bodyMedium,
                color = TextGrey
            )
        }
    }
}

@Composable
private fun ActiveSessionView(
    exercise: com.stoicus.app.core.domain.model.RitualExercise,
    currentStep: Int,
    completedSteps: List<Int>,
    progress: Float,
    reflection: String,
    onReflectionChanged: (String) -> Unit,
    onCompleteStep: () -> Unit,
    onCompleteSession: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        
        // Progress bar
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = Bronze,
            trackColor = StoneLight,
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = exercise.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = TextWhite
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Paso ${currentStep + 1} de ${exercise.steps.size}",
            style = MaterialTheme.typography.bodyMedium,
            color = TextGrey
        )
        Spacer(modifier = Modifier.height(24.dp))
        
        // Current step
        if (currentStep < exercise.steps.size) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Bronze.copy(alpha = 0.1f))
            ) {
                Text(
                    text = exercise.steps[currentStep],
                    modifier = Modifier.padding(20.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextWhite
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Steps list
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(exercise.steps) { index, step ->
                StepIndicator(
                    stepNumber = index + 1,
                    title = step,
                    isCompleted = index in completedSteps,
                    isActive = index == currentStep,
                    onClick = { }
                )
            }
        }
        
        // Reflection (shown on last step)
        if (currentStep == exercise.steps.size - 1) {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = reflection,
                onValueChange = onReflectionChanged,
                label = { Text("Reflexión final") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Bronze,
                    unfocusedBorderColor = StoneLight,
                    cursorColor = Bronze
                )
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Action buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Salir")
            }
            Button(
                onClick = if (currentStep == exercise.steps.size - 1) onCompleteSession else onCompleteStep,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Bronze)
            ) {
                Text(
                    if (currentStep == exercise.steps.size - 1) "Completar" else "Siguiente",
                    color = DarkBackground
                )
            }
        }
    }
}

@Composable
private fun CompletionView(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "🎉", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "¡Ritual completado!",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = TextWhite,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Has fortalecido tu mente esta mañana.\nLa práctica constante forja la virtud.",
            style = MaterialTheme.typography.bodyLarge,
            color = TextGrey,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onBack,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Bronze)
        ) {
            Text("Continuar", color = DarkBackground)
        }
    }
}
