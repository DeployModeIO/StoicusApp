package com.stoicus.app.di;

import com.stoicus.app.core.network.GumroadApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
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
public final class NetworkModule_ProvideGumroadApiServiceFactory implements Factory<GumroadApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideGumroadApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public GumroadApiService get() {
    return provideGumroadApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideGumroadApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideGumroadApiServiceFactory(retrofitProvider);
  }

  public static GumroadApiService provideGumroadApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideGumroadApiService(retrofit));
  }
}
