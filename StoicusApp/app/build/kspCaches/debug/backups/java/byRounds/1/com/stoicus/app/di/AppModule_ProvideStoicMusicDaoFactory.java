package com.stoicus.app.di;

import com.stoicus.app.core.data.local.StoicusDatabase;
import com.stoicus.app.core.data.local.dao.StoicMusicDao;
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
public final class AppModule_ProvideStoicMusicDaoFactory implements Factory<StoicMusicDao> {
  private final Provider<StoicusDatabase> databaseProvider;

  public AppModule_ProvideStoicMusicDaoFactory(Provider<StoicusDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public StoicMusicDao get() {
    return provideStoicMusicDao(databaseProvider.get());
  }

  public static AppModule_ProvideStoicMusicDaoFactory create(
      Provider<StoicusDatabase> databaseProvider) {
    return new AppModule_ProvideStoicMusicDaoFactory(databaseProvider);
  }

  public static StoicMusicDao provideStoicMusicDao(StoicusDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideStoicMusicDao(database));
  }
}
