package com.stoicus.app.di

import android.content.Context
import androidx.room.Room
import com.stoicus.app.core.data.local.StoicusDatabase
import com.stoicus.app.core.data.local.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): StoicusDatabase {
        return Room.databaseBuilder(
            context,
            StoicusDatabase::class.java,
            "stoicus_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserProfileDao(database: StoicusDatabase): UserProfileDao {
        return database.userProfileDao()
    }

    @Provides
    fun provideRitualSessionDao(database: StoicusDatabase): RitualSessionDao {
        return database.ritualSessionDao()
    }

    @Provides
    fun provideMicroActionDao(database: StoicusDatabase): MicroActionDao {
        return database.microActionDao()
    }

    @Provides
    fun provideMoodEntryDao(database: StoicusDatabase): MoodEntryDao {
        return database.moodEntryDao()
    }

    @Provides
    fun providePhilosophyQuoteDao(database: StoicusDatabase): PhilosophyQuoteDao {
        return database.philosophyQuoteDao()
    }

    @Provides
    fun provideStreakRecordDao(database: StoicusDatabase): StreakRecordDao {
        return database.streakRecordDao()
    }

    @Provides
    fun provideDailyGoalDao(database: StoicusDatabase): DailyGoalDao {
        return database.dailyGoalDao()
    }

    @Provides
    fun provideStoicImageDao(database: StoicusDatabase): StoicImageDao {
        return database.stoicImageDao()
    }

    @Provides
    fun provideStoicMusicDao(database: StoicusDatabase): StoicMusicDao {
        return database.stoicMusicDao()
    }

    @Provides
    fun provideAdminAuditLogDao(database: StoicusDatabase): AdminAuditLogDao {
        return database.adminAuditLogDao()
    }
}
