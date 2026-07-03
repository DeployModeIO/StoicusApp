package com.stoicus.app.feature.micro;

import com.stoicus.app.core.data.local.dao.MicroActionDao;
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
public final class MicroActionsViewModel_Factory implements Factory<MicroActionsViewModel> {
  private final Provider<MicroActionDao> microActionDaoProvider;

  public MicroActionsViewModel_Factory(Provider<MicroActionDao> microActionDaoProvider) {
    this.microActionDaoProvider = microActionDaoProvider;
  }

  @Override
  public MicroActionsViewModel get() {
    return newInstance(microActionDaoProvider.get());
  }

  public static MicroActionsViewModel_Factory create(
      Provider<MicroActionDao> microActionDaoProvider) {
    return new MicroActionsViewModel_Factory(microActionDaoProvider);
  }

  public static MicroActionsViewModel newInstance(MicroActionDao microActionDao) {
    return new MicroActionsViewModel(microActionDao);
  }
}
