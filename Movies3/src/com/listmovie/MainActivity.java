package com.listmovie;


import java.util.ArrayList;
import java.util.List;

import net.sf.jtmdb.Movie;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.SearchView;

import com.listmovie.R;
import com.listmovie.tasks.AbstractTask;
import com.listmovie.tasks.Adapter;
import com.listmovie.tasks.MoviesListTask;
import com.listmovie.tasks.MoviesSearchTask;
import com.listmovie.utils.MovieCache;
import com.listmovie.utils.MovieConstants;
import com.listmovie.utils.SharedPreferencesManager;

public class MainActivity extends ListActivity implements OnScrollListener {
	
	protected Adapter adapter = null;
	protected MovieCache cache;
	protected ListView list = null;
	protected List<Movie> movies = new ArrayList<Movie>();
	public Boolean taskEnded = Boolean.FALSE;
	
    private int pageNum = 1;
    private AbstractTask<? extends Object, ? extends Object, ? extends Object> task ;
    private AbstractTask<? extends Object, ? extends Object, ? extends Object> searchTask ;
	protected View footer;
	private boolean limitExtended = false;
	private boolean searchMode = false;
	private String  query = "";
	private SharedPreferencesManager sharedPreferencesManager;
	
	private SharedPreferencesManager getSharedPreferencesManager() {
		if (sharedPreferencesManager == null) {
			sharedPreferencesManager = new SharedPreferencesManager(this);
		}
		return sharedPreferencesManager;
	}
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        list = getListView();
	 	list.setAdapter(adapter);
	 	
        loadPage();
        list.setOnScrollListener(this);
        getSharedPreferencesManager().setLocale(getSharedPreferencesManager().getPreferences(MovieConstants.KEY_LOCALE, "en"));
        for (int i=0; i<movies.size(); ++i) {
        	getMovieCache().removeImages(i);
		}
		adapter = new Adapter(this);
	   
        
    }

    protected void loadPage() {
		
		if (searchMode) {
			if (searchTask == null || searchTask.taskEnded) {
				//TODO: set "loading" screen
				searchTask = new MoviesSearchTask(this){
	             	protected void onPostExecute(List<Movie> result) {
	             		super.onPostExecute(result);
	             		list.setAdapter(adapter);
	             		adapter.clear();
	              		adapter.notifyDataSetChanged();
	             		adapter.addMovies(result);
	            		adapter.notifyDataSetChanged();
	            		int index = list.getFirstVisiblePosition();
	            		int top = (list.getChildAt(0) == null) ? 0 : list.getChildAt(0).getTop();
	            		list.setSelectionFromTop(index, top);
	            		list.removeFooterView(footer);
	            		footer = null;
	             	};
	            };
	            ((MoviesSearchTask)searchTask).execute(query);
			}
		} else {
			if (task == null || task.taskEnded) {
				task = new MoviesListTask(this){
	             	protected void onPostExecute(List<net.sf.jtmdb.Movie> result) {
	             		super.onPostExecute(result);
	            		list.setAdapter(adapter);
	             		adapter.addMovies(result);
	            		adapter.notifyDataSetChanged();
	            		int index = list.getFirstVisiblePosition();
	            		int top = (list.getChildAt(0) == null) ? 0 : list.getChildAt(0).getTop();
	            		list.setSelectionFromTop(index, top);
	            		limitExtended = limitOfMoviesExtended();
	             		if (!limitExtended && footer == null) {
	             			footer = getLayoutInflater().inflate(R.layout.footer, null);
	                        list.addFooterView(footer);
	             		} else {
	             			list.removeFooterView(footer);
	             		}
	             	};
	            };
	            ((MoviesListTask)task).execute(pageNum);
	            pageNum += 1;
			}
 		}
    }
    
    public void onScroll(AbsListView view, int firstVisible, int visibleCount, int totalCount) {
		if (!searchMode && !limitExtended && (firstVisible + visibleCount >= totalCount)) {
		    loadPage();
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    SearchView searchView =
	             (SearchView) menu.findItem(R.id.search_menu).getActionView();
	    final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
	    	
		    @Override
		    public boolean onQueryTextChange(String newText) {
		        return true;
		    }

		    @Override		    
		    public boolean onQueryTextSubmit(String query) {
		        MainActivity.this.query = query;
		        MainActivity.this.searchMode = true;
		      
		        loadPage();
		        return true;
		    }
		};
	    if (searchView != null) {
	    	searchView.setOnQueryTextListener(queryTextListener);
	    }
	    
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    if (R.id.preferences_menu == item.getItemId()) {
        	Intent searchActivity = new Intent(this, PreferencesActivity.class);
	        this.startActivity(searchActivity);
	        return true;    
	    } else {
	    	return false;
	    }
	}
	
	@Override
	public void onBackPressed() {
		MainActivity.this.searchMode = false;
		for (int i=0; i<movies.size(); ++i) {
			getMovieCache().removeImages(i);
		}
		super.onBackPressed();
	}
	
	public MovieCache getMovieCache() {
		return MovieCache.getMovieCache(this);
	}
	
	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	Intent newActivity = new Intent(this, DetailsActivity.class);
    	Movie movie = (Movie)adapter.getItem(position);
    	newActivity.putExtra(MovieConstants.KEY_TITLE, movie.getName());
    	newActivity.putExtra(MovieConstants.KEY_DESCRIPTION, movie.getOverview());
    	if (movie.getReleasedDate() != null) {
    		newActivity.putExtra(MovieConstants.KEY_RELEASE_DATE, MovieConstants.SDF.format(movie.getReleasedDate()));
    	}
    	if (movie.getTrailer() != null) {
    		newActivity.putExtra(MovieConstants.KEY_VIDEO_SOURCE, movie.getTrailer());
    	}
    	newActivity.putExtra(MovieConstants.KEY_ID, movie.getID());
    	this.startActivity(newActivity);
    }
	
	public int countOfmovies () {
		return movies.size();
	}

	private void setLocationListener() {
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {
		    public void onLocationChanged(Location location) {

		    	Log.i("location", location.toString());
		    }

		    public void onStatusChanged(String provider, int status, Bundle extras) {
		    	Log.i("location", provider);
		    }

		    public void onProviderEnabled(String provider) {
		    	Log.i("location", provider);
		    }

		    public void onProviderDisabled(String provider) {
		    	Log.i("location", provider);
		    }
		};
		
		// Register the listener with the Location Manager to receive location updates
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		
//		Location lastKnown = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//
//		String.valueOf(lastKnown.getLatitude());
//		String.valueOf(lastKnown.getLongitude());
	}
	
}
