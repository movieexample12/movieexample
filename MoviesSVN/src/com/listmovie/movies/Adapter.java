package com.listmovie.movies;

import java.util.ArrayList;
import java.util.List;

import net.sf.jtmdb.Movie;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.listmovie.R;
import com.listmovie.utils.MovieCache;
import com.listmovie.utils.MovieConstants;

public class Adapter extends BaseAdapter {
	
	private List<Movie> movies = new ArrayList<Movie>();
	
	private Activity activity;
    private LayoutInflater inflater;
    public Adapter(Activity a) {
    	activity = a;
    }
    
    public void addMovies(List<Movie> movies) {
    	this.movies.addAll(movies);
    }
    
    public void clear() {
    	this.movies.clear();
    }
    
    @Override
	public int getCount() {
		return movies.size();
	}

	@Override
	public Object getItem(int position) {
		return movies.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	static class ViewHolder {
	    TextView title;
	    TextView duration;
	    ImageView image;
	    int position;
    }
	
	private MovieCache getCache() {
		return MovieCache.getMovieCache(activity);
	}

	@Override
	public View getView(final int position, View vi, final ViewGroup parent) {
		ViewHolder holder = new ViewHolder();
		if (vi == null) {
			if (inflater == null) {
				inflater = activity.getLayoutInflater();
			}
			vi = inflater.inflate(R.layout.list_item, parent, false);
			holder.image = (ImageView) vi.findViewById(R.id.image);
			holder.title = (TextView) vi.findViewById(R.id.title);
			holder.duration = (TextView) vi.findViewById(R.id.duration);
			vi.setTag(holder);
        } else {
        	holder = (ViewHolder) vi.getTag(); 
        }
	 
        Movie movie = (Movie)getItem(position);
        if (movie != null) {
	        holder.title.setText(String.valueOf(movie.getName()));
	        if (movie.getReleasedDate() != null) {
	        	holder.duration.setText(MovieConstants.SDF.format(movie.getReleasedDate()));
	        }
	        holder.image.setImageBitmap(getCache().getThumb(movie.getID()));
        }
        return vi;
	}
	
	
}