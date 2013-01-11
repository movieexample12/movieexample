package com.listmovie.movies;

import net.sf.jtmdb.Movie;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.listmovie.movies.activity.LoadMovieTrailerActivity;


@SuppressLint({ "UseSparseArrays" })
public class MovieInfoTask extends AsyncTask<Integer, Integer, Movie> {

	private static final String TAG = MovieInfoTask.class.getSimpleName();

	protected LoadMovieTrailerActivity parentActivity;
	
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
