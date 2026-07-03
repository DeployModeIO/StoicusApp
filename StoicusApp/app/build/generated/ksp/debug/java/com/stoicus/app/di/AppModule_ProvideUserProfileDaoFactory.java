package com.stoicus.app.di;

import com.stoicus.app.core.data.local.StoicusDatabase;
import com.stoicus.app.core.data.local.dao.UserProfileDao;
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
public final class AppModule_ProvideUserProfileDaoFactory implements Factory<UserProfileDao> {
  private final Provider<StoicusDatabase> databaseProvider;

  public AppModule_ProvideUserProfileDaoFactory(Provider<StoicusDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public UserProfileDao get() {
    return provideUserProfileDao(databaseProvider.get());
  }

  public static AppModule_ProvideUserProfileDaoFactory create(
      Provider<StoicusDatabase> databaseProvider) {
    return new AppModule_ProvideUserProfileDaoFactory(databaseProvider);
  }

  public static UserProfileDao provideUserProfileDao(StoicusDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideUserProfileDao(database));
  }
}
