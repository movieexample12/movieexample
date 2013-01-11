package com.listmovie.movies;

import net.sf.jtmdb.Movie;
import android.os.AsyncTask;

import com.listmovie.movies.activity.LoadMovieTrailerActivity;
import com.listmovie.utils.MovieCache;


public class LoadTrailerTask extends AsyncTask<Movie, Integer, Boolean> {

	protected LoadMovieTrailerActivity parentActivity = null;
	
	public Boolean taskEnded = Boolean.FALSE;
	
	@Override
	protected void onPostExecute(Boolean result) {
		taskEnded = result;
		super.onPostExecute(result);
	}
	
	protected MovieCache getCache() {
		return parentActivity.getMovieCache();
	}
	
	@Override
	protected Boolean doInBackground(Movie ... params) {
		return getCache().loadTrailer(params[0]);
	}
	
	public LoadTrailerTask(LoadMovieTrailerActivity parentActivity) {
		super();
		this.parentActivity = parentActivity;
	}

}
