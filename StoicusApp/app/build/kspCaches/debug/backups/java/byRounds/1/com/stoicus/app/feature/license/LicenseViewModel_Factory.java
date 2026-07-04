package com.stoicus.app.feature.license;

import com.stoicus.app.core.domain.usecase.VerifyLicenseUseCase;
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
public final class LicenseViewModel_Factory implements Factory<LicenseViewModel> {
  private final Provider<VerifyLicenseUseCase> verifyLicenseUseCaseProvider;

  public LicenseViewModel_Factory(Provider<VerifyLicenseUseCase> verifyLicenseUseCaseProvider) {
    this.verifyLicenseUseCaseProvider = verifyLicenseUseCaseProvider;
  }

  @Override
  public LicenseViewModel get() {
    return newInstance(verifyLicenseUseCaseProvider.get());
  }

  public static LicenseViewModel_Factory create(
      Provider<VerifyLicenseUseCase> verifyLicenseUseCaseProvider) {
    return new LicenseViewModel_Factory(verifyLicenseUseCaseProvider);
  }

  public static LicenseViewModel newInstance(VerifyLicenseUseCase verifyLicenseUseCase) {
    return new LicenseViewModel(verifyLicenseUseCase);
  }
}
