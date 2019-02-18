package em.au.basemvpjava.ui.base;

/**
 * Created by Wageesha Chinthaka on 18/02/2019
 */
public interface Presenter<V extends MvpView>{

  void attachView(V mapView);

  void detachView();
}
