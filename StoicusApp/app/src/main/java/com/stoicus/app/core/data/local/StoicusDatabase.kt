package com.stoicus.app.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stoicus.app.core.data.local.dao.*
import com.stoicus.app.core.data.local.entity.*
import com.stoicus.app.core.data.local.converters.Converters

@Database(
    entities = [
        UserProfile::class,
        RitualSession::class,
        MicroAction::class,
        MoodEntry::class,
        PhilosophyQuote::class,
        StreakRecord::class,
        DailyGoal::class,
        StoicImage::class,
        StoicMusicTrack::class,
        AdminAuditLog::class
    ],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class StoicusDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
    abstract fun ritualSessionDao(): RitualSessionDao
    abstract fun microActionDao(): MicroActionDao
    abstract fun moodEntryDao(): MoodEntryDao
    abstract fun philosophyQuoteDao(): PhilosophyQuoteDao
    abstract fun streakRecordDao(): StreakRecordDao
    abstract fun dailyGoalDao(): DailyGoalDao
    abstract fun stoicImageDao(): StoicImageDao
    abstract fun stoicMusicDao(): StoicMusicDao
    abstract fun adminAuditLogDao(): AdminAuditLogDao
}
