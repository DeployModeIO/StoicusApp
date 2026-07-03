package com.stoicus.app.feature.evening;

import com.stoicus.app.core.data.local.dao.MoodEntryDao;
import com.stoicus.app.core.domain.usecase.GetRitualExercisesUseCase;
import com.stoicus.app.core.domain.usecase.SaveMoodEntryUseCase;
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
public final class EveningRitualViewModel_Factory implements Factory<EveningRitualViewModel> {
  private final Provider<GetRitualExercisesUseCase> getRitualExercisesUseCaseProvider;

  private final Provider<SaveRitualSessionUseCase> saveRitualSessionUseCaseProvider;

  private final Provider<SaveMoodEntryUseCase> saveMoodEntryUseCaseProvider;

  private final Provider<MoodEntryDao> moodEntryDaoProvider;

  public EveningRitualViewModel_Factory(
      Provider<GetRitualExercisesUseCase> getRitualExercisesUseCaseProvider,
      Provider<SaveRitualSessionUseCase> saveRitualSessionUseCaseProvider,
      Provider<SaveMoodEntryUseCase> saveMoodEntryUseCaseProvider,
      Provider<MoodEntryDao> moodEntryDaoProvider) {
    this.getRitualExercisesUseCaseProvider = getRitualExercisesUseCaseProvider;
    this.saveRitualSessionUseCaseProvider = saveRitualSessionUseCaseProvider;
    this.saveMoodEntryUseCaseProvider = saveMoodEntryUseCaseProvider;
    this.moodEntryDaoProvider = moodEntryDaoProvider;
  }

  @Override
  public EveningRitualViewModel get() {
    return newInstance(getRitualExercisesUseCaseProvider.get(), saveRitualSessionUseCaseProvider.get(), saveMoodEntryUseCaseProvider.get(), moodEntryDaoProvider.get());
  }

  public static EveningRitualViewModel_Factory create(
      Provider<GetRitualExercisesUseCase> getRitualExercisesUseCaseProvider,
      Provider<SaveRitualSessionUseCase> saveRitualSessionUseCaseProvider,
      Provider<SaveMoodEntryUseCase> saveMoodEntryUseCaseProvider,
      Provider<MoodEntryDao> moodEntryDaoProvider) {
    return new EveningRitualViewModel_Factory(getRitualExercisesUseCaseProvider, saveRitualSessionUseCaseProvider, saveMoodEntryUseCaseProvider, moodEntryDaoProvider);
  }

  public static EveningRitualViewModel newInstance(
      GetRitualExercisesUseCase getRitualExercisesUseCase,
      SaveRitualSessionUseCase saveRitualSessionUseCase, SaveMoodEntryUseCase saveMoodEntryUseCase,
      MoodEntryDao moodEntryDao) {
    return new EveningRitualViewModel(getRitualExercisesUseCase, saveRitualSessionUseCase, saveMoodEntryUseCase, moodEntryDao);
  }
}
