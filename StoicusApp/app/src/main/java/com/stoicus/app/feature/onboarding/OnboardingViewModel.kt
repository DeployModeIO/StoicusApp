package com.stoicus.app.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoicus.app.core.data.local.entity.UserProfile
import com.stoicus.app.core.domain.model.Pillar
import com.stoicus.app.core.domain.usecase.GetUserProfileUseCase
import com.stoicus.app.core.domain.usecase.SaveUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OnboardingState(
    val currentStep: Int = 0,
    val name: String = "",
    val selectedPillars: Set<Pillar> = emptySet(),
    val biggestChallenge: String = "",
    val preferredTime: String = "morning",
    val isLoading: Boolean = false,
    val isComplete: Boolean = false
)

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val saveUserProfileUseCase: SaveUserProfileUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(OnboardingState())
    val state: StateFlow<OnboardingState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getUserProfileUseCase().first()?.let { profile ->
                if (profile.onboardingCompleted) {
                    _state.update { it.copy(isComplete = true) }
                }
            }
        }
    }

    fun onNameChanged(name: String) {
        _state.update { it.copy(name = name) }
    }

    fun onPillarToggled(pillar: Pillar) {
        _state.update { state ->
            val newPillars = if (pillar in state.selectedPillars) {
                state.selectedPillars - pillar
            } else {
                state.selectedPillars + pillar
            }
            state.copy(selectedPillars = newPillars)
        }
    }

    fun onChallengeChanged(challenge: String) {
        _state.update { it.copy(biggestChallenge = challenge) }
    }

    fun onTimeChanged(time: String) {
        _state.update { it.copy(preferredTime = time) }
    }

    fun nextStep() {
        _state.update { it.copy(currentStep = it.currentStep + 1) }
    }

    fun previousStep() {
        _state.update { it.copy(currentStep = (it.currentStep - 1).coerceAtLeast(0)) }
    }

    fun completeOnboarding() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            val profile = UserProfile(
                name = _state.value.name,
                selectedPillars = _state.value.selectedPillars.map { it.name },
                biggestChallenge = _state.value.biggestChallenge,
                preferredTime = _state.value.preferredTime,
                onboardingCompleted = true
            )
            
            saveUserProfileUseCase(profile)
            _state.update { it.copy(isLoading = false, isComplete = true) }
        }
    }
}