package com.stoicus.app.core.data.local.dao

import androidx.room.*
import com.stoicus.app.core.data.local.entity.MoodEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodEntryDao {
    @Query("SELECT * FROM mood_entries ORDER BY timestamp DESC LIMIT 30")
    fun getRecentMoodEntries(): Flow<List<MoodEntry>>

    @Query("SELECT * FROM mood_entries WHERE date = :date")
    fun getMoodByDate(date: String): Flow<MoodEntry?>

    @Query("SELECT AVG(moodScore) FROM mood_entries WHERE timestamp BETWEEN :startTime AND :endTime")
    fun getAverageMood(startTime: Long, endTime: Long): Flow<Float?>

    @Query("SELECT AVG(stressLevel) FROM mood_entries WHERE timestamp BETWEEN :startTime AND :endTime")
    fun getAverageStress(startTime: Long, endTime: Long): Flow<Float?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoodEntry(entry: MoodEntry): Long

    @Update
    suspend fun updateMoodEntry(entry: MoodEntry)
}
