package com.stoicus.app;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.stoicus.app.core.data.local.StoicusDatabase;
import com.stoicus.app.core.data.local.dao.MicroActionDao;
import com.stoicus.app.core.data.local.dao.MoodEntryDao;
import com.stoicus.app.core.data.local.dao.PhilosophyQuoteDao;
import com.stoicus.app.core.data.local.dao.RitualSessionDao;
import com.stoicus.app.core.data.local.dao.StoicImageDao;
import com.stoicus.app.core.data.local.dao.StoicMusicDao;
import com.stoicus.app.core.data.local.dao.StreakRecordDao;
import com.stoicus.app.core.data.local.dao.UserProfileDao;
import com.stoicus.app.core.domain.usecase.GetAnalyticsDataUseCase;
import com.stoicus.app.core.domain.usecase.GetRitualExercisesUseCase;
import com.stoicus.app.core.domain.usecase.GetUserProfileUseCase;
import com.stoicus.app.core.domain.usecase.SaveMoodEntryUseCase;
import com.stoicus.app.core.domain.usecase.SaveRitualSessionUseCase;
import com.stoicus.app.core.domain.usecase.SaveUserProfileUseCase;
import com.stoicus.app.di.AppModule_ProvideDatabaseFactory;
import com.stoicus.app.di.AppModule_ProvideMicroActionDaoFactory;
import com.stoicus.app.di.AppModule_ProvideMoodEntryDaoFactory;
import com.stoicus.app.di.AppModule_ProvidePhilosophyQuoteDaoFactory;
import com.stoicus.app.di.AppModule_ProvideRitualSessionDaoFactory;
import com.stoicus.app.di.AppModule_ProvideStoicImageDaoFactory;
import com.stoicus.app.di.AppModule_ProvideStoicMusicDaoFactory;
import com.stoicus.app.di.AppModule_ProvideStreakRecordDaoFactory;
import com.stoicus.app.di.AppModule_ProvideUserProfileDaoFactory;
import com.stoicus.app.feature.analytics.AnalyticsViewModel;
import com.stoicus.app.feature.analytics.AnalyticsViewModel_HiltModules_KeyModule_ProvideFactory;
import com.stoicus.app.feature.evening.EveningRitualViewModel;
import com.stoicus.app.feature.evening.EveningRitualViewModel_HiltModules_KeyModule_ProvideFactory;
import com.stoicus.app.feature.gallery.GalleryViewModel;
import com.stoicus.app.feature.gallery.GalleryViewModel_HiltModules_KeyModule_ProvideFactory;
import com.stoicus.app.feature.micro.MicroActionsViewModel;
import com.stoicus.app.feature.micro.MicroActionsViewModel_HiltModules_KeyModule_ProvideFactory;
import com.stoicus.app.feature.morning.HomeViewModel;
import com.stoicus.app.feature.morning.HomeViewModel_HiltModules_KeyModule_ProvideFactory;
import com.stoicus.app.feature.morning.MorningRitualViewModel;
import com.stoicus.app.feature.morning.MorningRitualViewModel_HiltModules_KeyModule_ProvideFactory;
import com.stoicus.app.feature.music.MusicViewModel;
import com.stoicus.app.feature.music.MusicViewModel_HiltModules_KeyModule_ProvideFactory;
import com.stoicus.app.feature.onboarding.OnboardingViewModel;
import com.stoicus.app.feature.onboarding.OnboardingViewModel_HiltModules_KeyModule_ProvideFactory;
import com.stoicus.app.feature.philosophy.PhilosophyViewModel;
import com.stoicus.app.feature.philosophy.PhilosophyViewModel_HiltModules_KeyModule_ProvideFactory;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SetBuilder;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerStoicusApp_HiltComponents_SingletonC {
  private DaggerStoicusApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public StoicusApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements StoicusApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public StoicusApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements StoicusApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public StoicusApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements StoicusApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public StoicusApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements StoicusApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public StoicusApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements StoicusApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public StoicusApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements StoicusApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public StoicusApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements StoicusApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public StoicusApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends StoicusApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends StoicusApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends StoicusApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends StoicusApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return SetBuilder.<String>newSetBuilder(9).add(AnalyticsViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(EveningRitualViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(GalleryViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(HomeViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(MicroActionsViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(MorningRitualViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(MusicViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(OnboardingViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(PhilosophyViewModel_HiltModules_KeyModule_ProvideFactory.provide()).build();
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends StoicusApp_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AnalyticsViewModel> analyticsViewModelProvider;

    private Provider<EveningRitualViewModel> eveningRitualViewModelProvider;

    private Provider<GalleryViewModel> galleryViewModelProvider;

    private Provider<HomeViewModel> homeViewModelProvider;

    private Provider<MicroActionsViewModel> microActionsViewModelProvider;

    private Provider<MorningRitualViewModel> morningRitualViewModelProvider;

    private Provider<MusicViewModel> musicViewModelProvider;

    private Provider<OnboardingViewModel> onboardingViewModelProvider;

    private Provider<PhilosophyViewModel> philosophyViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private GetAnalyticsDataUseCase getAnalyticsDataUseCase() {
      return new GetAnalyticsDataUseCase(singletonCImpl.ritualSessionDao(), singletonCImpl.moodEntryDao(), singletonCImpl.streakRecordDao());
    }

    private SaveRitualSessionUseCase saveRitualSessionUseCase() {
      return new SaveRitualSessionUseCase(singletonCImpl.ritualSessionDao());
    }

    private SaveMoodEntryUseCase saveMoodEntryUseCase() {
      return new SaveMoodEntryUseCase(singletonCImpl.moodEntryDao());
    }

    private GetUserProfileUseCase getUserProfileUseCase() {
      return new GetUserProfileUseCase(singletonCImpl.userProfileDao());
    }

    private SaveUserProfileUseCase saveUserProfileUseCase() {
      return new SaveUserProfileUseCase(singletonCImpl.userProfileDao());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.analyticsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.eveningRitualViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.galleryViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.microActionsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.morningRitualViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.musicViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.onboardingViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.philosophyViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
    }

    @Override
    public Map<String, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(9).put("com.stoicus.app.feature.analytics.AnalyticsViewModel", ((Provider) analyticsViewModelProvider)).put("com.stoicus.app.feature.evening.EveningRitualViewModel", ((Provider) eveningRitualViewModelProvider)).put("com.stoicus.app.feature.gallery.GalleryViewModel", ((Provider) galleryViewModelProvider)).put("com.stoicus.app.feature.morning.HomeViewModel", ((Provider) homeViewModelProvider)).put("com.stoicus.app.feature.micro.MicroActionsViewModel", ((Provider) microActionsViewModelProvider)).put("com.stoicus.app.feature.morning.MorningRitualViewModel", ((Provider) morningRitualViewModelProvider)).put("com.stoicus.app.feature.music.MusicViewModel", ((Provider) musicViewModelProvider)).put("com.stoicus.app.feature.onboarding.OnboardingViewModel", ((Provider) onboardingViewModelProvider)).put("com.stoicus.app.feature.philosophy.PhilosophyViewModel", ((Provider) philosophyViewModelProvider)).build();
    }

    @Override
    public Map<String, Object> getHiltViewModelAssistedMap() {
      return Collections.<String, Object>emptyMap();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.stoicus.app.feature.analytics.AnalyticsViewModel 
          return (T) new AnalyticsViewModel(viewModelCImpl.getAnalyticsDataUseCase());

          case 1: // com.stoicus.app.feature.evening.EveningRitualViewModel 
          return (T) new EveningRitualViewModel(new GetRitualExercisesUseCase(), viewModelCImpl.saveRitualSessionUseCase(), viewModelCImpl.saveMoodEntryUseCase(), singletonCImpl.moodEntryDao());

          case 2: // com.stoicus.app.feature.gallery.GalleryViewModel 
          return (T) new GalleryViewModel(singletonCImpl.stoicImageDao());

          case 3: // com.stoicus.app.feature.morning.HomeViewModel 
          return (T) new HomeViewModel(viewModelCImpl.getUserProfileUseCase());

          case 4: // com.stoicus.app.feature.micro.MicroActionsViewModel 
          return (T) new MicroActionsViewModel(singletonCImpl.microActionDao());

          case 5: // com.stoicus.app.feature.morning.MorningRitualViewModel 
          return (T) new MorningRitualViewModel(new GetRitualExercisesUseCase(), viewModelCImpl.saveRitualSessionUseCase());

          case 6: // com.stoicus.app.feature.music.MusicViewModel 
          return (T) new MusicViewModel(singletonCImpl.stoicMusicDao());

          case 7: // com.stoicus.app.feature.onboarding.OnboardingViewModel 
          return (T) new OnboardingViewModel(viewModelCImpl.getUserProfileUseCase(), viewModelCImpl.saveUserProfileUseCase());

          case 8: // com.stoicus.app.feature.philosophy.PhilosophyViewModel 
          return (T) new PhilosophyViewModel(singletonCImpl.philosophyQuoteDao());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends StoicusApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends StoicusApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends StoicusApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<StoicusDatabase> provideDatabaseProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private RitualSessionDao ritualSessionDao() {
      return AppModule_ProvideRitualSessionDaoFactory.provideRitualSessionDao(provideDatabaseProvider.get());
    }

    private MoodEntryDao moodEntryDao() {
      return AppModule_ProvideMoodEntryDaoFactory.provideMoodEntryDao(provideDatabaseProvider.get());
    }

    private StreakRecordDao streakRecordDao() {
      return AppModule_ProvideStreakRecordDaoFactory.provideStreakRecordDao(provideDatabaseProvider.get());
    }

    private StoicImageDao stoicImageDao() {
      return AppModule_ProvideStoicImageDaoFactory.provideStoicImageDao(provideDatabaseProvider.get());
    }

    private UserProfileDao userProfileDao() {
      return AppModule_ProvideUserProfileDaoFactory.provideUserProfileDao(provideDatabaseProvider.get());
    }

    private MicroActionDao microActionDao() {
      return AppModule_ProvideMicroActionDaoFactory.provideMicroActionDao(provideDatabaseProvider.get());
    }

    private StoicMusicDao stoicMusicDao() {
      return AppModule_ProvideStoicMusicDaoFactory.provideStoicMusicDao(provideDatabaseProvider.get());
    }

    private PhilosophyQuoteDao philosophyQuoteDao() {
      return AppModule_ProvidePhilosophyQuoteDaoFactory.providePhilosophyQuoteDao(provideDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<StoicusDatabase>(singletonCImpl, 0));
    }

    @Override
    public void injectStoicusApp(StoicusApp stoicusApp) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.stoicus.app.core.data.local.StoicusDatabase 
          return (T) AppModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
