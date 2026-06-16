package com.stoicus.app.core.data.local.dao

import androidx.room.*
import com.stoicus.app.core.data.local.entity.RitualSession
import kotlinx.coroutines.flow.Flow

@Dao
interface RitualSessionDao {
    @Query("SELECT * FROM ritual_sessions ORDER BY completedAt DESC")
    fun getAllSessions(): Flow<List<RitualSession>>

    @Query("SELECT * FROM ritual_sessions WHERE date(completedAt/1000, 'unixepoch') = date(:date/1000, 'unixepoch')")
    fun getSessionsByDate(date: Long): Flow<List<RitualSession>>

    @Query("SELECT COUNT(*) FROM ritual_sessions WHERE pillar = :pillar")
    fun getSessionCountByPillar(pillar: String): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: RitualSession): Long

    @Delete
    suspend fun deleteSession(session: RitualSession)
}
