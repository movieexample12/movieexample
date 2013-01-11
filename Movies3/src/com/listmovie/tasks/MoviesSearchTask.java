package com.listmovie.tasks;

import java.util.ArrayList;
import java.util.List;

import net.sf.jtmdb.GeneralSettings;
import net.sf.jtmdb.Movie;
import android.util.Log;

import com.listmovie.MainActivity;
import com.listmovie.utils.MovieCache;
import com.listmovie.utils.MovieConstants;

public class MoviesSearchTask extends AbstractTask<String, Integer, List<net.sf.jtmdb.Movie>> {

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
	protected List<net.sf.jtmdb.Movie> doInBackground(String ... params) {
		GeneralSettings.setApiKey(MovieConstants.API_KEY);
		List<net.sf.jtmdb.Movie> movies = getMovies(params[0]);
	    for (int i = 0; i < movies.size(); ++i) {
            Movie movie = movies.get(i);
            getMovieCache().addMoviePictures(movie);
        }
		return movies;
	}
	

	public MoviesSearchTask(MainActivity parentActivity) {
		super();
		this.parentActivity = parentActivity;
	}
	
	public MovieCache getMovieCache() {
		return MovieCache.getMovieCache(parentActivity);
	}
	
}