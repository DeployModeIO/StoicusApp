package com.stoicus.app.feature.micro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoicus.app.core.data.local.entity.MicroAction
import com.stoicus.app.core.data.local.dao.MicroActionDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

data class MicroActionsState(
    val todayActions: List<MicroAction> = emptyList(),
    val completedCount: Int = 0,
    val totalCount: Int = 0,
    val progress: Float = 0f
)

@HiltViewModel
class MicroActionsViewModel @Inject constructor(
    private val microActionDao: MicroActionDao
) : ViewModel() {

    private val _state = MutableStateFlow(MicroActionsState())
    val state: StateFlow<MicroActionsState> = _state.asStateFlow()

    private val today = LocalDate.now().toString()

    init {
        loadActions()
    }

    private fun loadActions() {
        viewModelScope.launch {
            microActionDao.getActionsByDate(today).collect { actions ->
                _state.update {
                    it.copy(
                        todayActions = actions,
                        completedCount = actions.count { a -> a.completed },
                        totalCount = actions.size,
                        progress = if (actions.isEmpty()) 0f 
                                   else actions.count { a -> a.completed }.toFloat() / actions.size
                    )
                }
            }
        }
    }

    fun addAction(description: String, type: String) {
        viewModelScope.launch {
            val action = MicroAction(
                actionType = type,
                description = description,
                date = today
            )
            microActionDao.insertAction(action)
        }
    }

    fun toggleAction(action: MicroAction) {
        viewModelScope.launch {
            val updated = action.copy(
                completed = !action.completed,
                completedAt = if (!action.completed) System.currentTimeMillis() else null
            )
            microActionDao.updateAction(updated)
        }
    }

    fun deleteAction(action: MicroAction) {
        viewModelScope.launch {
            microActionDao.deleteAction(action)
        }
    }
}
