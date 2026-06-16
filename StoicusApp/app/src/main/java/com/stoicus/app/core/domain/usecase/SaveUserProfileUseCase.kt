package com.stoicus.app.core.domain.usecase

import com.stoicus.app.core.data.local.dao.UserProfileDao
import com.stoicus.app.core.data.local.entity.UserProfile
import javax.inject.Inject

class SaveUserProfileUseCase @Inject constructor(
    private val userProfileDao: UserProfileDao
) {
    suspend operator fun invoke(profile: UserProfile): Long {
        return userProfileDao.insertProfile(profile)
    }
}
