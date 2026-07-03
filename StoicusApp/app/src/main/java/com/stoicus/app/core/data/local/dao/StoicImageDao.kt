package com.stoicus.app.core.data.local.dao

import androidx.room.*
import com.stoicus.app.core.data.local.entity.StoicImage
import kotlinx.coroutines.flow.Flow

@Dao
interface StoicImageDao {
    @Query("SELECT * FROM stoic_images ORDER BY RANDOM()")
    fun getAllImages(): Flow<List<StoicImage>>

    @Query("SELECT * FROM stoic_images WHERE category = :category ORDER BY RANDOM()")
    fun getImagesByCategory(category: String): Flow<List<StoicImage>>

    @Query("SELECT * FROM stoic_images WHERE favorited = 1")
    fun getFavoriteImages(): Flow<List<StoicImage>>

    @Query("SELECT * FROM stoic_images WHERE philosopher = :philosopher")
    fun getImagesByPhilosopher(philosopher: String): Flow<List<StoicImage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: StoicImage): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<StoicImage>)

    @Update
    suspend fun updateImage(image: StoicImage)

    @Delete
    suspend fun deleteImage(image: StoicImage)
}
