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
public final class GetUserProfileUseCase_Factory implements Factory<GetUserProfileUseCase> {
  private final Provider<UserProfileDao> userProfileDaoProvider;

  public GetUserProfileUseCase_Factory(Provider<UserProfileDao> userProfileDaoProvider) {
    this.userProfileDaoProvider = userProfileDaoProvider;
  }

  @Override
  public GetUserProfileUseCase get() {
    return newInstance(userProfileDaoProvider.get());
  }

  public static GetUserProfileUseCase_Factory create(
      Provider<UserProfileDao> userProfileDaoProvider) {
    return new GetUserProfileUseCase_Factory(userProfileDaoProvider);
  }

  public static GetUserProfileUseCase newInstance(UserProfileDao userProfileDao) {
    return new GetUserProfileUseCase(userProfileDao);
  }
}
