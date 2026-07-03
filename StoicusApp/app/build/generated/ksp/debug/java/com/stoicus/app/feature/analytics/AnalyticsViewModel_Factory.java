package com.stoicus.app.feature.analytics;

import com.stoicus.app.core.domain.usecase.GetAnalyticsDataUseCase;
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
public final class AnalyticsViewModel_Factory implements Factory<AnalyticsViewModel> {
  private final Provider<GetAnalyticsDataUseCase> getAnalyticsDataUseCaseProvider;

  public AnalyticsViewModel_Factory(
      Provider<GetAnalyticsDataUseCase> getAnalyticsDataUseCaseProvider) {
    this.getAnalyticsDataUseCaseProvider = getAnalyticsDataUseCaseProvider;
  }

  @Override
  public AnalyticsViewModel get() {
    return newInstance(getAnalyticsDataUseCaseProvider.get());
  }

  public static AnalyticsViewModel_Factory create(
      Provider<GetAnalyticsDataUseCase> getAnalyticsDataUseCaseProvider) {
    return new AnalyticsViewModel_Factory(getAnalyticsDataUseCaseProvider);
  }

  public static AnalyticsViewModel newInstance(GetAnalyticsDataUseCase getAnalyticsDataUseCase) {
    return new AnalyticsViewModel(getAnalyticsDataUseCase);
  }
}
