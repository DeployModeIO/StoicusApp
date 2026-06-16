package com.stoicus.app.core.domain.usecase

import com.stoicus.app.core.data.local.dao.PhilosophyQuoteDao
import com.stoicus.app.core.data.local.entity.PhilosophyQuote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDailyQuoteUseCase @Inject constructor(
    private val philosophyQuoteDao: PhilosophyQuoteDao
) {
    operator fun invoke(): Flow<PhilosophyQuote?> {
        return philosophyQuoteDao.getRandomQuote()
    }
    
    fun getByPillar(pillar: String): Flow<PhilosophyQuote?> {
        return philosophyQuoteDao.getQuoteByPillar(pillar)
    }
}
