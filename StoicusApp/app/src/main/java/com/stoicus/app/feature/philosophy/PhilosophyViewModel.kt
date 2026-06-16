package com.stoicus.app.feature.philosophy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoicus.app.core.data.local.entity.PhilosophyQuote
import com.stoicus.app.core.data.local.dao.PhilosophyQuoteDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PhilosophyState(
    val quotes: List<PhilosophyQuote> = emptyList(),
    val selectedQuote: PhilosophyQuote? = null,
    val selectedPillar: String? = null
)

@HiltViewModel
class PhilosophyViewModel @Inject constructor(
    private val quoteDao: PhilosophyQuoteDao
) : ViewModel() {

    private val _state = MutableStateFlow(PhilosophyState())
    val state: StateFlow<PhilosophyState> = _state.asStateFlow()

    init {
        loadQuotes()
    }

    private fun loadQuotes() {
        viewModelScope.launch {
            quoteDao.getFavoriteQuotes().collect { quotes ->
                if (quotes.isEmpty()) {
                    // Insert default quotes if none exist
                    insertDefaultQuotes()
                } else {
                    _state.update { it.copy(quotes = quotes) }
                }
            }
        }
    }

    private suspend fun insertDefaultQuotes() {
        val defaultQuotes = listOf(
            PhilosophyQuote(author = "Marco Aurelio", text = "La felicidad de tu vida depende de la calidad de tus pensamientos.", pillar = "MIND", favorited = true),
            PhilosophyQuote(author = "Epicteto", text = "No son las cosas las que nos perturban, sino los juicios que hacemos sobre ellas.", pillar = "SOUL", favorited = true),
            PhilosophyQuote(author = "Séneca", text = "No es que tengamos poco tiempo, es que desperdiciamos mucho.", pillar = "MIND", favorited = true),
            PhilosophyQuote(author = "Marco Aurelio", text = "El impedimento para la acción avanza la acción. Lo que se interpone se convierte en el camino.", pillar = "BODY", favorited = true),
            PhilosophyQuote(author = "Epicteto", text = "Primero dite a ti mismo lo que quieres ser, y luego haz lo que tengas que hacer.", pillar = "MIND", favorited = true),
            PhilosophyQuote(author = "Séneca", text = "El hombre más rico es el que se contenta con poco.", pillar = "SOUL", favorited = true),
            PhilosophyQuote(author = "Marco Aurelio", text = "Cuando te levantes por la mañana, piensa en el precioso privilegio de estar vivo.", pillar = "MIND", favorited = true),
            PhilosophyQuote(author = "Epicteto", text = "No pretendas que las cosas sucedan como tú quieres. Desea, más bien, que sucedan tal como suceden.", pillar = "SOUL", favorited = true),
            PhilosophyQuote(author = "Séneca", text = "La verdadera felicidad consiste en disfrutar el presente.", pillar = "MIND", favorited = true),
            PhilosophyQuote(author = "Marco Aurelio", text = "La mejor venganza es no ser como tu agresor.", pillar = "SOUL", favorited = true)
        )
        defaultQuotes.forEach { quoteDao.insertQuote(it) }
    }

    fun filterByPillar(pillar: String?) {
        _state.update { it.copy(selectedPillar = pillar) }
    }

    fun toggleFavorite(quote: PhilosophyQuote) {
        viewModelScope.launch {
            quoteDao.updateQuote(quote.copy(favorited = !quote.favorited))
        }
    }
}
