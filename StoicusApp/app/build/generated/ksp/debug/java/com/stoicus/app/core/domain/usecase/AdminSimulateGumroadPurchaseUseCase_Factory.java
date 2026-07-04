package com.stoicus.app.core.domain.usecase;

import com.stoicus.app.core.data.local.dao.AdminAuditLogDao;
import com.stoicus.app.core.network.GumroadRepository;
import com.stoicus.app.core.security.AdminAuthManager;
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
public final class AdminSimulateGumroadPurchaseUseCase_Factory implements Factory<AdminSimulateGumroadPurchaseUseCase> {
  private final Provider<AdminAuthManager> adminAuthManagerProvider;

  private final Provider<GumroadRepository> gumroadRepositoryProvider;

  private final Provider<AdminAuditLogDao> auditLogDaoProvider;

  public AdminSimulateGumroadPurchaseUseCase_Factory(
      Provider<AdminAuthManager> adminAuthManagerProvider,
      Provider<GumroadRepository> gumroadRepositoryProvider,
      Provider<AdminAuditLogDao> auditLogDaoProvider) {
    this.adminAuthManagerProvider = adminAuthManagerProvider;
    this.gumroadRepositoryProvider = gumroadRepositoryProvider;
    this.auditLogDaoProvider = auditLogDaoProvider;
  }

  @Override
  public AdminSimulateGumroadPurchaseUseCase get() {
    return newInstance(adminAuthManagerProvider.get(), gumroadRepositoryProvider.get(), auditLogDaoProvider.get());
  }

  public static AdminSimulateGumroadPurchaseUseCase_Factory create(
      Provider<AdminAuthManager> adminAuthManagerProvider,
      Provider<GumroadRepository> gumroadRepositoryProvider,
      Provider<AdminAuditLogDao> auditLogDaoProvider) {
    return new AdminSimulateGumroadPurchaseUseCase_Factory(adminAuthManagerProvider, gumroadRepositoryProvider, auditLogDaoProvider);
  }

  public static AdminSimulateGumroadPurchaseUseCase newInstance(AdminAuthManager adminAuthManager,
      GumroadRepository gumroadRepository, AdminAuditLogDao auditLogDao) {
    return new AdminSimulateGumroadPurchaseUseCase(adminAuthManager, gumroadRepository, auditLogDao);
  }
}
