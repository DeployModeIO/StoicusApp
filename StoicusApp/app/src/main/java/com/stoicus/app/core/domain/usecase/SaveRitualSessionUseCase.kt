package com.stoicus.app.core.domain.usecase

import com.stoicus.app.core.data.local.dao.RitualSessionDao
import com.stoicus.app.core.data.local.entity.RitualSession
import javax.inject.Inject

class SaveRitualSessionUseCase @Inject constructor(
    private val ritualSessionDao: RitualSessionDao
) {
    suspend operator fun invoke(session: RitualSession): Long {
        return ritualSessionDao.insertSession(session)
    }
}
