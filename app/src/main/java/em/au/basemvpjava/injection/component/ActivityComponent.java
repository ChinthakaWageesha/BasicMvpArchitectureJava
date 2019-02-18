package em.au.basemvpjava.injection.component;

import dagger.Subcomponent;
import em.au.basemvpjava.ui.SplashActivity;
import em.au.basemvpjava.injection.PerActivity;
import em.au.basemvpjava.injection.module.ActivityModule;

/**
 * Created by Wageesha Chinthaka on 18/02/2019
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

  void inject(SplashActivity splashActivity);
}
