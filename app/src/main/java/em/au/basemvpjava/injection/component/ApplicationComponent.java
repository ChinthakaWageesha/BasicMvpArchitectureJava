package em.au.basemvpjava.injection.component;

import android.app.Application;
import android.content.Context;
import dagger.Component;
import em.au.basemvpjava.data.DataManager;
import em.au.basemvpjava.data.local.PreferenceHelper;
import em.au.basemvpjava.data.remote.ApiService;
import em.au.basemvpjava.injection.ApplicationContext;
import em.au.basemvpjava.injection.module.ApplicationModule;
import javax.inject.Singleton;

/**
 * Created by Wageesha Chinthaka on 18/02/2019
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

  @ApplicationContext
  Context context();

  Application application();

  ApiService apiService();

  DataManager dataManager();

  PreferenceHelper preferenceHelper();

}
