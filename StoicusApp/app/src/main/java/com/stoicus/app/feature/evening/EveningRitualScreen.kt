package com.stoicus.app.feature.evening

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stoicus.app.core.ui.components.MoodSlider
import com.stoicus.app.core.ui.components.StepIndicator
import com.stoicus.app.core.ui.theme.*

@Composable
fun EveningRitualScreen(
    onNavigateBack: () -> Unit,
    viewModel: EveningRitualViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        when (state.currentStep) {
            0 -> MoodCheckInView(
                moodScore = state.moodScore,
                stressLevel = state.stressLevel,
                energyLevel = state.energyLevel,
                onMoodChanged = viewModel::updateMoodScore,
                onStressChanged = viewModel::updateStressLevel,
                onEnergyChanged = viewModel::updateEnergyLevel,
                onNext = viewModel::nextStep
            )
            1 -> ExerciseSelectionView(
                exercises = state.exercises,
                selectedExercise = state.selectedExercise,
                onSelectExercise = viewModel::selectExercise,
                onNext = viewModel::nextStep,
                onBack = viewModel::previousStep
            )
            2 -> ReflectionView(
                reflection = state.reflection,
                onReflectionChanged = viewModel::updateReflection,
                onComplete = viewModel::completeSession,
                onBack = viewModel::previousStep
            )
        }

        // Completion overlay
        if (state.isComplete) {
            CompletionOverlay(
                onDismiss = {
                    viewModel.reset()
                    onNavigateBack()
                }
            )
        }
    }
}

@Composable
private fun MoodCheckInView(
    moodScore: Float,
    stressLevel: Float,
    energyLevel: Float,
    onMoodChanged: (Float) -> Unit,
    onStressChanged: (Float) -> Unit,
    onEnergyChanged: (Float) -> Unit,
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "🌙 Ritual Nocturno",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = TextWhite
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "¿Cómo te sientes hoy?",
            style = MaterialTheme.typography.bodyLarge,
            color = TextGrey
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        MoodSlider(
            label = "Ánimo",
            value = moodScore,
            onValueChange = onMoodChanged,
            emoji = "😊"
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        MoodSlider(
            label = "Estrés",
            value = stressLevel,
            onValueChange = onStressChanged,
            emoji = "😰"
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        MoodSlider(
            label = "Energía",
            value = energyLevel,
            onValueChange = onEnergyChanged,
            emoji = "⚡"
        )
        
        Spacer(modifier = Modifier.weight(1f))
        
        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Bronze)
        ) {
            Text("Continuar", color = DarkBackground)
        }
    }
}

@Composable
private fun ExerciseSelectionView(
    exercises: List<com.stoicus.app.core.domain.model.RitualExercise>,
    selectedExercise: com.stoicus.app.core.domain.model.RitualExercise?,
    onSelectExercise: (com.stoicus.app.core.domain.model.RitualExercise) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "Elige tu ejercicio",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = TextWhite
        )
        Spacer(modifier = Modifier.height(24.dp))
        
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(exercises) { exercise ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectExercise(exercise) },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (selectedExercise?.id == exercise.id) 
                            Bronze.copy(alpha = 0.2f) else DarkSurfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = exercise.philosopher,
                            style = MaterialTheme.typography.bodySmall,
                            color = Bronze
                        )
                        Text(
                            text = exercise.title,
                            style = MaterialTheme.typography.titleLarge,
                            color = TextWhite
                        )
                        Text(
                            text = exercise.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextGrey
                        )
                    }
                }
            }
        }
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Atrás")
            }
            Button(
                onClick = onNext,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Bronze),
                enabled = selectedExercise != null
            ) {
                Text("Siguiente", color = DarkBackground)
            }
        }
    }
}

@Composable
private fun ReflectionView(
    reflection: String,
    onReflectionChanged: (String) -> Unit,
    onComplete: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "Reflexión del día",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = TextWhite
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "¿Qué hiciste bien? ¿Qué puedes mejorar?",
            style = MaterialTheme.typography.bodyLarge,
            color = TextGrey
        )
        Spacer(modifier = Modifier.height(24.dp))
        
        OutlinedTextField(
            value = reflection,
            onValueChange = onReflectionChanged,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            placeholder = { Text("Escribe tu reflexión...", color = TextMuted) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Bronze,
                unfocusedBorderColor = StoneLight,
                cursorColor = Bronze
            )
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Atrás")
            }
            Button(
                onClick = onComplete,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Bronze)
            ) {
                Text("Completar", color = DarkBackground)
            }
        }
    }
}

@Composable
private fun CompletionOverlay(onDismiss: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground.copy(alpha = 0.95f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(text = "🌙", fontSize = 64.sp)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Buenas noches",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = TextWhite,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Has completado tu ritual nocturno.\nDescansa con la mente tranquila.",
                style = MaterialTheme.typography.bodyLarge,
                color = TextGrey,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = onDismiss,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Bronze)
            ) {
                Text("Dulces sueños", color = DarkBackground)
            }
        }
    }
}
