package em.au.basemvpjava.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import em.au.basemvpjava.Constants;
import em.au.basemvpjava.data.model.User;
import em.au.basemvpjava.injection.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Wageesha Chinthaka on 18/02/2019
 */
@Singleton
public class PreferenceHelper {


  private static final String PREF_FILE_NAME = "edot_file";

  private final SharedPreferences mPref;

  @Inject
  public PreferenceHelper(@ApplicationContext Context context) {
    mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
  }

  public void clear() {
    mPref.edit().clear().apply();
  }

  public static void saveToPreferences(Context context, User user, boolean userLoggedInState) {
    final SharedPreferences preferences = context.getSharedPreferences(
        Constants.PREFERENCES, Context.MODE_PRIVATE);
    final SharedPreferences.Editor editor = preferences.edit();

    try {
      editor.putString(
          Constants.PREF_KEY_ACCESS_TOKEN, user.token
      );
      editor.putInt(Constants.PREF_KEY_USER_ID, user.id
      );

      editor.putBoolean(
          Constants.PREF_KEY_USER_LOGGED_IN_STATE, userLoggedInState
      );

      editor.apply();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  public static void saveUser(Context context, User user) {
    final SharedPreferences preferences = context.getSharedPreferences(
        Constants.PREFERENCES, Context.MODE_PRIVATE);
    final SharedPreferences.Editor editor = preferences.edit();

    Gson gson = new Gson();
    String userJson = gson.toJson(user);
    editor.putString(Constants.PREF_KEY_USER, userJson);
    editor.apply();
  }

  public static boolean getUserLoggedInState(Context context) {
    final SharedPreferences prefs = getPreferences(context);

    return prefs.getBoolean(Constants.PREF_KEY_USER_LOGGED_IN_STATE, false);
  }

  public static String getAccessToken(Context context) {
    final SharedPreferences prefs = getPreferences(context);

    return  prefs.getString(Constants.PREF_KEY_ACCESS_TOKEN, "");
  }

  public static SharedPreferences getPreferences(Context context) {
    return context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
  }

  public static void removePreferences(Context context) {
    SharedPreferences preferences =
        context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);

    preferences.edit().remove(Constants.PREF_KEY_ACCESS_TOKEN).apply();
    preferences.edit().remove(Constants.PREF_KEY_USER_ID).apply();
    preferences.edit().putBoolean(Constants.PREF_KEY_USER_LOGGED_IN_STATE, false).apply();
  }

  public static void removeUser(Context context) {
    SharedPreferences preferences =
        context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);

    preferences.edit().remove(Constants.PREF_KEY_USER).apply();
  }

  public static String getUser(Context context) {
    final SharedPreferences prefs = getPreferences(context);
    String string = prefs.getString(Constants.PREF_KEY_USER, "");

    return string;
  }

  public static User getUserObject(Context context) {
    final SharedPreferences prefs = getPreferences(context);
    String jsonString = prefs.getString(Constants.PREF_KEY_USER, "");

    Gson gson = new Gson();

    User userObj = gson.fromJson(jsonString, User.class);

    return userObj;
  }

}
