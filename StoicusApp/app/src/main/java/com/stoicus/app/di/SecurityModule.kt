package com.stoicus.app.di

import android.content.Context
import com.stoicus.app.core.network.GumroadRepository
import com.stoicus.app.core.security.AdminAuthManager
import com.stoicus.app.core.security.SecureUsageCounter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo Hilt para dependencias de seguridad.
 *
 * [AdminAuthManager] y [SecureUsageCounter] son singletons porque mantienen
 * estado en memoria (sesión admin) y archivos cifrados.
 */
@Module
@InstallIn(SingletonComponent::class)
object SecurityModule {

    @Provides
    @Singleton
    fun provideAdminAuthManager(@ApplicationContext context: Context): AdminAuthManager {
        return AdminAuthManager(context).also { it.ensureDefaultAdminPinInitialized() }
    }

    @Provides
    @Singleton
    fun provideSecureUsageCounter(
        @ApplicationContext context: Context,
        gumroadRepository: GumroadRepository
    ): SecureUsageCounter {
        // El licenseChecker apunta al repositorio para no consumir usos si hay licencia activa.
        return SecureUsageCounter(context) { gumroadRepository.isLicenseActive() }
    }
}