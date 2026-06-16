package com.stoicus.app.feature.evening

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoicus.app.core.data.local.entity.MoodEntry
import com.stoicus.app.core.data.local.entity.RitualSession
import com.stoicus.app.core.data.local.dao.MoodEntryDao
import com.stoicus.app.core.domain.model.Pillar
import com.stoicus.app.core.domain.model.RitualExercise
import com.stoicus.app.core.domain.usecase.GetRitualExercisesUseCase
import com.stoicus.app.core.domain.usecase.SaveRitualSessionUseCase
import com.stoicus.app.core.domain.usecase.SaveMoodEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

data class EveningRitualState(
    val currentStep: Int = 0,  // 0: mood, 1: exercise, 2: reflection
    val moodScore: Float = 5f,
    val stressLevel: Float = 5f,
    val energyLevel: Float = 5f,
    val exercises: List<RitualExercise> = emptyList(),
    val selectedExercise: RitualExercise? = null,
    val currentExerciseStep: Int = 0,
    val completedExerciseSteps: List<Int> = emptyList(),
    val reflection: String = "",
    val isComplete: Boolean = false
)

@HiltViewModel
class EveningRitualViewModel @Inject constructor(
    private val getRitualExercisesUseCase: GetRitualExercisesUseCase,
    private val saveRitualSessionUseCase: SaveRitualSessionUseCase,
    private val saveMoodEntryUseCase: SaveMoodEntryUseCase,
    private val moodEntryDao: MoodEntryDao
) : ViewModel() {

    private val _state = MutableStateFlow(EveningRitualState())
    val state: StateFlow<EveningRitualState> = _state.asStateFlow()

    private val today = LocalDate.now().toString()

    init {
        loadExercises()
    }

    private fun loadExercises() {
        viewModelScope.launch {
            val exercises = getRitualExercisesUseCase(Pillar.SOUL)
            _state.update { it.copy(exercises = exercises) }
        }
    }

    fun updateMoodScore(score: Float) {
        _state.update { it.copy(moodScore = score) }
    }

    fun updateStressLevel(level: Float) {
        _state.update { it.copy(stressLevel = level) }
    }

    fun updateEnergyLevel(level: Float) {
        _state.update { it.copy(energyLevel = level) }
    }

    fun nextStep() {
        _state.update { it.copy(currentStep = it.currentStep + 1) }
    }

    fun previousStep() {
        _state.update { it.copy(currentStep = (it.currentStep - 1).coerceAtLeast(0)) }
    }

    fun selectExercise(exercise: RitualExercise) {
        _state.update { it.copy(selectedExercise = exercise) }
    }

    fun completeExerciseStep() {
        _state.update { state ->
            val exercise = state.selectedExercise ?: return@update state
            val newCompleted = state.completedExerciseSteps + state.currentExerciseStep
            val nextStep = state.currentExerciseStep + 1
            val isComplete = nextStep >= exercise.steps.size

            state.copy(
                currentExerciseStep = if (isComplete) state.currentExerciseStep else nextStep,
                completedExerciseSteps = newCompleted
            )
        }
    }

    fun updateReflection(text: String) {
        _state.update { it.copy(reflection = text) }
    }

    fun completeSession() {
        viewModelScope.launch {
            // Save mood entry
            val moodEntry = MoodEntry(
                moodScore = _state.value.moodScore.toInt(),
                stressLevel = _state.value.stressLevel.toInt(),
                energyLevel = _state.value.energyLevel.toInt(),
                reflection = _state.value.reflection,
                date = today
            )
            saveMoodEntryUseCase(moodEntry)

            // Save ritual session
            _state.value.selectedExercise?.let { exercise ->
                val session = RitualSession(
                    pillar = exercise.pillar.name,
                    exerciseType = exercise.id,
                    durationMinutes = exercise.durationMinutes,
                    completed = true,
                    reflection = _state.value.reflection
                )
                saveRitualSessionUseCase(session)
            }

            _state.update { it.copy(isComplete = true) }
        }
    }

    fun reset() {
        _state.update {
            EveningRitualState(exercises = it.exercises)
        }
    }
}
