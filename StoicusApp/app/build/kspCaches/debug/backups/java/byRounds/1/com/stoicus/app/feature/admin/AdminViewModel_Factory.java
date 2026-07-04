package com.stoicus.app.feature.admin;

import com.stoicus.app.core.data.local.dao.AdminAuditLogDao;
import com.stoicus.app.core.domain.usecase.AdminLoginUseCase;
import com.stoicus.app.core.domain.usecase.AdminResetUsageCounterUseCase;
import com.stoicus.app.core.domain.usecase.AdminSimulateGumroadPurchaseUseCase;
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
public final class AdminViewModel_Factory implements Factory<AdminViewModel> {
  private final Provider<AdminAuthManager> adminAuthManagerProvider;

  private final Provider<SecureUsageCounter> secureUsageCounterProvider;

  private final Provider<AdminLoginUseCase> adminLoginUseCaseProvider;

  private final Provider<AdminResetUsageCounterUseCase> adminResetUsageCounterUseCaseProvider;

  private final Provider<AdminSimulateGumroadPurchaseUseCase> adminSimulateGumroadPurchaseUseCaseProvider;

  private final Provider<AdminAuditLogDao> auditLogDaoProvider;

  public AdminViewModel_Factory(Provider<AdminAuthManager> adminAuthManagerProvider,
      Provider<SecureUsageCounter> secureUsageCounterProvider,
      Provider<AdminLoginUseCase> adminLoginUseCaseProvider,
      Provider<AdminResetUsageCounterUseCase> adminResetUsageCounterUseCaseProvider,
      Provider<AdminSimulateGumroadPurchaseUseCase> adminSimulateGumroadPurchaseUseCaseProvider,
      Provider<AdminAuditLogDao> auditLogDaoProvider) {
    this.adminAuthManagerProvider = adminAuthManagerProvider;
    this.secureUsageCounterProvider = secureUsageCounterProvider;
    this.adminLoginUseCaseProvider = adminLoginUseCaseProvider;
    this.adminResetUsageCounterUseCaseProvider = adminResetUsageCounterUseCaseProvider;
    this.adminSimulateGumroadPurchaseUseCaseProvider = adminSimulateGumroadPurchaseUseCaseProvider;
    this.auditLogDaoProvider = auditLogDaoProvider;
  }

  @Override
  public AdminViewModel get() {
    return newInstance(adminAuthManagerProvider.get(), secureUsageCounterProvider.get(), adminLoginUseCaseProvider.get(), adminResetUsageCounterUseCaseProvider.get(), adminSimulateGumroadPurchaseUseCaseProvider.get(), auditLogDaoProvider.get());
  }

  public static AdminViewModel_Factory create(Provider<AdminAuthManager> adminAuthManagerProvider,
      Provider<SecureUsageCounter> secureUsageCounterProvider,
      Provider<AdminLoginUseCase> adminLoginUseCaseProvider,
      Provider<AdminResetUsageCounterUseCase> adminResetUsageCounterUseCaseProvider,
      Provider<AdminSimulateGumroadPurchaseUseCase> adminSimulateGumroadPurchaseUseCaseProvider,
      Provider<AdminAuditLogDao> auditLogDaoProvider) {
    return new AdminViewModel_Factory(adminAuthManagerProvider, secureUsageCounterProvider, adminLoginUseCaseProvider, adminResetUsageCounterUseCaseProvider, adminSimulateGumroadPurchaseUseCaseProvider, auditLogDaoProvider);
  }

  public static AdminViewModel newInstance(AdminAuthManager adminAuthManager,
      SecureUsageCounter secureUsageCounter, AdminLoginUseCase adminLoginUseCase,
      AdminResetUsageCounterUseCase adminResetUsageCounterUseCase,
      AdminSimulateGumroadPurchaseUseCase adminSimulateGumroadPurchaseUseCase,
      AdminAuditLogDao auditLogDao) {
    return new AdminViewModel(adminAuthManager, secureUsageCounter, adminLoginUseCase, adminResetUsageCounterUseCase, adminSimulateGumroadPurchaseUseCase, auditLogDao);
  }
}
