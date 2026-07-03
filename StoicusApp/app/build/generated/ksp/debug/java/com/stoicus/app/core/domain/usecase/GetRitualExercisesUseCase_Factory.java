package com.stoicus.app.core.domain.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class GetRitualExercisesUseCase_Factory implements Factory<GetRitualExercisesUseCase> {
  @Override
  public GetRitualExercisesUseCase get() {
    return newInstance();
  }

  public static GetRitualExercisesUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GetRitualExercisesUseCase newInstance() {
    return new GetRitualExercisesUseCase();
  }

  private static final class InstanceHolder {
    private static final GetRitualExercisesUseCase_Factory INSTANCE = new GetRitualExercisesUseCase_Factory();
  }
}
