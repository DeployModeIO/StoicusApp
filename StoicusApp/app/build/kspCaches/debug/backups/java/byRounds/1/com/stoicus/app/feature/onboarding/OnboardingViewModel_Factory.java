package com.stoicus.app.feature.onboarding;

import com.stoicus.app.core.domain.usecase.GetUserProfileUseCase;
import com.stoicus.app.core.domain.usecase.SaveUserProfileUseCase;
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
public final class OnboardingViewModel_Factory implements Factory<OnboardingViewModel> {
  private final Provider<GetUserProfileUseCase> getUserProfileUseCaseProvider;

  private final Provider<SaveUserProfileUseCase> saveUserProfileUseCaseProvider;

  public OnboardingViewModel_Factory(Provider<GetUserProfileUseCase> getUserProfileUseCaseProvider,
      Provider<SaveUserProfileUseCase> saveUserProfileUseCaseProvider) {
    this.getUserProfileUseCaseProvider = getUserProfileUseCaseProvider;
    this.saveUserProfileUseCaseProvider = saveUserProfileUseCaseProvider;
  }

  @Override
  public OnboardingViewModel get() {
    return newInstance(getUserProfileUseCaseProvider.get(), saveUserProfileUseCaseProvider.get());
  }

  public static OnboardingViewModel_Factory create(
      Provider<GetUserProfileUseCase> getUserProfileUseCaseProvider,
      Provider<SaveUserProfileUseCase> saveUserProfileUseCaseProvider) {
    return new OnboardingViewModel_Factory(getUserProfileUseCaseProvider, saveUserProfileUseCaseProvider);
  }

  public static OnboardingViewModel newInstance(GetUserProfileUseCase getUserProfileUseCase,
      SaveUserProfileUseCase saveUserProfileUseCase) {
    return new OnboardingViewModel(getUserProfileUseCase, saveUserProfileUseCase);
  }
}
