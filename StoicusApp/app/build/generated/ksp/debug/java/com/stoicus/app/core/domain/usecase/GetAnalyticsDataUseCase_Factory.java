package com.stoicus.app.core.domain.usecase;

import com.stoicus.app.core.data.local.dao.MoodEntryDao;
import com.stoicus.app.core.data.local.dao.RitualSessionDao;
import com.stoicus.app.core.data.local.dao.StreakRecordDao;
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
public final class GetAnalyticsDataUseCase_Factory implements Factory<GetAnalyticsDataUseCase> {
  private final Provider<RitualSessionDao> ritualSessionDaoProvider;

  private final Provider<MoodEntryDao> moodEntryDaoProvider;

  private final Provider<StreakRecordDao> streakRecordDaoProvider;

  public GetAnalyticsDataUseCase_Factory(Provider<RitualSessionDao> ritualSessionDaoProvider,
      Provider<MoodEntryDao> moodEntryDaoProvider,
      Provider<StreakRecordDao> streakRecordDaoProvider) {
    this.ritualSessionDaoProvider = ritualSessionDaoProvider;
    this.moodEntryDaoProvider = moodEntryDaoProvider;
    this.streakRecordDaoProvider = streakRecordDaoProvider;
  }

  @Override
  public GetAnalyticsDataUseCase get() {
    return newInstance(ritualSessionDaoProvider.get(), moodEntryDaoProvider.get(), streakRecordDaoProvider.get());
  }

  public static GetAnalyticsDataUseCase_Factory create(
      Provider<RitualSessionDao> ritualSessionDaoProvider,
      Provider<MoodEntryDao> moodEntryDaoProvider,
      Provider<StreakRecordDao> streakRecordDaoProvider) {
    return new GetAnalyticsDataUseCase_Factory(ritualSessionDaoProvider, moodEntryDaoProvider, streakRecordDaoProvider);
  }

  public static GetAnalyticsDataUseCase newInstance(RitualSessionDao ritualSessionDao,
      MoodEntryDao moodEntryDao, StreakRecordDao streakRecordDao) {
    return new GetAnalyticsDataUseCase(ritualSessionDao, moodEntryDao, streakRecordDao);
  }
}
