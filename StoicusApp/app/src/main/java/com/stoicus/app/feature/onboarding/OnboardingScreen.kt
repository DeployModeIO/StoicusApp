package com.stoicus.app.feature.onboarding

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stoicus.app.core.domain.model.Pillar
import com.stoicus.app.core.ui.components.PillarCard
import com.stoicus.app.core.ui.theme.*

@Composable
fun OnboardingScreen(
    onOnboardingComplete: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isComplete) {
        if (state.isComplete) {
            onOnboardingComplete()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // Progress indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(4) { index ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(
                                if (index <= state.currentStep) Bronze
                                else StoneLight
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            AnimatedContent(
                targetState = state.currentStep,
                transitionSpec = {
                    fadeIn() + slideInHorizontally { width -> width } togetherWith
                    fadeOut() + slideOutHorizontally { width -> -width }
                },
                label = "onboarding_step"
            ) { step ->
                when (step) {
                    0 -> WelcomeStep(
                        name = state.name,
                        onNameChanged = viewModel::onNameChanged,
                        onNext = viewModel::nextStep
                    )
                    1 -> PillarSelectionStep(
                        selectedPillars = state.selectedPillars,
                        onPillarToggled = viewModel::onPillarToggled,
                        onNext = viewModel::nextStep,
                        onBack = viewModel::previousStep
                    )
                    2 -> ChallengeStep(
                        challenge = state.biggestChallenge,
                        onChallengeChanged = viewModel::onChallengeChanged,
                        onNext = viewModel::nextStep,
                        onBack = viewModel::previousStep
                    )
                    3 -> TimePreferenceStep(
                        preferredTime = state.preferredTime,
                        onTimeChanged = viewModel::onTimeChanged,
                        onComplete = viewModel::completeOnboarding,
                        onBack = viewModel::previousStep,
                        isLoading = state.isLoading
                    )
                }
            }
        }
    }
}

@Composable
private fun WelcomeStep(
    name: String,
    onNameChanged: (String) -> Unit,
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🏛️",
            fontSize = 64.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Bienvenido a Stoicus",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = TextWhite,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Tu entrenador personal estoico",
            style = MaterialTheme.typography.bodyLarge,
            color = TextGrey,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(48.dp))
        
        OutlinedTextField(
            value = name,
            onValueChange = onNameChanged,
            label = { Text("¿Cómo te llamas?") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Bronze,
                unfocusedBorderColor = StoneLight,
                focusedLabelColor = Bronze,
                cursorColor = Bronze
            ),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Bronze
            ),
            enabled = name.isNotBlank()
        ) {
            Text(
                text = "Comenzar",
                style = MaterialTheme.typography.titleMedium,
                color = DarkBackground
            )
        }
    }
}

@Composable
private fun PillarSelectionStep(
    selectedPillars: Set<Pillar>,
    onPillarToggled: (Pillar) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Elige tus pilares",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = TextWhite
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Selecciona las áreas que quieres fortalecer",
            style = MaterialTheme.typography.bodyLarge,
            color = TextGrey
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(Pillar.entries.size) { index ->
                val pillar = Pillar.entries[index]
                PillarCard(
                    pillar = pillar,
                    isSelected = pillar in selectedPillars,
                    onClick = { onPillarToggled(pillar) }
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
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
                enabled = selectedPillars.isNotEmpty()
            ) {
                Text("Siguiente", color = DarkBackground)
            }
        }
    }
}

@Composable
private fun ChallengeStep(
    challenge: String,
    onChallengeChanged: (String) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val challenges = listOf(
        "Procrastinación",
        "Estrés y ansiedad",
        "Falta de disciplina",
        "Relaciones difíciles",
        "Miedo al fracaso",
        "Falta de propósito"
    )
    
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "¿Cuál es tu mayor desafío?",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = TextWhite
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Selecciona o escribe tu mayor obstáculo",
            style = MaterialTheme.typography.bodyLarge,
            color = TextGrey
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        challenges.forEach { challengeOption ->
            val isSelected = challenge == challengeOption
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable { onChallengeChanged(challengeOption) },
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) Bronze.copy(alpha = 0.2f) else DarkSurfaceVariant
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) Bronze else StoneLight)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = challengeOption,
                        color = TextWhite,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = if (challenge !in challenges) challenge else "",
            onValueChange = onChallengeChanged,
            label = { Text("O escribe el tuyo") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Bronze,
                unfocusedBorderColor = StoneLight,
                cursorColor = Bronze
            )
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
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
                enabled = challenge.isNotBlank()
            ) {
                Text("Siguiente", color = DarkBackground)
            }
        }
    }
}

@Composable
private fun TimePreferenceStep(
    preferredTime: String,
    onTimeChanged: (String) -> Unit,
    onComplete: () -> Unit,
    onBack: () -> Unit,
    isLoading: Boolean
) {
    val times = listOf(
        "morning" to "🌅 Mañana (6:00 - 9:00)",
        "midday" to "☀️ Mediodía (12:00 - 14:00)",
        "evening" to "🌙 Noche (19:00 - 22:00)"
    )
    
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "¿Cuándo entrenas mejor?",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = TextWhite,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Personalizaremos tus recordatorios",
            style = MaterialTheme.typography.bodyLarge,
            color = TextGrey,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        times.forEach { (time, label) ->
            val isSelected = preferredTime == time
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onTimeChanged(time) },
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) Bronze.copy(alpha = 0.2f) else DarkSurfaceVariant
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) Bronze else StoneLight)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = label,
                        color = TextWhite,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
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
                colors = ButtonDefaults.buttonColors(containerColor = Bronze),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = DarkBackground
                    )
                } else {
                    Text("Comenzar", color = DarkBackground)
                }
            }
        }
    }
}