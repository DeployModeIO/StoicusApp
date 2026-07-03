package com.stoicus.app.feature.morning;

import com.stoicus.app.core.domain.usecase.GetUserProfileUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<GetUserProfileUseCase> getUserProfileUseCaseProvider;

  public HomeViewModel_Factory(Provider<GetUserProfileUseCase> getUserProfileUseCaseProvider) {
    this.getUserProfileUseCaseProvider = getUserProfileUseCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(getUserProfileUseCaseProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<GetUserProfileUseCase> getUserProfileUseCaseProvider) {
    return new HomeViewModel_Factory(getUserProfileUseCaseProvider);
  }

  public static HomeViewModel newInstance(GetUserProfileUseCase getUserProfileUseCase) {
    return new HomeViewModel(getUserProfileUseCase);
  }
}
