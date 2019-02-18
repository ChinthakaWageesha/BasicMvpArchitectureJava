package em.au.basemvpjava.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.LongSparseArray;
import android.view.View;
import android.widget.Toast;
import em.au.basemvpjava.BaseApplication;
import em.au.basemvpjava.R;
import em.au.basemvpjava.injection.component.ActivityComponent;
import em.au.basemvpjava.injection.component.ConfigPersistentComponent;
import em.au.basemvpjava.injection.component.DaggerConfigPersistentComponent;
import em.au.basemvpjava.injection.module.ActivityModule;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Wageesha Chinthaka on 18/02/2019
 */
public class BaseFragment extends Fragment {

  private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
  private static final AtomicLong NEXT_ID = new AtomicLong(0);
  private static final LongSparseArray<ConfigPersistentComponent>
      sComponentsMap = new LongSparseArray<>();
  public Toolbar toolbar;
  private ActivityComponent mActivityComponent;
  private long mActivityId;
  private Dialog mProgress;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Context mContext = getActivity();

    mActivityId = savedInstanceState != null ?
        savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();

    ConfigPersistentComponent configPersistentComponent = sComponentsMap.get(mActivityId, null);

    if (configPersistentComponent == null) {
      configPersistentComponent = DaggerConfigPersistentComponent.builder()
          .applicationComponent(BaseApplication.get(getActivity()).getComponent())
          .build();
      sComponentsMap.put(mActivityId, configPersistentComponent);
    }
    mActivityComponent =
        configPersistentComponent.activityComponent(new ActivityModule(getActivity()));

    //accessToken = "Bearer " + PreferenceHelper.getAccessToken(getContext());
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    toolbar = getActivity().findViewById(R.id.main_toolbar);
    toolbar.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorBlack));
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putLong(KEY_ACTIVITY_ID, mActivityId);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (toolbar != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      toolbar.setElevation(0);
    }
    sComponentsMap.remove(mActivityId);
  }

  public ActivityComponent activityComponent() {
    return mActivityComponent;
  }

  public void showToast(String message) {
    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
  }

  //public void showError(String message) {
  //  ViewDialog.showDialog(getContext(), getString(R.string.label_error), message,
  //      getString(R.string.label_ok));
  //}
  //
  //public void showDialog(String title, String message, String dismissText) {
  //  ViewDialog.showDialog(getContext(), title, message, dismissText);
  //}

  //protected void setTitle(String title, int choose) {
  //  //
  //  //  Typeface font = Typeface.SANS_SERIF;
  //  //  LayoutInflater inflator = LayoutInflater.from(getActivity());
  //  //  View v = inflator.inflate(R.layout.custom_text_view, null);
  //  //
  //  //  //if you need to customize anything else about the text, do it here.
  //  //  //I'm using a custom TextView with a custom font in my layout xml so all I need to do is set title
  //  //  TextView textView = ((TextView) v.findViewById(R.id.title));
  //  //  textView.setTypeface(font);
  //  //  textView.setText(getActivity().getTitle());
  //  //
  //  //  switch (choose) {
  //  //    case 1:
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar()
  //  //          .setHomeAsUpIndicator(R.drawable.ic_menu);
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowCustomEnabled(true);
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar().setCustomView(v);
  //  //      break;
  //  //    case 2:
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar()
  //  //          .setHomeAsUpIndicator(R.drawable.ic_back);
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar()
  //  //          .setDisplayShowCustomEnabled(false);
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
  //  //      break;
  //  //    case 3:
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar()
  //  //          .setHomeAsUpIndicator(R.drawable.ic_menu);
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar()
  //  //          .setDisplayShowCustomEnabled(false);
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
  //  //      ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
  //  //      break;
  //  //  }
  //  //}

}
