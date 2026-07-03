package com.stoicus.app.core.domain.usecase;

import com.stoicus.app.core.data.local.dao.RitualSessionDao;
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
public final class SaveRitualSessionUseCase_Factory implements Factory<SaveRitualSessionUseCase> {
  private final Provider<RitualSessionDao> ritualSessionDaoProvider;

  public SaveRitualSessionUseCase_Factory(Provider<RitualSessionDao> ritualSessionDaoProvider) {
    this.ritualSessionDaoProvider = ritualSessionDaoProvider;
  }

  @Override
  public SaveRitualSessionUseCase get() {
    return newInstance(ritualSessionDaoProvider.get());
  }

  public static SaveRitualSessionUseCase_Factory create(
      Provider<RitualSessionDao> ritualSessionDaoProvider) {
    return new SaveRitualSessionUseCase_Factory(ritualSessionDaoProvider);
  }

  public static SaveRitualSessionUseCase newInstance(RitualSessionDao ritualSessionDao) {
    return new SaveRitualSessionUseCase(ritualSessionDao);
  }
}
