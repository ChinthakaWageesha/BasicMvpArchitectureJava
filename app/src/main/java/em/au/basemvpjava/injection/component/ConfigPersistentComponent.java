package em.au.basemvpjava.injection.component;

import dagger.Component;
import em.au.basemvpjava.injection.ConfigPersistent;
import em.au.basemvpjava.injection.module.ActivityModule;

/**
 * Created by Wageesha Chinthaka on 18/02/2019
 */
@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {
  ActivityComponent activityComponent(ActivityModule activityModule);
}
