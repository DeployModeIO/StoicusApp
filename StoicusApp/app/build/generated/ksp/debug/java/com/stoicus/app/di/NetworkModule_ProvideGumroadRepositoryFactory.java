package com.stoicus.app.di;

import android.content.Context;
import com.stoicus.app.core.network.GumroadApiService;
import com.stoicus.app.core.network.GumroadRepository;
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
public final class NetworkModule_ProvideGumroadRepositoryFactory implements Factory<GumroadRepository> {
  private final Provider<GumroadApiService> apiProvider;

  private final Provider<Context> contextProvider;

  public NetworkModule_ProvideGumroadRepositoryFactory(Provider<GumroadApiService> apiProvider,
      Provider<Context> contextProvider) {
    this.apiProvider = apiProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public GumroadRepository get() {
    return provideGumroadRepository(apiProvider.get(), contextProvider.get());
  }

  public static NetworkModule_ProvideGumroadRepositoryFactory create(
      Provider<GumroadApiService> apiProvider, Provider<Context> contextProvider) {
    return new NetworkModule_ProvideGumroadRepositoryFactory(apiProvider, contextProvider);
  }

  public static GumroadRepository provideGumroadRepository(GumroadApiService api, Context context) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideGumroadRepository(api, context));
  }
}
