package com.stoicus.app.core.data.local.dao

import androidx.room.*
import com.stoicus.app.core.data.local.entity.StoicMusicTrack
import kotlinx.coroutines.flow.Flow

@Dao
interface StoicMusicDao {
    @Query("SELECT * FROM stoic_music ORDER BY title")
    fun getAllTracks(): Flow<List<StoicMusicTrack>>

    @Query("SELECT * FROM stoic_music WHERE category = :category ORDER BY title")
    fun getTracksByCategory(category: String): Flow<List<StoicMusicTrack>>

    @Query("SELECT * FROM stoic_music WHERE favorited = 1")
    fun getFavoriteTracks(): Flow<List<StoicMusicTrack>>

    @Query("SELECT * FROM stoic_music WHERE mood = :mood ORDER BY title")
    fun getTracksByMood(mood: String): Flow<List<StoicMusicTrack>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(track: StoicMusicTrack): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTracks(tracks: List<StoicMusicTrack>)

    @Update
    suspend fun updateTrack(track: StoicMusicTrack)

    @Delete
    suspend fun deleteTrack(track: StoicMusicTrack)

    @Query("UPDATE stoic_music SET isPlaying = 0")
    suspend fun stopAllTracks()

    @Query("UPDATE stoic_music SET isPlaying = 1 WHERE id = :id")
    suspend fun playTrack(id: Long)
}
