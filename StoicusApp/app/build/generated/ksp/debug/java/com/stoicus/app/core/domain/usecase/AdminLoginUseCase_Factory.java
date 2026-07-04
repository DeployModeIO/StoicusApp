package com.stoicus.app.core.domain.usecase;

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
public final class AdminLoginUseCase_Factory implements Factory<AdminLoginUseCase> {
  private final Provider<AdminAuthManager> adminAuthManagerProvider;

  public AdminLoginUseCase_Factory(Provider<AdminAuthManager> adminAuthManagerProvider) {
    this.adminAuthManagerProvider = adminAuthManagerProvider;
  }

  @Override
  public AdminLoginUseCase get() {
    return newInstance(adminAuthManagerProvider.get());
  }

  public static AdminLoginUseCase_Factory create(
      Provider<AdminAuthManager> adminAuthManagerProvider) {
    return new AdminLoginUseCase_Factory(adminAuthManagerProvider);
  }

  public static AdminLoginUseCase newInstance(AdminAuthManager adminAuthManager) {
    return new AdminLoginUseCase(adminAuthManager);
  }
}
