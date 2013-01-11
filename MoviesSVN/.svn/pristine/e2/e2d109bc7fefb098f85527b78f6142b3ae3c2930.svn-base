package com.listmovie.utils;

import java.util.Locale;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
	
	Context context;
	
	public SharedPreferencesManager(Context context) {
		this.context = context;
	}
	
	private SharedPreferences getSharedPreferences() {
		return context.getSharedPreferences(MovieConstants.KEY_SHARED_PREFS, 0);
	}
	
	public  String getPreferences(String key, String dv) {
 		SharedPreferences settings = getSharedPreferences();
	    return settings.getString(key,  dv);	
	}
	
	public  void addPreferences(String key, String value) {
		SharedPreferences settings = getSharedPreferences();
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
	}
	
	public void setLocale(String locale) {
		android.content.res.Configuration conf = context.getResources().getConfiguration();
        conf.locale = new Locale(locale);
        context.getResources().updateConfiguration(conf, context.getResources().getDisplayMetrics());
        addPreferences(MovieConstants.KEY_LOCALE, locale);
	}
}
