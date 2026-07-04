package com.stoicus.app.di;

import com.stoicus.app.core.data.local.StoicusDatabase;
import com.stoicus.app.core.data.local.dao.AdminAuditLogDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideAdminAuditLogDaoFactory implements Factory<AdminAuditLogDao> {
  private final Provider<StoicusDatabase> databaseProvider;

  public AppModule_ProvideAdminAuditLogDaoFactory(Provider<StoicusDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public AdminAuditLogDao get() {
    return provideAdminAuditLogDao(databaseProvider.get());
  }

  public static AppModule_ProvideAdminAuditLogDaoFactory create(
      Provider<StoicusDatabase> databaseProvider) {
    return new AppModule_ProvideAdminAuditLogDaoFactory(databaseProvider);
  }

  public static AdminAuditLogDao provideAdminAuditLogDao(StoicusDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideAdminAuditLogDao(database));
  }
}
