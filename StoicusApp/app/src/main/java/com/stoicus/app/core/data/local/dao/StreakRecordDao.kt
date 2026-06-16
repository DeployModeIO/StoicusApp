package com.stoicus.app.core.data.local.dao

import androidx.room.*
import com.stoicus.app.core.data.local.entity.StreakRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface StreakRecordDao {
    @Query("SELECT * FROM streak_records ORDER BY date DESC LIMIT 30")
    fun getRecentStreaks(): Flow<List<StreakRecord>>

    @Query("SELECT * FROM streak_records WHERE date = :date")
    fun getStreakByDate(date: String): Flow<StreakRecord?>

    @Query("SELECT * FROM streak_records WHERE morningRitualCompleted = 1 OR eveningRitualCompleted = 1 ORDER BY date DESC")
    fun getCompletedDays(): Flow<List<StreakRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStreak(streak: StreakRecord): Long

    @Update
    suspend fun updateStreak(streak: StreakRecord)
}
