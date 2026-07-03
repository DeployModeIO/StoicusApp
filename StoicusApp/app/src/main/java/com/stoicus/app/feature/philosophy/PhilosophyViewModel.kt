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
            // MARCO AURELIO - MIND
            PhilosophyQuote(author = "Marco Aurelio", text = "La felicidad de tu vida depende de la calidad de tus pensamientos.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_1"),
            PhilosophyQuote(author = "Marco Aurelio", text = "Cuando te levantes por la mañana, piensa en el precioso privilegio de estar vivo: respirar, pensar, disfrutar, amar.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_2"),
            PhilosophyQuote(author = "Marco Aurelio", text = "Tienes poder sobre tu mente, no sobre los eventos externos. Date cuenta de esto y encontrarás la fuerza.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_3"),
            PhilosophyQuote(author = "Marco Aurelio", text = "La mejor venganza es no ser como tu agresor.", pillar = "SOUL", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_4"),
            PhilosophyQuote(author = "Marco Aurelio", text = "El impedimento para la acción avanza la acción. Lo que se interpone se convierte en el camino.", pillar = "BODY", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_5"),
            PhilosophyQuote(author = "Marco Aurelio", text = "No digas más lo que un buen hombre debe ser. Sé uno.", pillar = "BODY", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_6"),
            PhilosophyQuote(author = "Marco Aurelio", text = "Todo lo que escuchamos es una opinión, no un hecho. Todo lo que vemos es una perspectiva, no la verdad.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_7"),
            PhilosophyQuote(author = "Marco Aurelio", text = "La ira siempre nos hace más daño que el daño que la provoca.", pillar = "SOUL", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_8"),
            PhilosophyQuote(author = "Marco Aurelio", text = "Si estás angustiado por algo externo, el dolor no se debe a la cosa en sí, sino a tu estimación de ella; y esto tienes el poder de revocarlo en cualquier momento.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_9"),
            PhilosophyQuote(author = "Marco Aurelio", text = "El alma se tiñe del color de sus pensamientos.", pillar = "SOUL", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_10"),
            
            // EPICTETO - SOUL & MIND
            PhilosophyQuote(author = "Epicteto", text = "No son las cosas las que nos perturban, sino los juicios que hacemos sobre ellas.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "epictetus_1"),
            PhilosophyQuote(author = "Epicteto", text = "Primero dite a ti mismo lo que quieres ser, y luego haz lo que tengas que hacer.", pillar = "MIND", favorited = true, era = "Griego", imageUrl = "epictetus_2"),
            PhilosophyQuote(author = "Epicteto", text = "No pretendas que las cosas sucedan como tú quieres. Desea, más bien, que sucedan tal como suceden, y serás feliz.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "epictetus_3"),
            PhilosophyQuote(author = "Epicteto", text = "Solo los educados son libres.", pillar = "MIND", favorited = true, era = "Griego", imageUrl = "epictetus_4"),
            PhilosophyQuote(author = "Epicteto", text = "Ningún hombre es libre si no es dueño de sí mismo.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "epictetus_5"),
            PhilosophyQuote(author = "Epicteto", text = "Es imposible que un hombre aprenda lo que cree que ya sabe.", pillar = "MIND", favorited = true, era = "Griego", imageUrl = "epictetus_6"),
            PhilosophyQuote(author = "Epicteto", text = "La libertad no es el tener todo lo que deseas, sino aprender a desear lo que tienes.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "epictetus_7"),
            PhilosophyQuote(author = "Epicteto", text = "Si quieres mejorar, conformate con ser considerado tonto y estúpido en lo que respecta a las cosas externas.", pillar = "BODY", favorited = true, era = "Griego", imageUrl = "epictetus_8"),
            PhilosophyQuote(author = "Epicteto", text = "No expliques tu filosofía. Encárnala.", pillar = "BODY", favorited = true, era = "Griego", imageUrl = "epictetus_9"),
            PhilosophyQuote(author = "Epicteto", text = "El hombre que teme a la muerte no será libre, pues todo aquel que teme perder algo, no se dará cuenta de que no le pertenece.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "epictetus_10"),
            
            // SÉNECA - MIND & SOUL
            PhilosophyQuote(author = "Séneca", text = "No es que tengamos poco tiempo, es que desperdiciamos mucho.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "seneca_1"),
            PhilosophyQuote(author = "Séneca", text = "El hombre más rico es el que se contenta con poco.", pillar = "SOUL", favorited = true, era = "Romano", imageUrl = "seneca_2"),
            PhilosophyQuote(author = "Séneca", text = "La verdadera felicidad consiste en disfrutar el presente sin depender ansiosamente del futuro.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "seneca_3"),
            PhilosophyQuote(author = "Séneca", text = "Sufrimos más a menudo en la imaginación que en la realidad.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "seneca_4"),
            PhilosophyQuote(author = "Séneca", text = "La suerte no es más que la preparación encontrándose con la oportunidad.", pillar = "BODY", favorited = true, era = "Romano", imageUrl = "seneca_5"),
            PhilosophyQuote(author = "Séneca", text = "Un hombre nunca debe avergonzarse de admitir que se ha equivocado, lo cual significa decir que hoy es más sabio que ayer.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "seneca_6"),
            PhilosophyQuote(author = "Séneca", text = "El camino de la vida es largo y tortuoso para quien no tiene un propósito claro.", pillar = "SOUL", favorited = true, era = "Romano", imageUrl = "seneca_7"),
            PhilosophyQuote(author = "Séneca", text = "La ira es un ácido que puede hacer más daño al vaso en el que se almacena que a cualquier cosa sobre la que se vierta.", pillar = "SOUL", favorited = true, era = "Romano", imageUrl = "seneca_8"),
            PhilosophyQuote(author = "Séneca", text = "Comienza a vivir y cuenta cada día separado como una vida separada.", pillar = "BODY", favorited = true, era = "Romano", imageUrl = "seneca_9"),
            PhilosophyQuote(author = "Séneca", text = "La filosofía no es un truco para entretener al público con palabras ingeniosas. Se trata de vivir, no de hablar.", pillar = "BODY", favorited = true, era = "Romano", imageUrl = "seneca_10"),
            
            // SPINOZA - MIND & SOUL
            PhilosophyQuote(author = "Baruch Spinoza", text = "La paz mental no es el resultado de la satisfacción de los deseos, sino de la liberación de ellos.", pillar = "SOUL", favorited = true, era = "Neerlandés", imageUrl = "spinoza_1"),
            PhilosophyQuote(author = "Baruch Spinoza", text = "El hombre libre en nada piensa menos que en la muerte, y su sabiduría es una meditación no de la muerte sino de la vida.", pillar = "MIND", favorited = true, era = "Neerlandés", imageUrl = "spinoza_2"),
            PhilosophyQuote(author = "Baruch Spinoza", text = "La emoción no puede ser restringida ni eliminada por la razón, sino solo por otra emoción más fuerte.", pillar = "SOUL", favorited = true, era = "Neerlandés", imageUrl = "spinoza_3"),
            PhilosophyQuote(author = "Baruch Spinoza", text = "La virtud es el poder de Dios en nosotros.", pillar = "BODY", favorited = true, era = "Neerlandés", imageUrl = "spinoza_4"),
            PhilosophyQuote(author = "Baruch Spinoza", text = "Cuanto más entendemos las causas naturales, más grande es nuestro amor hacia Dios.", pillar = "MIND", favorited = true, era = "Neerlandés", imageUrl = "spinoza_5"),
            PhilosophyQuote(author = "Baruch Spinoza", text = "El miedo no puede ser superado por la razón, sino solo por el coraje.", pillar = "SOUL", favorited = true, era = "Neerlandés", imageUrl = "spinoza_6"),
            PhilosophyQuote(author = "Baruch Spinoza", text = "La felicidad no es una recompensa por la virtud, sino la virtud misma.", pillar = "BODY", favorited = true, era = "Neerlandés", imageUrl = "spinoza_7"),
            PhilosophyQuote(author = "Baruch Spinoza", text = "Todo lo excelente es tan difícil como raro.", pillar = "MIND", favorited = true, era = "Neerlandés", imageUrl = "spinoza_8"),
            PhilosophyQuote(author = "Baruch Spinoza", text = "La mente desea entender las cosas por necesidad, no por contingencia.", pillar = "MIND", favorited = true, era = "Neerlandés", imageUrl = "spinoza_9"),
            PhilosophyQuote(author = "Baruch Spinoza", text = "La libertad es la conciencia de la necesidad.", pillar = "SOUL", favorited = true, era = "Neerlandés", imageUrl = "spinoza_10")
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
