package com.stoicus.app.di;

import com.stoicus.app.core.data.local.StoicusDatabase;
import com.stoicus.app.core.data.local.dao.StreakRecordDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideStreakRecordDaoFactory implements Factory<StreakRecordDao> {
  private final Provider<StoicusDatabase> databaseProvider;

  public AppModule_ProvideStreakRecordDaoFactory(Provider<StoicusDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public StreakRecordDao get() {
    return provideStreakRecordDao(databaseProvider.get());
  }

  public static AppModule_ProvideStreakRecordDaoFactory create(
      Provider<StoicusDatabase> databaseProvider) {
    return new AppModule_ProvideStreakRecordDaoFactory(databaseProvider);
  }

  public static StreakRecordDao provideStreakRecordDao(StoicusDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideStreakRecordDao(database));
  }
}
