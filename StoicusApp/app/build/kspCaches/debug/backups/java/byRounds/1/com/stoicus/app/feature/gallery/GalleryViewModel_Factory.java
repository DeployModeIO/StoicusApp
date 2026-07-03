package com.stoicus.app.feature.gallery;

import com.stoicus.app.core.data.local.dao.StoicImageDao;
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
public final class GalleryViewModel_Factory implements Factory<GalleryViewModel> {
  private final Provider<StoicImageDao> imageDaoProvider;

  public GalleryViewModel_Factory(Provider<StoicImageDao> imageDaoProvider) {
    this.imageDaoProvider = imageDaoProvider;
  }

  @Override
  public GalleryViewModel get() {
    return newInstance(imageDaoProvider.get());
  }

  public static GalleryViewModel_Factory create(Provider<StoicImageDao> imageDaoProvider) {
    return new GalleryViewModel_Factory(imageDaoProvider);
  }

  public static GalleryViewModel newInstance(StoicImageDao imageDao) {
    return new GalleryViewModel(imageDao);
  }
}
