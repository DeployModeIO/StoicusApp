package com.stoicus.app.di;

import com.stoicus.app.core.data.local.StoicusDatabase;
import com.stoicus.app.core.data.local.dao.StoicImageDao;
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
public final class AppModule_ProvideStoicImageDaoFactory implements Factory<StoicImageDao> {
  private final Provider<StoicusDatabase> databaseProvider;

  public AppModule_ProvideStoicImageDaoFactory(Provider<StoicusDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public StoicImageDao get() {
    return provideStoicImageDao(databaseProvider.get());
  }

  public static AppModule_ProvideStoicImageDaoFactory create(
      Provider<StoicusDatabase> databaseProvider) {
    return new AppModule_ProvideStoicImageDaoFactory(databaseProvider);
  }

  public static StoicImageDao provideStoicImageDao(StoicusDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideStoicImageDao(database));
  }
}
