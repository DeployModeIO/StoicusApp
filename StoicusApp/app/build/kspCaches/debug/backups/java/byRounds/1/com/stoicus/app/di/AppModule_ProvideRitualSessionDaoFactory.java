package com.stoicus.app.di;

import com.stoicus.app.core.data.local.StoicusDatabase;
import com.stoicus.app.core.data.local.dao.RitualSessionDao;
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
public final class AppModule_ProvideRitualSessionDaoFactory implements Factory<RitualSessionDao> {
  private final Provider<StoicusDatabase> databaseProvider;

  public AppModule_ProvideRitualSessionDaoFactory(Provider<StoicusDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public RitualSessionDao get() {
    return provideRitualSessionDao(databaseProvider.get());
  }

  public static AppModule_ProvideRitualSessionDaoFactory create(
      Provider<StoicusDatabase> databaseProvider) {
    return new AppModule_ProvideRitualSessionDaoFactory(databaseProvider);
  }

  public static RitualSessionDao provideRitualSessionDao(StoicusDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideRitualSessionDao(database));
  }
}
