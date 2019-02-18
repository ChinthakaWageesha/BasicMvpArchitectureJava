package em.au.basemvpjava.injection.module;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import em.au.basemvpjava.data.remote.ApiService;
import em.au.basemvpjava.injection.ApplicationContext;
import javax.inject.Singleton;

/**
 * Created by Wageesha Chinthaka on 18/02/2019
 */
@Module
public class ApplicationModule {

  private final Application mApplication;

  public ApplicationModule(Application application) {
    mApplication = application;
  }

  @Provides
  Application provideApplication() {
    return mApplication;
  }

  @Provides
  @ApplicationContext
  Context provideContext() {
    return mApplication;
  }

  @Provides
  @Singleton
  ApiService provideService() {
    return ApiService.Creator.newApiService();
  }

}
