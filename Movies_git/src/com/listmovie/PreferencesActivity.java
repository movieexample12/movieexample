package com.listmovie;

import android.annotation.SuppressLint ;
import android.app.Activity ;
import android.app.AlertDialog ;
import android.os.Bundle ;
import android.preference.EditTextPreference ;
import android.preference.Preference ;
import android.preference.PreferenceFragment ;
import android.text.InputType ;

import com.listmovie.utils.MovieConstants ;
import com.listmovie.utils.SharedPreferencesManager ;

@SuppressLint("ValidFragment")
public class PreferencesActivity extends Activity  {
    Activity activity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getFragmentManager().beginTransaction()
	        .replace(android.R.id.content, new PreferencesFragment())
	        .commit();
		activity = this;
		
	}
	
	class PreferencesFragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.preferences);
			EditTextPreference pref = (EditTextPreference)findPreference("limitOfMovies");
	        pref.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
	        pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                
                @Override
                public boolean onPreferenceChange( Preference preference, Object obj) {
                    try {
                        Integer newValue = Integer.valueOf(obj.toString());
                        if (newValue < MovieConstants.MIN_LIMIT || newValue > MovieConstants.MAX_LIMIT) {
                            throw new IllegalStateException(getString(R.string.limit_movies_wrong_message));
                        }
                    } catch(Exception ex) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setMessage(R.string.limit_movies_wrong_message)
                               .setTitle(R.string.exception_dialog_title);

                        AlertDialog dialog = builder.create();
                        dialog.show();
                        return false;   
                    }
                    return true;
                }
            });
		}
	}
	
	@Override
	public void onBackPressed() {
	    setLocale(getSharedPreferencesManager().getLanguage());
	    super.onBackPressed() ;
	}
	
	private void setLocale(String locale) {
        android.content.res.Configuration conf = getResources().getConfiguration();
        conf.locale = new java.util.Locale(locale);
        getResources().updateConfiguration(conf, getResources().getDisplayMetrics());
        
    }
	private SharedPreferencesManager sharedPreferencesManager;
	private SharedPreferencesManager getSharedPreferencesManager() {
        if (sharedPreferencesManager == null) {
            sharedPreferencesManager = new SharedPreferencesManager(this);
        }
        return sharedPreferencesManager;
    }

}
