package com.stoicus.app.feature.music;

import com.stoicus.app.core.data.local.dao.StoicMusicDao;
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
public final class MusicViewModel_Factory implements Factory<MusicViewModel> {
  private final Provider<StoicMusicDao> musicDaoProvider;

  public MusicViewModel_Factory(Provider<StoicMusicDao> musicDaoProvider) {
    this.musicDaoProvider = musicDaoProvider;
  }

  @Override
  public MusicViewModel get() {
    return newInstance(musicDaoProvider.get());
  }

  public static MusicViewModel_Factory create(Provider<StoicMusicDao> musicDaoProvider) {
    return new MusicViewModel_Factory(musicDaoProvider);
  }

  public static MusicViewModel newInstance(StoicMusicDao musicDao) {
    return new MusicViewModel(musicDao);
  }
}
