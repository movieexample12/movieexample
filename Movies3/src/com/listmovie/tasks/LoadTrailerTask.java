package com.listmovie.tasks;

import net.sf.jtmdb.Movie;
import android.os.AsyncTask;
import android.util.Log;

import com.listmovie.TrailerActivity;
import com.listmovie.utils.MovieCache;


public class LoadTrailerTask extends AsyncTask<Integer, Integer, Integer> {//return  movie_id

	private static final String TAG = LoadTrailerTask.class.getSimpleName();
	
	protected TrailerActivity parentActivity = null;
	
	public Boolean taskEnded = Boolean.FALSE;
	
	@Override
	protected void onPostExecute(Integer result) {
		taskEnded = Boolean.TRUE;
		super.onPostExecute(result);
	}
	
	protected MovieCache getCache() {
		return parentActivity.getMovieCache();
	}
	
	@Override
	protected Integer doInBackground(Integer ... params) {
		try {
			if (getCache().loadTrailer(Movie.getInfo(params[0]))) {
				return params[0];
			}
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}
	
	public LoadTrailerTask(TrailerActivity parentActivity) {
		super();
		this.parentActivity = parentActivity;
	}

}
