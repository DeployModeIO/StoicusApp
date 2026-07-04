package com.stoicus.app.feature.morning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoicus.app.core.data.local.entity.StreakRecord
import com.stoicus.app.core.domain.model.Pillar
import com.stoicus.app.core.domain.usecase.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

data class HomeState(
    val userName: String = "",
    val selectedPillars: List<Pillar> = emptyList(),
    val currentStreak: Int = 0,
    val todayMorningCompleted: Boolean = false,
    val todayEveningCompleted: Boolean = false,
    val todayMicroActions: Int = 0,
    val greeting: String = "",
    val dateLine: String = ""
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            getUserProfileUseCase().collect { profile ->
                profile?.let {
                    val pillars = it.selectedPillars.mapNotNull { name ->
                        Pillar.entries.find { p -> p.name == name }
                    }
                    _state.update { state ->
                        state.copy(
                            userName = it.name,
                            selectedPillars = pillars,
                            greeting = getGreeting(),
                            dateLine = getDateLine()
                        )
                    }
                }
            }
        }
    }

    private fun getGreeting(): String {
        val hour = java.time.LocalTime.now().hour
        return when {
            hour < 12 -> "Buenos días"
            hour < 18 -> "Buenas tardes"
            else -> "Buenas noches"
        }
    }

    private fun getDateLine(): String {
        val date = LocalDate.now()
        val day = date.dayOfMonth
        val month = when (date.monthValue) {
            1 -> "Enero"; 2 -> "Febrero"; 3 -> "Marzo"; 4 -> "Abril"
            5 -> "Mayo"; 6 -> "Junio"; 7 -> "Julio"; 8 -> "Agosto"
            9 -> "Septiembre"; 10 -> "Octubre"; 11 -> "Noviembre"; 12 -> "Diciembre"
            else -> ""
        }
        return "$day de $month"
    }
}
