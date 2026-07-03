package com.stoicus.app.di;

import com.stoicus.app.core.data.local.StoicusDatabase;
import com.stoicus.app.core.data.local.dao.MicroActionDao;
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
public final class AppModule_ProvideMicroActionDaoFactory implements Factory<MicroActionDao> {
  private final Provider<StoicusDatabase> databaseProvider;

  public AppModule_ProvideMicroActionDaoFactory(Provider<StoicusDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public MicroActionDao get() {
    return provideMicroActionDao(databaseProvider.get());
  }

  public static AppModule_ProvideMicroActionDaoFactory create(
      Provider<StoicusDatabase> databaseProvider) {
    return new AppModule_ProvideMicroActionDaoFactory(databaseProvider);
  }

  public static MicroActionDao provideMicroActionDao(StoicusDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideMicroActionDao(database));
  }
}
