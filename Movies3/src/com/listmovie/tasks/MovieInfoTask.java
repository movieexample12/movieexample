package com.listmovie.tasks;

import net.sf.jtmdb.Movie;
import android.os.AsyncTask;
import android.util.Log;

import com.listmovie.TrailerActivity;


public class MovieInfoTask extends AsyncTask<Integer, Integer, Movie> {

	private static final String TAG = MovieInfoTask.class.getSimpleName();

	protected TrailerActivity parentActivity;
	
	public Boolean taskEnded = Boolean.FALSE;
	
	@Override
	protected Movie doInBackground(Integer ... params) {
		Movie m = null;
		try {
			m = Movie.getInfo(params[0]);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		
		return m;
	}
	
	@Override
	protected void onPostExecute(Movie m) {
		taskEnded = Boolean.TRUE;
		super.onPostExecute(m);
	}

}
