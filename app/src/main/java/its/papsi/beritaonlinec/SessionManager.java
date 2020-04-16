package its.papsi.beritaonlinec;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private SharedPreferences preferences;
    public static final String KEY_USERID = "user_id";

    public SessionManager(Context context) {
        preferences = context.getSharedPreferences("prefSessionManager",
                Context.MODE_PRIVATE);
    }

    public void setUserId(String userId) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USERID, userId);
        editor.apply();
    }

    public void clearEditor() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public boolean isLoggedIn() {
        if (preferences.getString(KEY_USERID, null) == null) {
            return false;
        } else {
            return true;
        }
    }

    public String getUserId() {
        return preferences.getString(KEY_USERID, null);
    }
}
