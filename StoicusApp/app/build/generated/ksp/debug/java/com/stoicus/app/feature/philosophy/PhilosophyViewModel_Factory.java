package com.stoicus.app.feature.philosophy;

import com.stoicus.app.core.data.local.dao.PhilosophyQuoteDao;
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
public final class PhilosophyViewModel_Factory implements Factory<PhilosophyViewModel> {
  private final Provider<PhilosophyQuoteDao> quoteDaoProvider;

  public PhilosophyViewModel_Factory(Provider<PhilosophyQuoteDao> quoteDaoProvider) {
    this.quoteDaoProvider = quoteDaoProvider;
  }

  @Override
  public PhilosophyViewModel get() {
    return newInstance(quoteDaoProvider.get());
  }

  public static PhilosophyViewModel_Factory create(Provider<PhilosophyQuoteDao> quoteDaoProvider) {
    return new PhilosophyViewModel_Factory(quoteDaoProvider);
  }

  public static PhilosophyViewModel newInstance(PhilosophyQuoteDao quoteDao) {
    return new PhilosophyViewModel(quoteDao);
  }
}
