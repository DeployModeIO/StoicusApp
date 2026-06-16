package com.stoicus.app.core.domain.usecase

import com.stoicus.app.core.data.local.dao.*
import com.stoicus.app.core.domain.model.AnalyticsData
import kotlinx.coroutines.flow.*
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

class GetAnalyticsDataUseCase @Inject constructor(
    private val ritualSessionDao: RitualSessionDao,
    private val moodEntryDao: MoodEntryDao,
    private val streakRecordDao: StreakRecordDao
) {
    operator fun invoke(): Flow<AnalyticsData> {
        val now = System.currentTimeMillis()
        val thirtyDaysAgo = now - (30L * 24 * 60 * 60 * 1000)
        val today = LocalDate.now().toString()

        return combine(
            ritualSessionDao.getAllSessions(),
            moodEntryDao.getRecentMoodEntries(),
            moodEntryDao.getAverageMood(thirtyDaysAgo, now),
            moodEntryDao.getAverageStress(thirtyDaysAgo, now),
            streakRecordDao.getRecentStreaks()
        ) { sessions, moodEntries, avgMood, avgStress, streaks ->
            val sessionsByPillar = sessions.groupBy { it.pillar }.mapValues { it.value.size }
            val moodTrend = moodEntries.take(7).map { it.moodScore.toFloat() }
            val stressTrend = moodEntries.take(7).map { it.stressLevel.toFloat() }
            
            val currentStreak = calculateCurrentStreak(streaks)
            val longestStreak = calculateLongestStreak(streaks)

            AnalyticsData(
                totalSessions = sessions.size,
                currentStreak = currentStreak,
                longestStreak = longestStreak,
                averageMood = avgMood ?: 5f,
                averageStress = avgStress ?: 5f,
                sessionsByPillar = sessionsByPillar,
                moodTrend = moodTrend,
                stressTrend = stressTrend
            )
        }
    }

    private fun calculateCurrentStreak(streaks: List<com.stoicus.app.core.data.local.entity.StreakRecord>): Int {
        if (streaks.isEmpty()) return 0
        
        var streak = 0
        val today = LocalDate.now()
        
        for (i in 0 until 365) {
            val date = today.minusDays(i.toLong()).toString()
            val dayStreak = streaks.find { it.date == date }
            if (dayStreak != null && (dayStreak.morningRitualCompleted || dayStreak.eveningRitualCompleted)) {
                streak++
            } else {
                break
            }
        }
        return streak
    }

    private fun calculateLongestStreak(streaks: List<com.stoicus.app.core.data.local.entity.StreakRecord>): Int {
        if (streaks.isEmpty()) return 0
        
        val completedDays = streaks
            .filter { it.morningRitualCompleted || it.eveningRitualCompleted }
            .map { it.date }
            .sorted()
        
        if (completedDays.isEmpty()) return 0
        
        var longest = 1
        var current = 1
        
        for (i in 1 until completedDays.size) {
            val prev = LocalDate.parse(completedDays[i - 1])
            val curr = LocalDate.parse(completedDays[i])
            if (curr.toEpochDay() - prev.toEpochDay() == 1L) {
                current++
                longest = maxOf(longest, current)
            } else {
                current = 1
            }
        }
        return longest
    }
}
