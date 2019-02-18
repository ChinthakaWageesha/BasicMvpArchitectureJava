package em.au.basemvpjava.injection.module;

import android.app.Activity;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import em.au.basemvpjava.injection.ActivityContext;

/**
 * Created by Wageesha Chinthaka on 18/02/2019
 */
@Module
public class ActivityModule {

  private Activity mActivity;

  public ActivityModule(Activity activity) {
    mActivity = activity;
  }

  @Provides
  Activity provideActivity() {
    return mActivity;
  }

  @Provides
  @ActivityContext
  Context providesContext() {
    return mActivity;
  }

}
