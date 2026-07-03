package com.stoicus.app.feature.morning;

import com.stoicus.app.core.domain.usecase.GetRitualExercisesUseCase;
import com.stoicus.app.core.domain.usecase.SaveRitualSessionUseCase;
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
public final class MorningRitualViewModel_Factory implements Factory<MorningRitualViewModel> {
  private final Provider<GetRitualExercisesUseCase> getRitualExercisesUseCaseProvider;

  private final Provider<SaveRitualSessionUseCase> saveRitualSessionUseCaseProvider;

  public MorningRitualViewModel_Factory(
      Provider<GetRitualExercisesUseCase> getRitualExercisesUseCaseProvider,
      Provider<SaveRitualSessionUseCase> saveRitualSessionUseCaseProvider) {
    this.getRitualExercisesUseCaseProvider = getRitualExercisesUseCaseProvider;
    this.saveRitualSessionUseCaseProvider = saveRitualSessionUseCaseProvider;
  }

  @Override
  public MorningRitualViewModel get() {
    return newInstance(getRitualExercisesUseCaseProvider.get(), saveRitualSessionUseCaseProvider.get());
  }

  public static MorningRitualViewModel_Factory create(
      Provider<GetRitualExercisesUseCase> getRitualExercisesUseCaseProvider,
      Provider<SaveRitualSessionUseCase> saveRitualSessionUseCaseProvider) {
    return new MorningRitualViewModel_Factory(getRitualExercisesUseCaseProvider, saveRitualSessionUseCaseProvider);
  }

  public static MorningRitualViewModel newInstance(
      GetRitualExercisesUseCase getRitualExercisesUseCase,
      SaveRitualSessionUseCase saveRitualSessionUseCase) {
    return new MorningRitualViewModel(getRitualExercisesUseCase, saveRitualSessionUseCase);
  }
}
