package com.payu.payuapp.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nitin on 9/5/15.
 */
public class setSharedPreferences {

    static String PREF_FILE_NAME = "sharedPrefs";

    public static void saveToPreferences (Context context,String preferenceName,String preferenceValue){

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName,preferenceValue);
        editor.apply();

    }

    public static String readFromPreferences (Context context,String preferenceName,String defaultValue){

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME,context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName,defaultValue);

    }
}
