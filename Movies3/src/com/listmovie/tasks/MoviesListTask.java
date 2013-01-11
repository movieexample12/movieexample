package com.listmovie.tasks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.jtmdb.BrowseOptions;
import net.sf.jtmdb.GeneralSettings;
import net.sf.jtmdb.Movie;
import android.annotation.SuppressLint;
import android.util.Log;

import com.listmovie.MainActivity;
import com.listmovie.utils.MovieCache;
import com.listmovie.utils.MovieConstants;
import com.listmovie.utils.SharedPreferencesManager;


@SuppressLint({ "SimpleDateFormat", "UseSparseArrays" })
public class MoviesListTask extends AbstractTask<Integer, Integer, List<Movie>> {

	private static final String TAG = MoviesListTask.class.getSimpleName();

	protected MainActivity parentActivity;
	
	
	SharedPreferencesManager sharedPreferencesManager;
	private SharedPreferencesManager getSharedPreferences() {
		if (sharedPreferencesManager == null) {
			sharedPreferencesManager = new SharedPreferencesManager(parentActivity);
		}
		return sharedPreferencesManager;
	}
	
	private Integer getMoviesLmit() {
		return Integer.valueOf(getSharedPreferences().getPreferences(MovieConstants.KEY_MOVIES_MAX_SIZE, MovieConstants.LIMIT_BY_DEFAULT));
	}
	
	public boolean limitOfMoviesExtended() {
		return parentActivity.countOfmovies() >= getMoviesLmit();
	}
	
	int pageInc = 1;
	
	private int getPerPageCount(int page) {
		Integer lim = getMoviesLmit();
		Integer def_lim = Integer.valueOf(MovieConstants.LIMIT_BY_DEFAULT);
		if (lim < def_lim) {
			return lim;
		} else if (lim - parentActivity.countOfmovies() < def_lim) { 
			lim = lim - parentActivity.countOfmovies();
			pageInc = Double.valueOf(def_lim.doubleValue()/lim.doubleValue()).intValue();
		} else {
			lim = def_lim;
		}
		return lim;
	}
	

	protected List<net.sf.jtmdb.Movie> getMovies(final Integer page) {
		
		List<net.sf.jtmdb.Movie> movies = new ArrayList<net.sf.jtmdb.Movie>();
		if (!limitOfMoviesExtended()) {
			try {
				movies = net.sf.jtmdb.Movie.browse(new BrowseOptions(){
				
					private static final long serialVersionUID = -4620142785033329241L;
	
					@Override
					public ORDER_BY getOrderBy() {
						return ORDER_BY.RATING;
					}
					
					@Override
					public ORDER getOrder() {
						return ORDER.DESC;
					}
					
					@Override
					public Set<Integer> getGenres() {
						Set<Integer> genres = new HashSet<Integer>();
						genres.add(18);
						return genres;
					}
					
					@Override
					public Integer getMinVotes() {
						return 5;
					}
					
					@Override
					public Integer getPerPage() {
						return getPerPageCount(page) ;
					}
					
					@Override
					public Integer getPage() {
						return page;
					}
					
				});
			} catch( Exception e) {
				Log.e(TAG, e.getMessage());
			}
		}
		return movies;
	}
	
	@Override
	protected List<net.sf.jtmdb.Movie> doInBackground(Integer ... params) {
		GeneralSettings.setApiKey(MovieConstants.API_KEY);
		List<net.sf.jtmdb.Movie> movies = getMovies(params[0]);
		for (int i = 0; i < movies.size() ; ++i) {
            getMovieCache().addMoviePictures(movies.get(i));
        }
		return movies;
	}
	
	public MoviesListTask(MainActivity parentActivity) {
		super();
		this.parentActivity = parentActivity;
	}
	
	public MovieCache getMovieCache() {
		return MovieCache.getMovieCache(parentActivity);
	}
	
}
