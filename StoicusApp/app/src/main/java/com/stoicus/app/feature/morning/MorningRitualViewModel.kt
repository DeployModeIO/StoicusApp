package com.stoicus.app.feature.morning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoicus.app.core.data.local.entity.RitualSession
import com.stoicus.app.core.domain.model.Pillar
import com.stoicus.app.core.domain.model.RitualExercise
import com.stoicus.app.core.domain.usecase.GetRitualExercisesUseCase
import com.stoicus.app.core.domain.usecase.SaveRitualSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

data class MorningRitualState(
    val exercises: List<RitualExercise> = emptyList(),
    val currentExercise: RitualExercise? = null,
    val currentStep: Int = 0,
    val isSessionActive: Boolean = false,
    val sessionProgress: Float = 0f,
    val completedSteps: List<Int> = emptyList(),
    val reflection: String = "",
    val isSessionComplete: Boolean = false
)

@HiltViewModel
class MorningRitualViewModel @Inject constructor(
    private val getRitualExercisesUseCase: GetRitualExercisesUseCase,
    private val saveRitualSessionUseCase: SaveRitualSessionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MorningRitualState())
    val state: StateFlow<MorningRitualState> = _state.asStateFlow()

    init {
        loadExercises()
    }

    private fun loadExercises() {
        viewModelScope.launch {
            val exercises = getRitualExercisesUseCase(Pillar.MIND)
            _state.update { it.copy(exercises = exercises) }
        }
    }

    fun selectExercise(exercise: RitualExercise) {
        _state.update {
            it.copy(
                currentExercise = exercise,
                currentStep = 0,
                completedSteps = emptyList(),
                isSessionActive = true,
                sessionProgress = 0f
            )
        }
    }

    fun completeStep() {
        _state.update { state ->
            val exercise = state.currentExercise ?: return@update state
            val newCompletedSteps = state.completedSteps + state.currentStep
            val nextStep = state.currentStep + 1
            val isComplete = nextStep >= exercise.steps.size
            val progress = newCompletedSteps.size.toFloat() / exercise.steps.size

            state.copy(
                currentStep = if (isComplete) state.currentStep else nextStep,
                completedSteps = newCompletedSteps,
                sessionProgress = progress,
                isSessionActive = !isComplete,
                isSessionComplete = isComplete
            )
        }
    }

    fun updateReflection(text: String) {
        _state.update { it.copy(reflection = text) }
    }

    fun saveSession() {
        viewModelScope.launch {
            val exercise = _state.value.currentExercise ?: return@launch
            val session = RitualSession(
                pillar = exercise.pillar.name,
                exerciseType = exercise.id,
                durationMinutes = exercise.durationMinutes,
                completed = true,
                reflection = _state.value.reflection
            )
            saveRitualSessionUseCase(session)
            _state.update {
                it.copy(
                    isSessionComplete = false,
                    isSessionActive = false,
                    currentExercise = null,
                    currentStep = 0,
                    completedSteps = emptyList(),
                    reflection = "",
                    sessionProgress = 0f
                )
            }
        }
    }

    fun goBack() {
        _state.update {
            it.copy(
                currentExercise = null,
                currentStep = 0,
                completedSteps = emptyList(),
                isSessionActive = false,
                sessionProgress = 0f
            )
        }
    }
}
