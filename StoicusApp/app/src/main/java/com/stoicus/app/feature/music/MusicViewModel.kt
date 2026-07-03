package com.stoicus.app.feature.music

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoicus.app.core.data.local.entity.StoicMusicTrack
import com.stoicus.app.core.data.local.dao.StoicMusicDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MusicState(
    val tracks: List<StoicMusicTrack> = emptyList(),
    val selectedCategory: String? = null,
    val currentTrack: StoicMusicTrack? = null,
    val isPlaying: Boolean = false,
    val currentPosition: Int = 0,
    val isLoading: Boolean = false,
    val volume: Float = 0.7f
)

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val musicDao: StoicMusicDao
) : ViewModel() {

    private val _state = MutableStateFlow(MusicState())
    val state: StateFlow<MusicState> = _state.asStateFlow()

    private var mediaPlayer: MediaPlayer? = null

    init {
        loadTracks()
    }

    private fun loadTracks() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            musicDao.getAllTracks().collect { tracks ->
                if (tracks.isEmpty()) {
                    insertDefaultTracks()
                } else {
                    _state.update { 
                        it.copy(
                            tracks = tracks,
                            isLoading = false
                        ) 
                    }
                }
            }
        }
    }

    private suspend fun insertDefaultTracks() {
        val defaultTracks = listOf(
            // Meditation - Ambient Stoic Music
            StoicMusicTrack(title = "Meditación Matutina", artist = "Estoico Ambient", duration = 300, audioUrl = "morning_meditation", category = "meditation", mood = "calm"),
            StoicMusicTrack(title = "Contemplación Estoica", artist = "Philosophy Sounds", duration = 420, audioUrl = "stoic_contemplation", category = "meditation", mood = "calm"),
            StoicMusicTrack(title = "Silencio Interior", artist = "Marcus Aurelius Sounds", duration = 360, audioUrl = "inner_silence", category = "meditation", mood = "calm"),
            StoicMusicTrack(title = "Virtud y Paz", artist = "Seneca Waves", duration = 480, audioUrl = "virtue_peace", category = "meditation", mood = "calm"),
            
            // Ambient - Background Music
            StoicMusicTrack(title = "Ataraxia", artist = "Tranquil Mind", duration = 540, audioUrl = "ataraxia_ambient", category = "ambient", mood = "calm"),
            StoicMusicTrack(title = "Jardín de Epicuro", artist = "Ancient Gardens", duration = 600, audioUrl = "epicurean_garden_ambient", category = "ambient", mood = "calm"),
            StoicMusicTrack(title = "Columnas de Roma", artist = "Imperial Sounds", duration = 420, audioUrl = "roman_columns", category = "ambient", mood = "calm"),
            StoicMusicTrack(title = "Noche de Reflexión", artist = "Night Philosophy", duration = 480, audioUrl = "reflection_night", category = "ambient", mood = "melancholic"),
            
            // Focus - Study Music
            StoicMusicTrack(title = "Enfoque Profundo", artist = "Deep Work Sounds", duration = 900, audioUrl = "deep_focus", category = "focus", mood = "energetic"),
            StoicMusicTrack(title = "Escribiendo Meditaciones", artist = "Writer's Block", duration = 720, audioUrl = "writing_meditations", category = "focus", mood = "calm"),
            StoicMusicTrack(title = "Dialéctica", artist = "Logic Beats", duration = 600, audioUrl = "dialectic", category = "focus", mood = "energetic"),
            StoicMusicTrack(title = "Sabiduría Antigua", artist = "Ancient Wisdom", duration = 540, audioUrl = "ancient_wisdom", category = "focus", mood = "calm"),
            
            // Sleep - Night Music
            StoicMusicTrack(title = "Sueño del Filósofo", artist = "Sleep Sounds", duration = 1800, audioUrl = "philosopher_sleep", category = "sleep", mood = "calm"),
            StoicMusicTrack(title = "Descanso Estoico", artist = "Deep Sleep", duration = 2400, audioUrl = "stoic_rest", category = "sleep", mood = "calm"),
            StoicMusicTrack(title = "Paz Nocturna", artist = "Night Peace", duration = 1200, audioUrl = "nightly_peace", category = "sleep", mood = "calm"),
            StoicMusicTrack(title = "Meditaciones Nocturnas", artist = "Evening Calm", duration = 1500, audioUrl = "night_meditations", category = "sleep", mood = "melancholic")
        )
        musicDao.insertTracks(defaultTracks)
    }

    fun filterByCategory(category: String?) {
        _state.update { it.copy(selectedCategory = category) }
    }

    fun playTrack(context: Context, track: StoicMusicTrack) {
        viewModelScope.launch {
            // Stop current track
            stopTrack()
            
            // Update playing state
            musicDao.stopAllTracks()
            musicDao.playTrack(track.id)
            
            // In production, replace with actual audio file path
            // For now, simulate playback
            _state.update { 
                it.copy(
                    currentTrack = track,
                    isPlaying = true,
                    currentPosition = 0
                ) 
            }
            
            // TODO: Initialize MediaPlayer with actual audio file
            // mediaPlayer = MediaPlayer.create(context, R.raw.${track.audioUrl})
            // mediaPlayer?.start()
        }
    }

    fun pauseTrack() {
        viewModelScope.launch {
            mediaPlayer?.pause()
            _state.update { it.copy(isPlaying = false) }
        }
    }

    fun stopTrack() {
        viewModelScope.launch {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            musicDao.stopAllTracks()
            _state.update { 
                it.copy(
                    isPlaying = false,
                    currentTrack = null,
                    currentPosition = 0
                ) 
            }
        }
    }

    fun resumeTrack() {
        viewModelScope.launch {
            mediaPlayer?.start()
            _state.update { it.copy(isPlaying = true) }
        }
    }

    fun seekTo(position: Int) {
        viewModelScope.launch {
            mediaPlayer?.seekTo(position)
            _state.update { it.copy(currentPosition = position) }
        }
    }

    fun setVolume(volume: Float) {
        _state.update { it.copy(volume = volume) }
        mediaPlayer?.setVolume(volume, volume)
    }

    fun toggleFavorite(track: StoicMusicTrack) {
        viewModelScope.launch {
            musicDao.updateTrack(track.copy(favorited = !track.favorited))
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopTrack()
    }
}
