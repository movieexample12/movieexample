package com.listmovie.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager ;

public class SharedPreferencesManager implements IPreferences {
	
	private SharedPreferences settings;
	
	public SharedPreferencesManager(Context context) {
		settings = PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	public static final String KEY_DISTANCE_GPS = "distance_gps";
    public static final String KEY_LIMIT_OF_MOVIES = "limitOfMovies";
    public static final String KEY_PREF_LANGUAGE = "pref_languge";
    public static final String KEY_EXTERNAL_STORAGE = "external_storage";
    
	@Override
	public int getDistance() {
		return Integer.valueOf(settings.getString(MovieConstants.KEY_DISTANCE_GPS, 
		        String.valueOf(MovieConstants.DEFAULT_DISTANCE))).intValue();
	}

	@Override
	public int getLimitOfMovies() {
		return Integer.valueOf(settings.getString(MovieConstants.KEY_LIMIT_OF_MOVIES, 
		        String.valueOf(MovieConstants.MIN_LIMIT))).intValue();
	}

	@Override
	public String getLanguage() {
		return settings.getString(MovieConstants.KEY_PREF_LANGUAGE, null);
	}

	@Override
	public boolean useExternalStorage() {
		return settings.getBoolean(MovieConstants.KEY_EXTERNAL_STORAGE, false);
	}
	
}
