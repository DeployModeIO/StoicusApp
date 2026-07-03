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
                if (actions.isEmpty()) {
                    insertDefaultDailyTasks()
                } else {
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
    }

    private suspend fun insertDefaultDailyTasks() {
        val defaultTasks = listOf(
            // MIND - Mental Tasks
            MicroAction(actionType = "mind", description = "📖 Leer 10 minutos de Meditaciones de Marco Aurelio", date = today),
            MicroAction(actionType = "mind", description = "🧠 Practicar la dicotomía del control: identificar qué está bajo tu control hoy", date = today),
            MicroAction(actionType = "mind", description = "✍️ Escribir 3 pensamientos negativos y reformularlos estoicamente", date = today),
            MicroAction(actionType = "mind", description = "🎯 Visualizar el día perfecto: imaginar obstáculos y respuestas virtuosas", date = today),
            MicroAction(actionType = "mind", description = "📚 Estudiar una virtud estoica (sabiduría, justicia, coraje, templanza)", date = today),
            MicroAction(actionType = "mind", description = "🤔 Reflexionar: '¿Qué haría un sabio en mi situación?'", date = today),
            
            // BODY - Physical Tasks
            MicroAction(actionType = "body", description = "💪 Ejercicio matutino: 20 minutos de entrenamiento funcional", date = today),
            MicroAction(actionType = "body", description = "🚶 Caminata consciente: 15 minutos prestando atención a cada paso", date = today),
            MicroAction(actionType = "body", description = "🧘 Practicar posturas de resistencia: mantener una posición incómoda 2 min", date = today),
            MicroAction(actionType = "body", description = "🚿 Terminar la ducha con 30 segundos de agua fría", date = today),
            MicroAction(actionType = "body", description = "🍎 Comer una comida simple y frugal (ayuno opcional)", date = today),
            MicroAction(actionType = "body", description = "😴 Dormir 7-8 horas tonight - preparar rutina nocturna", date = today),
            
            // SOUL - Spiritual/Emotional Tasks
            MicroAction(actionType = "soul", description = "🙏 Gratitud matutina: escribir 3 cosas por las que estar agradecido", date = today),
            MicroAction(actionType = "soul", description = "💝 Practicar un acto de justicia o bondad anónima", date = today),
            MicroAction(actionType = "soul", description = "🧘 Meditación de 10 minutos sobre la virtud", date = today),
            MicroAction(actionType = "soul", description = "📝 Journaling nocturno: revisar el día, ¿viví conforme a la naturaleza?", date = today),
            MicroAction(actionType = "soul", description = "🤲 Practicar la simpatía (sympatheia): recordar nuestra conexión humana", date = today),
            MicroAction(actionType = "soul", description = "🌟 Reflexionar sobre la mortalidad (memento mori) - vivir el día plenamente", date = today),
            
            // Mixed - Integration Tasks
            MicroAction(actionType = "mind", description = "🎨 Contemplar arte o naturaleza con atención plena", date = today),
            MicroAction(actionType = "body", description = "🏃 Entrenamiento de resistencia: correr o nadar 20 minutos", date = today),
            MicroAction(actionType = "soul", description = "👥 Conversación significativa: hablar de filosofía con alguien", date = today)
        )
        defaultTasks.forEach { microActionDao.insertAction(it) }
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
