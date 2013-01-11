package com.listmovie.movies.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.listmovie.R;
import com.listmovie.utils.MovieCache;
import com.listmovie.utils.MovieConstants;
import com.listmovie.utils.SharedPreferencesManager;

public class PreferencesActivity extends Activity {
	
	SharedPreferencesManager sharedPreferencesManager;
	private SharedPreferencesManager getSharedPreferencesManager() {
		if (sharedPreferencesManager == null) {
			sharedPreferencesManager = new SharedPreferencesManager(this);
		}
		return sharedPreferencesManager;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preferences);
		//set saved by user settings
		CheckBox cb = (CheckBox)findViewById(R.id.memory_cb); 
		if (!getMovieCache().externalDeviceAvailable()) {
			getSharedPreferencesManager().addPreferences(MovieConstants.KEY_MEMORY_STORAGE, MovieConstants.KEY_INTERNAL);
			cb.setChecked(false);
			cb.setText(R.string.external_device_does_not_work);
		} else {
			cb.setChecked(getMovieCache().useExternalDevice());
		}
		
		RadioButton rb_en = (RadioButton)findViewById(R.id.rb_en);
		RadioButton rb_ua = (RadioButton)findViewById(R.id.rb_ua);
		
		rb_en.setChecked("en".equals(getSharedPreferencesManager().getPreferences(MovieConstants.KEY_LOCALE, "rr")));
		rb_ua.setChecked("ua".equals(getSharedPreferencesManager().getPreferences(MovieConstants.KEY_LOCALE, "rr")));

		final EditText editText = (EditText)findViewById(R.id.limitOfMovies);
		final String limit = getSharedPreferencesManager().getPreferences(MovieConstants.KEY_MOVIES_MAX_SIZE, MovieConstants.LIMIT_BY_DEFAULT);
		editText.setText(limit);
		
        final Button button = (Button)findViewById(R.id.limitButton);
        final PreferencesActivity activity = this;
        button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Integer n = 0;
				try {
					n = Integer.valueOf(editText.getText().toString());
				} catch (Exception ex) {
					Log.e("Wrong value", ex.getMessage());
				}
				if ( n > MovieConstants.MAX_LIMIT || n < MovieConstants.MIN_LIMIT ) {
					editText.setText(MovieConstants.LIMIT_BY_DEFAULT);
					new DialogFragment(){
						@Override
					    public Dialog onCreateDialog(Bundle savedInstanceState) {
					        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
					        builder.setMessage(R.string.title_dialog)
					               .setCancelable(false)
					               .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					                   public void onClick(DialogInterface dialog, int id) {
					                	   
					                   }
					               });
					        return builder.create();
					    }
					}.show(activity.getFragmentManager(), "List of movies");
					
				}
				getSharedPreferencesManager().addPreferences(MovieConstants.KEY_MOVIES_MAX_SIZE, String.valueOf(n));
			}
		});
	}

	public void cb_memoryChecked(View view) {
	    boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
        	getSharedPreferencesManager().addPreferences(MovieConstants.KEY_MEMORY_STORAGE, MovieConstants.KEY_EXTERNAL);
        } else {
        	getSharedPreferencesManager().addPreferences(MovieConstants.KEY_MEMORY_STORAGE, MovieConstants.KEY_INTERNAL);
        }
	}
	
	

	
	public void rb_uaLocaleChecked(View view) {
        if (((RadioButton) view).isChecked()) {
        	getSharedPreferencesManager().setLocale("ua");
        }
	}

	public void rb_enLocaleChecked(View view) {
	    
        if (((RadioButton) view).isChecked()) {
        	getSharedPreferencesManager().setLocale("en");
        }
	}
	
	public MovieCache getMovieCache() {
		return MovieCache.getMovieCache(this);
	}

}
