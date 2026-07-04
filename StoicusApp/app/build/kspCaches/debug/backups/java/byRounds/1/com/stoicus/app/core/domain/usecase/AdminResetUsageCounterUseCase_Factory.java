package com.stoicus.app.core.domain.usecase;

import com.stoicus.app.core.data.local.dao.AdminAuditLogDao;
import com.stoicus.app.core.security.AdminAuthManager;
import com.stoicus.app.core.security.SecureUsageCounter;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AdminResetUsageCounterUseCase_Factory implements Factory<AdminResetUsageCounterUseCase> {
  private final Provider<AdminAuthManager> adminAuthManagerProvider;

  private final Provider<SecureUsageCounter> secureUsageCounterProvider;

  private final Provider<AdminAuditLogDao> auditLogDaoProvider;

  public AdminResetUsageCounterUseCase_Factory(Provider<AdminAuthManager> adminAuthManagerProvider,
      Provider<SecureUsageCounter> secureUsageCounterProvider,
      Provider<AdminAuditLogDao> auditLogDaoProvider) {
    this.adminAuthManagerProvider = adminAuthManagerProvider;
    this.secureUsageCounterProvider = secureUsageCounterProvider;
    this.auditLogDaoProvider = auditLogDaoProvider;
  }

  @Override
  public AdminResetUsageCounterUseCase get() {
    return newInstance(adminAuthManagerProvider.get(), secureUsageCounterProvider.get(), auditLogDaoProvider.get());
  }

  public static AdminResetUsageCounterUseCase_Factory create(
      Provider<AdminAuthManager> adminAuthManagerProvider,
      Provider<SecureUsageCounter> secureUsageCounterProvider,
      Provider<AdminAuditLogDao> auditLogDaoProvider) {
    return new AdminResetUsageCounterUseCase_Factory(adminAuthManagerProvider, secureUsageCounterProvider, auditLogDaoProvider);
  }

  public static AdminResetUsageCounterUseCase newInstance(AdminAuthManager adminAuthManager,
      SecureUsageCounter secureUsageCounter, AdminAuditLogDao auditLogDao) {
    return new AdminResetUsageCounterUseCase(adminAuthManager, secureUsageCounter, auditLogDao);
  }
}
