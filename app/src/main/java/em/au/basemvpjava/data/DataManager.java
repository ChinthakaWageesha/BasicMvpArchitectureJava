package em.au.basemvpjava.data;

import em.au.basemvpjava.data.remote.ApiService;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Wageesha Chinthaka on 18/02/2019
 */

@Singleton
public class DataManager {

  ApiService mApiService;

  @Inject
  public DataManager(ApiService apiService){this.mApiService = apiService;}

}
