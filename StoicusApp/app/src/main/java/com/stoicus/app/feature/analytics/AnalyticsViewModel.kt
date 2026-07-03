package com.stoicus.app.feature.analytics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoicus.app.core.domain.model.AnalyticsData
import com.stoicus.app.core.domain.usecase.GetAnalyticsDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AnalyticsState(
    val data: AnalyticsData = AnalyticsData(),
    val isLoading: Boolean = true,
    val selectedTimeRange: TimeRange = TimeRange.WEEK
)

enum class TimeRange(val displayName: String) {
    WEEK("7 días"),
    MONTH("30 días"),
    ALL("Todo")
}

@HiltViewModel
class AnalyticsViewModel @Inject constructor(
    private val getAnalyticsDataUseCase: GetAnalyticsDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AnalyticsState())
    val state: StateFlow<AnalyticsState> = _state.asStateFlow()

    init {
        loadAnalytics()
    }

    private fun loadAnalytics() {
        viewModelScope.launch {
            getAnalyticsDataUseCase().collect { data ->
                _state.update {
                    it.copy(data = data, isLoading = false)
                }
            }
        }
    }

    fun selectTimeRange(range: TimeRange) {
        _state.update { it.copy(selectedTimeRange = range) }
    }
}
