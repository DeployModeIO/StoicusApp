package com.stoicus.app.core.domain.usecase

import com.stoicus.app.core.data.local.dao.UserProfileDao
import com.stoicus.app.core.data.local.entity.UserProfile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val userProfileDao: UserProfileDao
) {
    operator fun invoke(): Flow<UserProfile?> {
        return userProfileDao.getProfile()
    }
}
