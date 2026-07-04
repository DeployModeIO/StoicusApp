package com.stoicus.app.di;

import android.content.Context;
import com.stoicus.app.core.security.AdminAuthManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class SecurityModule_ProvideAdminAuthManagerFactory implements Factory<AdminAuthManager> {
  private final Provider<Context> contextProvider;

  public SecurityModule_ProvideAdminAuthManagerFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AdminAuthManager get() {
    return provideAdminAuthManager(contextProvider.get());
  }

  public static SecurityModule_ProvideAdminAuthManagerFactory create(
      Provider<Context> contextProvider) {
    return new SecurityModule_ProvideAdminAuthManagerFactory(contextProvider);
  }

  public static AdminAuthManager provideAdminAuthManager(Context context) {
    return Preconditions.checkNotNullFromProvides(SecurityModule.INSTANCE.provideAdminAuthManager(context));
  }
}
