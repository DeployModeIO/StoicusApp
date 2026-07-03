package com.stoicus.app.core.domain.usecase;

import com.stoicus.app.core.data.local.dao.MoodEntryDao;
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
public final class SaveMoodEntryUseCase_Factory implements Factory<SaveMoodEntryUseCase> {
  private final Provider<MoodEntryDao> moodEntryDaoProvider;

  public SaveMoodEntryUseCase_Factory(Provider<MoodEntryDao> moodEntryDaoProvider) {
    this.moodEntryDaoProvider = moodEntryDaoProvider;
  }

  @Override
  public SaveMoodEntryUseCase get() {
    return newInstance(moodEntryDaoProvider.get());
  }

  public static SaveMoodEntryUseCase_Factory create(Provider<MoodEntryDao> moodEntryDaoProvider) {
    return new SaveMoodEntryUseCase_Factory(moodEntryDaoProvider);
  }

  public static SaveMoodEntryUseCase newInstance(MoodEntryDao moodEntryDao) {
    return new SaveMoodEntryUseCase(moodEntryDao);
  }
}
