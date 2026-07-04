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
    val selectedPillar: String? = null,
    val selectedAuthor: String? = null,
    val isSpeaking: Boolean = false
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
            // ==================== MARCO AURELIO (10) ====================
            PhilosophyQuote(author = "Marco Aurelio", text = "La felicidad de tu vida depende de la calidad de tus pensamientos.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_1", source = "Meditaciones"),
            PhilosophyQuote(author = "Marco Aurelio", text = "Cuando te levantes por la mañana, piensa en el precioso privilegio de estar vivo: respirar, pensar, disfrutar, amar.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_2", source = "Meditaciones"),
            PhilosophyQuote(author = "Marco Aurelio", text = "Tienes poder sobre tu mente, no sobre los eventos externos. Date cuenta de esto y encontrarás la fuerza.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_3", source = "Meditaciones"),
            PhilosophyQuote(author = "Marco Aurelio", text = "La mejor venganza es no ser como tu agresor.", pillar = "SOUL", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_4", source = "Meditaciones"),
            PhilosophyQuote(author = "Marco Aurelio", text = "El impedimento para la acción avanza la acción. Lo que se interpone se convierte en el camino.", pillar = "BODY", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_5", source = "Meditaciones"),
            PhilosophyQuote(author = "Marco Aurelio", text = "No digas más lo que un buen hombre debe ser. Sé uno.", pillar = "BODY", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_6", source = "Meditaciones"),
            PhilosophyQuote(author = "Marco Aurelio", text = "Todo lo que escuchamos es una opinión, no un hecho. Todo lo que vemos es una perspectiva, no la verdad.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_7", source = "Meditaciones"),
            PhilosophyQuote(author = "Marco Aurelio", text = "La ira siempre nos hace más daño que el daño que la provoca.", pillar = "SOUL", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_8", source = "Meditaciones"),
            PhilosophyQuote(author = "Marco Aurelio", text = "Si estás angustiado por algo externo, el dolor no se debe a la cosa en sí, sino a tu estimación de ella; y esto tienes el poder de revocarlo en cualquier momento.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_9", source = "Meditaciones"),
            PhilosophyQuote(author = "Marco Aurelio", text = "El alma se tiñe del color de sus pensamientos.", pillar = "SOUL", favorited = true, era = "Romano", imageUrl = "marcus_aurelius_10", source = "Meditaciones"),

            // ==================== EPICTETO (10) ====================
            PhilosophyQuote(author = "Epicteto", text = "No son las cosas las que nos perturban, sino los juicios que hacemos sobre ellas.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "epictetus_1", source = "El Manual"),
            PhilosophyQuote(author = "Epicteto", text = "Primero dite a ti mismo lo que quieres ser, y luego haz lo que tengas que hacer.", pillar = "MIND", favorited = true, era = "Griego", imageUrl = "epictetus_2", source = "El Manual"),
            PhilosophyQuote(author = "Epicteto", text = "No pretendas que las cosas sucedan como tú quieres. Desea, más bien, que sucedan tal como suceden, y serás feliz.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "epictetus_3", source = "El Manual"),
            PhilosophyQuote(author = "Epicteto", text = "Solo los educados son libres.", pillar = "MIND", favorited = true, era = "Griego", imageUrl = "epictetus_4", source = "Disertaciones"),
            PhilosophyQuote(author = "Epicteto", text = "Ningún hombre es libre si no es dueño de sí mismo.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "epictetus_5", source = "El Manual"),
            PhilosophyQuote(author = "Epicteto", text = "Es imposible que un hombre aprenda lo que cree que ya sabe.", pillar = "MIND", favorited = true, era = "Griego", imageUrl = "epictetus_6", source = "Disertaciones"),
            PhilosophyQuote(author = "Epicteto", text = "La libertad no es el tener todo lo que deseas, sino aprender a desear lo que tienes.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "epictetus_7", source = "El Manual"),
            PhilosophyQuote(author = "Epicteto", text = "Si quieres mejorar, conformate con ser considerado tonto y estúpido en lo que respecta a las cosas externas.", pillar = "BODY", favorited = true, era = "Griego", imageUrl = "epictetus_8", source = "El Manual"),
            PhilosophyQuote(author = "Epicteto", text = "No expliques tu filosofía. Encárnala.", pillar = "BODY", favorited = true, era = "Griego", imageUrl = "epictetus_9", source = "El Manual"),
            PhilosophyQuote(author = "Epicteto", text = "El hombre que teme a la muerte no será libre, pues todo aquel que teme perder algo, no se dará cuenta de que no le pertenece.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "epictetus_10", source = "Disertaciones"),

            // ==================== SÉNECA (10) ====================
            PhilosophyQuote(author = "Séneca", text = "No es que tengamos poco tiempo, es que desperdiciamos mucho.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "seneca_1", source = "Sobre la brevedad de la vida"),
            PhilosophyQuote(author = "Séneca", text = "El hombre más rico es el que se contenta con poco.", pillar = "SOUL", favorited = true, era = "Romano", imageUrl = "seneca_2", source = "Cartas a Lucilio"),
            PhilosophyQuote(author = "Séneca", text = "La verdadera felicidad consiste en disfrutar el presente sin depender ansiosamente del futuro.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "seneca_3", source = "Cartas a Lucilio"),
            PhilosophyQuote(author = "Séneca", text = "Sufrimos más a menudo en la imaginación que en la realidad.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "seneca_4", source = "Cartas a Lucilio"),
            PhilosophyQuote(author = "Séneca", text = "La suerte no es más que la preparación encontrándose con la oportunidad.", pillar = "BODY", favorited = true, era = "Romano", imageUrl = "seneca_5", source = "Cartas a Lucilio"),
            PhilosophyQuote(author = "Séneca", text = "Un hombre nunca debe avergonzarse de admitir que se ha equivocado, lo cual significa decir que hoy es más sabio que ayer.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "seneca_6", source = "Cartas a Lucilio"),
            PhilosophyQuote(author = "Séneca", text = "El camino de la vida es largo y tortuoso para quien no tiene un propósito claro.", pillar = "SOUL", favorited = true, era = "Romano", imageUrl = "seneca_7", source = "Cartas a Lucilio"),
            PhilosophyQuote(author = "Séneca", text = "La ira es un ácido que puede hacer más daño al vaso en el que se almacena que a cualquier cosa sobre la que se vierta.", pillar = "SOUL", favorited = true, era = "Romano", imageUrl = "seneca_8", source = "Sobre la ira"),
            PhilosophyQuote(author = "Séneca", text = "Comienza a vivir y cuenta cada día separado como una vida separada.", pillar = "BODY", favorited = true, era = "Romano", imageUrl = "seneca_9", source = "Cartas a Lucilio"),
            PhilosophyQuote(author = "Séneca", text = "La filosofía no es un truco para entretener al público con palabras ingeniosas. Se trata de vivir, no de hablar.", pillar = "BODY", favorited = true, era = "Romano", imageUrl = "seneca_10", source = "Cartas a Lucilio"),

            // ==================== SPINOZA (5) ====================
            PhilosophyQuote(author = "Baruch Spinoza", text = "La paz mental no es el resultado de la satisfacción de los deseos, sino de la liberación de ellos.", pillar = "SOUL", favorited = true, era = "Neerlandés", imageUrl = "spinoza_1", source = "Ética"),
            PhilosophyQuote(author = "Baruch Spinoza", text = "El hombre libre en nada piensa menos que en la muerte, y su sabiduría es una meditación no de la muerte sino de la vida.", pillar = "MIND", favorited = true, era = "Neerlandés", imageUrl = "spinoza_2", source = "Ética"),
            PhilosophyQuote(author = "Baruch Spinoza", text = "La emoción no puede ser restringida ni eliminada por la razón, sino solo por otra emoción más fuerte.", pillar = "SOUL", favorited = true, era = "Neerlandés", imageUrl = "spinoza_3", source = "Ética"),
            PhilosophyQuote(author = "Baruch Spinoza", text = "La virtud es el poder de Dios en nosotros.", pillar = "BODY", favorited = true, era = "Neerlandés", imageUrl = "spinoza_4", source = "Ética"),
            PhilosophyQuote(author = "Baruch Spinoza", text = "Cuanto más entendemos las causas naturales, más grande es nuestro amor hacia Dios.", pillar = "MIND", favorited = true, era = "Neerlandés", imageUrl = "spinoza_5", source = "Ética"),

            // ==================== CLEANTES (3) ====================
            PhilosophyQuote(author = "Cleanthes", text = "Acepta libremente lo que el destino te impone, pues allí donde la voluntad del destino conduce, allí debe estar la voluntad del hombre.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "cleanthes_1", source = "Himno a Zeus"),
            PhilosophyQuote(author = "Cleanthes", text = "El malo no es más que un esclavo, aunque tenga abundancia de riquezas.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "cleanthes_2", source = "Fragmentos"),
            PhilosophyQuote(author = "Cleanthes", text = "La verdad está con los dioses y nunca les abandona; los hombres la abandonan a ellos.", pillar = "MIND", favorited = true, era = "Griego", imageUrl = "cleanthes_3", source = "Fragmentos"),

            // ==================== CRISIPO (3) ====================
            PhilosophyQuote(author = "Crisipo", text = "El fin es vivir de acuerdo con la naturaleza, viviendo de manera virtuosa.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "chrysippus_1", source = "Sobre el fin"),
            PhilosophyQuote(author = "Crisipo", text = "No hay nada más inútil que el esfuerzo cuando se aplica en la dirección equivocada.", pillar = "BODY", favorited = true, era = "Griego", imageUrl = "chrysippus_2", source = "Fragmentos"),
            PhilosophyQuote(author = "Crisipo", text = "La sabiduría es el conocimiento de las cosas divinas y humanas.", pillar = "MIND", favorited = true, era = "Griego", imageUrl = "chrysippus_3", source = "Fragmentos"),

            // ==================== ZENÓN DE CITIO (5) ====================
            PhilosophyQuote(author = "Zenón de Citio", text = "Tenemos dos oídos y una boca, para escuchar el doble de lo que hablamos.", pillar = "MIND", favorited = true, era = "Griego", imageUrl = "zeno_1", source = "La República"),
            PhilosophyQuote(author = "Zenón de Citio", text = "La felicidad se alcanza no buscando el placer, sino buscando la virtud.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "zeno_2", source = "La República"),
            PhilosophyQuote(author = "Zenón de Citio", text = "El hombre se turbación de espíritu como un barco sin lastre; el hombre sabio es estable.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "zeno_3", source = "Fragmentos"),
            PhilosophyQuote(author = "Zenón de Citio", text = "La razón es la base de toda virtud, sin la cual no se hace nada rectamente.", pillar = "MIND", favorited = true, era = "Griego", imageUrl = "zeno_4", source = "Fragmentos"),
            PhilosophyQuote(author = "Zenón de Citio", text = "Well-being is realized by small steps, but is truly no small thing.", pillar = "BODY", favorited = true, era = "Griego", imageUrl = "zeno_5", source = "Fragmentos"),

            // ==================== MUSONIO RUFO (3) ====================
            PhilosophyQuote(author = "Musonio Rufo", text = "Soportar con dignidad las cosas adversas es el mayor signo de sabiduría.", pillar = "SOUL", favorited = true, era = "Romano", imageUrl = "musonius_1", source = "Disertaciones"),
            PhilosophyQuote(author = "Musonio Rufo", text = "El objetivo del filósofo es poder enfrentar con calma cualquier circunstancia.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "musonius_2", source = "Disertaciones"),
            PhilosophyQuote(author = "Musonio Rufo", text = "No seremos verdaderamente libres mientras no nos liberemos de nuestros propios deseos.", pillar = "SOUL", favorited = true, era = "Romano", imageUrl = "musonius_3", source = "Disertaciones"),

            // ==================== CICERÓN (4) ====================
            PhilosophyQuote(author = "Cicerón", text = "Mientras respiramos, debemos tener esperanza.", pillar = "SOUL", favorited = true, era = "Romano", imageUrl = "cicero_1", source = "Tusculanas"),
            PhilosophyQuote(author = "Cicerón", text = "La libertad es participación en el poder.", pillar = "BODY", favorited = true, era = "Romano", imageUrl = "cicero_2", source = "La República"),
            PhilosophyQuote(author = "Cicerón", text = "El error es la fuente de todos los males morales.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "cicero_3", source = "Tusculanas"),
            PhilosophyQuote(author = "Cicerón", text = "No hay nada más perjudicial para un hombre que un vicio doméstico, sobre todo si proviene de la pereza.", pillar = "BODY", favorited = true, era = "Romano", imageUrl = "cicero_4", source = "De Officiis"),

            // ==================== PLUTARCO (3) ====================
            PhilosophyQuote(author = "Plutarco", text = "Carácter es simply habit long continued.", pillar = "BODY", favorited = true, era = "Griego", imageUrl = "plutarch_1", source = "Moralia"),
            PhilosophyQuote(author = "Plutarco", text = "No es lo que sufres, sino cómo lo tomas, lo que hace la diferencia.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "plutarch_2", source = "Moralia"),
            PhilosophyQuote(author = "Plutarco", text = "La mente no se vacía al ser usada, como el cuerpo; se fortalece.", pillar = "MIND", favorited = true, era = "Griego", imageUrl = "plutarch_3", source = "Moralia"),

            // ==================== CATÓN EL JOVEN (2) ====================
            PhilosophyQuote(author = "Catón el Joven", text = "Considera los más pequeños de tus males como si fueran los mayores, para que nunca hagas nada de lo que te arrepientas.", pillar = "MIND", favorited = true, era = "Romano", imageUrl = "cato_1", source = "Vidas paralelas"),
            PhilosophyQuote(author = "Catón el Joven", text = "Es más difícil recuperar la libertad que mantenerla.", pillar = "BODY", favorited = true, era = "Romano", imageUrl = "cato_2", source = "Vidas paralelas"),

            // ==================== HIEROCLES (2) ====================
            PhilosophyQuote(author = "Hierocles", text = "Desde el momento en que nacemos, estamos pre-dispuestos a amar a nuestros familiares, y especialmente a nuestros padres.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "hierocles_1", source = "Elementos de Ética"),
            PhilosophyQuote(author = "Hierocles", text = "Cada uno de nosotros está rodeado por muchos círculos de relación: el primero es el alma propia, el segundo la familia, el tercero los parientes, el cuarto los amigos, y el quinto los conciudadanos.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "hierocles_2", source = "Elementos de Ética"),

            // ==================== AURELIO VÍCTOR (1) ====================
            PhilosophyQuote(author = "Aurelio Víctor", text = "La constancia en el esfuerzo es más valiosa que el esfuerzo intenso pero breve.", pillar = "BODY", favorited = true, era = "Romano", imageUrl = "victor_1", source = "De Caesaribus"),

            // ==================== PITÁGORAS (3) - precursor ====================
            PhilosophyQuote(author = "Pitágoras", text = "Educa a los niños y no será necesario castigar a los hombres.", pillar = "MIND", favorited = true, era = "Griego", imageUrl = "pythagoras_1", source = "Versos áureos"),
            PhilosophyQuote(author = "Pitágoras", text = "No hagas lo que no quieras que te hagan.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "pythagoras_2", source = "Versos áureos"),
            PhilosophyQuote(author = "Pitágoras", text = "El número es la esencia de todas las cosas.", pillar = "MIND", favorited = true, era = "Griego", imageUrl = "pythagoras_3", source = "Versos áureos"),

            // ==================== HERÁCLITO (3) - precursor ====================
            PhilosophyQuote(author = "Heráclito", text = "Ningún hombre puede cruzar el mismo río dos veces, porque ni el río ni el hombre son los mismos.", pillar = "MIND", favorited = true, era = "Griego", imageUrl = "heraclitus_1", source = "Fragmentos"),
            PhilosophyQuote(author = "Heráclito", text = "El carácter del hombre es su destino.", pillar = "SOUL", favorited = true, era = "Griego", imageUrl = "heraclitus_2", source = "Fragmentos"),
            PhilosophyQuote(author = "Heráclito", text = "La lucha es el padre de todas las cosas.", pillar = "BODY", favorited = true, era = "Griego", imageUrl = "heraclitus_3", source = "Fragmentos")
        )
        defaultQuotes.forEach { quoteDao.insertQuote(it) }
    }

    fun filterByPillar(pillar: String?) {
        _state.update { it.copy(selectedPillar = pillar) }
    }

    fun filterByAuthor(author: String?) {
        _state.update { it.copy(selectedAuthor = author) }
    }

    fun setSpeaking(speaking: Boolean) {
        _state.update { it.copy(isSpeaking = speaking) }
    }

    fun toggleFavorite(quote: PhilosophyQuote) {
        viewModelScope.launch {
            quoteDao.updateQuote(quote.copy(favorited = !quote.favorited))
        }
    }
}