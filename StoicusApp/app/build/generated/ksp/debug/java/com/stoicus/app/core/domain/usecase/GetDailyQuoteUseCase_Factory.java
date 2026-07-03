package com.stoicus.app.core.domain.usecase;

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
public final class GetDailyQuoteUseCase_Factory implements Factory<GetDailyQuoteUseCase> {
  private final Provider<PhilosophyQuoteDao> philosophyQuoteDaoProvider;

  public GetDailyQuoteUseCase_Factory(Provider<PhilosophyQuoteDao> philosophyQuoteDaoProvider) {
    this.philosophyQuoteDaoProvider = philosophyQuoteDaoProvider;
  }

  @Override
  public GetDailyQuoteUseCase get() {
    return newInstance(philosophyQuoteDaoProvider.get());
  }

  public static GetDailyQuoteUseCase_Factory create(
      Provider<PhilosophyQuoteDao> philosophyQuoteDaoProvider) {
    return new GetDailyQuoteUseCase_Factory(philosophyQuoteDaoProvider);
  }

  public static GetDailyQuoteUseCase newInstance(PhilosophyQuoteDao philosophyQuoteDao) {
    return new GetDailyQuoteUseCase(philosophyQuoteDao);
  }
}
