package com.goldenchef.company.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by luo-hao on 2017-03-14.
 */

public class SharedPreferencesUtil {

    public static void preferenceCommit(Context context, String key, Object value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (value instanceof Boolean)
            editor.putBoolean(key, (Boolean) value);
        else if (value instanceof String)
            editor.putString(key, (String) value);
        editor.commit();
    }

    public static Object getPreference(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", MODE_PRIVATE);
        Map<String, Object> map = (Map<String, Object>) sharedPreferences.getAll();
        return map.get(key);
    }

}
