package com.stoicus.app.feature.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoicus.app.core.data.local.entity.StoicImage
import com.stoicus.app.core.data.local.dao.StoicImageDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GalleryState(
    val images: List<StoicImage> = emptyList(),
    val selectedCategory: String? = null,
    val isLoading: Boolean = false
)

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val imageDao: StoicImageDao
) : ViewModel() {

    private val _state = MutableStateFlow(GalleryState())
    val state: StateFlow<GalleryState> = _state.asStateFlow()

    init {
        loadImages()
    }

    private fun loadImages() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            imageDao.getAllImages().collect { images ->
                if (images.isEmpty()) {
                    insertDefaultImages()
                } else {
                    _state.update { 
                        it.copy(
                            images = images,
                            isLoading = false
                        ) 
                    }
                }
            }
        }
    }

    private suspend fun insertDefaultImages() {
        val defaultImages = listOf(
            // Statues - Marco Aurelio
            StoicImage(title = "Marco Aurelio Emperador", description = "Estatua ecuestre de Marco Aurelio en Roma", imageUrl = "marcus_aurelius_statue_1", category = "statues", philosopher = "Marco Aurelio"),
            StoicImage(title = "Busto de Marco Aurelio", description = "Busto clásico en los Museos Capitolinos", imageUrl = "marcus_aurelius_bust_1", category = "statues", philosopher = "Marco Aurelio"),
            
            // Statues - Séneca
            StoicImage(title = "Séneca Filósofo", description = "Estatua de Séneca en Córdoba, España", imageUrl = "seneca_statue_1", category = "statues", philosopher = "Séneca"),
            StoicImage(title = "Séneca y Nerón", description = "Relieve antiguo representando a Séneca enseñando a Nerón", imageUrl = "seneca_nero_relief", category = "statues", philosopher = "Séneca"),
            
            // Statues - Epicteto
            StoicImage(title = "Epicteto Maestro", description = "Representación artística de Epicteto enseñando", imageUrl = "epictetus_statue_1", category = "statues", philosopher = "Epicteto"),
            StoicImage(title = "Epicteto en Nicópolis", description = "Reconstrucción de la escuela de Epicteto", imageUrl = "epictetus_school", category = "statues", philosopher = "Epicteto"),
            
            // Artwork - Estoicismo
            StoicImage(title = "La Escuela de Atenas", description = "Fresco de Rafael mostrando filósofos estoicos", imageUrl = "school_of_athens", category = "artwork", philosopher = null),
            StoicImage(title = "Muerte de Séneca", description = "Pintura de Jacques-Louis David", imageUrl = "death_of_seneca", category = "artwork", philosopher = "Séneca"),
            StoicImage(title = "Meditaciones de Marco Aurelio", description = "Ilustración de las meditaciones", imageUrl = "meditations_art", category = "artwork", philosopher = "Marco Aurelio"),
            StoicImage(title = "Jardín de Epicuro", description = "Representación del jardín filosófico", imageUrl = "epicurean_garden", category = "artwork", philosopher = null),
            
            // Symbols
            StoicImage(title = "Columna Estoica", description = "Símbolo de la resistencia estoica", imageUrl = "stoic_column", category = "symbols", philosopher = null),
            StoicImage(title = "Mano de Zenón", description = "Representación de la lógica estoica", imageUrl = "zeno_hand", category = "symbols", philosopher = "Zenón"),
            StoicImage(title = "Ataraxia", description = "Símbolo de la tranquilidad del alma", imageUrl = "ataraxia_symbol", category = "symbols", philosopher = null),
            StoicImage(title = "Virtudes Cardinales", description = "Las cuatro virtudes estoicas", imageUrl = "cardinal_virtues", category = "symbols", philosopher = null),
            
            // Manuscripts
            StoicImage(title = "Meditaciones - Manuscrito", description = "Página de manuscrito antiguo de las Meditaciones", imageUrl = "meditations_manuscript", category = "manuscripts", philosopher = "Marco Aurelio"),
            StoicImage(title = "Cartas a Lucilio", description = "Manuscrito de las cartas de Séneca", imageUrl = "letters_to_lucilius", category = "manuscripts", philosopher = "Séneca"),
            StoicImage(title = "Disertaciones de Epicteto", description = "Manuscrito de las disertaciones", imageUrl = "discourses_epictetus", category = "manuscripts", philosopher = "Epicteto"),
            StoicImage(title = "Ética a Nicómaco", description = "Manuscrito antiguo sobre ética estoica", imageUrl = "nicomachean_ethics", category = "manuscripts", philosopher = null),
            
            // Spinoza
            StoicImage(title = "Baruch Spinoza", description = "Retrato de Spinoza", imageUrl = "spinoza_portrait", category = "statues", philosopher = "Baruch Spinoza"),
            StoicImage(title = "Casa de Spinoza", description = "La casa donde vivió Spinoza en Ámsterdam", imageUrl = "spinoza_house", category = "artwork", philosopher = "Baruch Spinoza"),
            StoicImage(title = "Ética de Spinoza", description = "Primera edición de la Ética", imageUrl = "spinoza_ethics", category = "manuscripts", philosopher = "Baruch Spinoza")
        )
        imageDao.insertImages(defaultImages)
    }

    fun filterByCategory(category: String?) {
        _state.update { it.copy(selectedCategory = category) }
    }

    fun toggleFavorite(image: StoicImage) {
        viewModelScope.launch {
            imageDao.updateImage(image.copy(favorited = !image.favorited))
        }
    }
}
