package com.stoicus.app.core.domain.usecase

import com.stoicus.app.core.data.local.dao.MoodEntryDao
import com.stoicus.app.core.data.local.entity.MoodEntry
import javax.inject.Inject

class SaveMoodEntryUseCase @Inject constructor(
    private val moodEntryDao: MoodEntryDao
) {
    suspend operator fun invoke(entry: MoodEntry): Long {
        return moodEntryDao.insertMoodEntry(entry)
    }
}
