package com.stoicus.app.di;

import android.content.Context;
import com.stoicus.app.core.network.GumroadRepository;
import com.stoicus.app.core.security.SecureUsageCounter;
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
public final class SecurityModule_ProvideSecureUsageCounterFactory implements Factory<SecureUsageCounter> {
  private final Provider<Context> contextProvider;

  private final Provider<GumroadRepository> gumroadRepositoryProvider;

  public SecurityModule_ProvideSecureUsageCounterFactory(Provider<Context> contextProvider,
      Provider<GumroadRepository> gumroadRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.gumroadRepositoryProvider = gumroadRepositoryProvider;
  }

  @Override
  public SecureUsageCounter get() {
    return provideSecureUsageCounter(contextProvider.get(), gumroadRepositoryProvider.get());
  }

  public static SecurityModule_ProvideSecureUsageCounterFactory create(
      Provider<Context> contextProvider, Provider<GumroadRepository> gumroadRepositoryProvider) {
    return new SecurityModule_ProvideSecureUsageCounterFactory(contextProvider, gumroadRepositoryProvider);
  }

  public static SecureUsageCounter provideSecureUsageCounter(Context context,
      GumroadRepository gumroadRepository) {
    return Preconditions.checkNotNullFromProvides(SecurityModule.INSTANCE.provideSecureUsageCounter(context, gumroadRepository));
  }
}
