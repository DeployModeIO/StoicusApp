package com.stoicus.app.di;

import com.stoicus.app.core.data.local.StoicusDatabase;
import com.stoicus.app.core.data.local.dao.PhilosophyQuoteDao;
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
public final class AppModule_ProvidePhilosophyQuoteDaoFactory implements Factory<PhilosophyQuoteDao> {
  private final Provider<StoicusDatabase> databaseProvider;

  public AppModule_ProvidePhilosophyQuoteDaoFactory(Provider<StoicusDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public PhilosophyQuoteDao get() {
    return providePhilosophyQuoteDao(databaseProvider.get());
  }

  public static AppModule_ProvidePhilosophyQuoteDaoFactory create(
      Provider<StoicusDatabase> databaseProvider) {
    return new AppModule_ProvidePhilosophyQuoteDaoFactory(databaseProvider);
  }

  public static PhilosophyQuoteDao providePhilosophyQuoteDao(StoicusDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providePhilosophyQuoteDao(database));
  }
}
