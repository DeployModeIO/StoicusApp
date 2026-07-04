package com.stoicus.app.core.domain.usecase;

import com.stoicus.app.core.network.GumroadRepository;
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
public final class VerifyLicenseUseCase_Factory implements Factory<VerifyLicenseUseCase> {
  private final Provider<GumroadRepository> gumroadRepositoryProvider;

  public VerifyLicenseUseCase_Factory(Provider<GumroadRepository> gumroadRepositoryProvider) {
    this.gumroadRepositoryProvider = gumroadRepositoryProvider;
  }

  @Override
  public VerifyLicenseUseCase get() {
    return newInstance(gumroadRepositoryProvider.get());
  }

  public static VerifyLicenseUseCase_Factory create(
      Provider<GumroadRepository> gumroadRepositoryProvider) {
    return new VerifyLicenseUseCase_Factory(gumroadRepositoryProvider);
  }

  public static VerifyLicenseUseCase newInstance(GumroadRepository gumroadRepository) {
    return new VerifyLicenseUseCase(gumroadRepository);
  }
}
