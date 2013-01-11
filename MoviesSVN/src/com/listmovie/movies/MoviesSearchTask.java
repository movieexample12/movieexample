package com.listmovie.movies;

import java.util.ArrayList;
import java.util.List;

import net.sf.jtmdb.GeneralSettings;
import net.sf.jtmdb.Movie;
import android.util.Log;

import com.listmovie.movies.activity.MainActivity;
import com.listmovie.utils.MovieCache;
import com.listmovie.utils.MovieConstants;

public class MoviesSearchTask extends AbstractTask<String, Integer, Boolean> {

	private static final String TAG = MoviesSearchTask.class.getSimpleName();

	protected MainActivity parentActivity;
	
	public Boolean taskEnded = Boolean.FALSE;
	
	protected List<Movie> getMovies(final String search_string) {
		List<Movie> movies = new ArrayList<Movie>();
		try {
			movies = Movie.search(search_string);
		} catch( Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return movies;
	}
	
	@Override
	protected Boolean doInBackground(String ... params) {
		GeneralSettings.setApiKey(MovieConstants.API_KEY);
		List<Movie> movies = getMovies(params[0]);
	    for (int i = 0; i < movies.size(); ++i) {
            Movie movie = movies.get(i);
            getMovieCache().addMoviePictures(movie);
        }
		return Boolean.TRUE;
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		taskEnded = result;
		super.onPostExecute(result);
	}
	
	public MoviesSearchTask(MainActivity parentActivity) {
		super();
		this.parentActivity = parentActivity;
	}
	
	public MovieCache getMovieCache() {
		return MovieCache.getMovieCache(parentActivity);
	}
	
}
