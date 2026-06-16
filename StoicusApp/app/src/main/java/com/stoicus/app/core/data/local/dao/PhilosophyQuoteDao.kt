package com.stoicus.app.core.data.local.dao

import androidx.room.*
import com.stoicus.app.core.data.local.entity.PhilosophyQuote
import kotlinx.coroutines.flow.Flow

@Dao
interface PhilosophyQuoteDao {
    @Query("SELECT * FROM philosophy_quotes ORDER BY RANDOM() LIMIT 1")
    fun getRandomQuote(): Flow<PhilosophyQuote?>

    @Query("SELECT * FROM philosophy_quotes WHERE pillar = :pillar ORDER BY RANDOM() LIMIT 1")
    fun getQuoteByPillar(pillar: String): Flow<PhilosophyQuote?>

    @Query("SELECT * FROM philosophy_quotes WHERE favorited = 1")
    fun getFavoriteQuotes(): Flow<List<PhilosophyQuote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: PhilosophyQuote): Long

    @Update
    suspend fun updateQuote(quote: PhilosophyQuote)
}
