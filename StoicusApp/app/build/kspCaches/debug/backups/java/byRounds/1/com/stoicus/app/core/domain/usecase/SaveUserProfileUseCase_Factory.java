package com.stoicus.app.core.domain.usecase;

import com.stoicus.app.core.data.local.dao.UserProfileDao;
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
public final class SaveUserProfileUseCase_Factory implements Factory<SaveUserProfileUseCase> {
  private final Provider<UserProfileDao> userProfileDaoProvider;

  public SaveUserProfileUseCase_Factory(Provider<UserProfileDao> userProfileDaoProvider) {
    this.userProfileDaoProvider = userProfileDaoProvider;
  }

  @Override
  public SaveUserProfileUseCase get() {
    return newInstance(userProfileDaoProvider.get());
  }

  public static SaveUserProfileUseCase_Factory create(
      Provider<UserProfileDao> userProfileDaoProvider) {
    return new SaveUserProfileUseCase_Factory(userProfileDaoProvider);
  }

  public static SaveUserProfileUseCase newInstance(UserProfileDao userProfileDao) {
    return new SaveUserProfileUseCase(userProfileDao);
  }
}
