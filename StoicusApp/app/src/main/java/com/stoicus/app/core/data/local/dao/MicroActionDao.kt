package com.stoicus.app.core.data.local.dao

import androidx.room.*
import com.stoicus.app.core.data.local.entity.MicroAction
import kotlinx.coroutines.flow.Flow

@Dao
interface MicroActionDao {
    @Query("SELECT * FROM micro_actions WHERE date = :date ORDER BY id DESC")
    fun getActionsByDate(date: String): Flow<List<MicroAction>>

    @Query("SELECT * FROM micro_actions WHERE date = :date AND completed = 1")
    fun getCompletedActionsByDate(date: String): Flow<List<MicroAction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAction(action: MicroAction): Long

    @Update
    suspend fun updateAction(action: MicroAction)

    @Delete
    suspend fun deleteAction(action: MicroAction)
}
