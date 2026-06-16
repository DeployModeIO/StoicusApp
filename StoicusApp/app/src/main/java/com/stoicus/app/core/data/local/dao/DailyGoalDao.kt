package com.stoicus.app.core.data.local.dao

import androidx.room.*
import com.stoicus.app.core.data.local.entity.DailyGoal
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyGoalDao {
    @Query("SELECT * FROM daily_goals WHERE date = :date ORDER BY id DESC")
    fun getGoalsByDate(date: String): Flow<List<DailyGoal>>

    @Query("SELECT * FROM daily_goals WHERE date = :date AND completed = 1")
    fun getCompletedGoalsByDate(date: String): Flow<List<DailyGoal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: DailyGoal): Long

    @Update
    suspend fun updateGoal(goal: DailyGoal)
}
