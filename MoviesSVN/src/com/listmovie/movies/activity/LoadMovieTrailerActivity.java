package com.listmovie.movies.activity;

import net.sf.jtmdb.Movie;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.listmovie.R;
import com.listmovie.movies.LoadTrailerTask;
import com.listmovie.movies.MovieInfoTask;
import com.listmovie.utils.MovieCache;
import com.listmovie.utils.MovieConstants;

public class LoadMovieTrailerActivity extends Activity {
	
	protected MovieCache cache;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.trailer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        new MovieInfoTask(){/*get trailer url*/
	        protected void onPostExecute(final Movie m) {
	        	new LoadTrailerTask(LoadMovieTrailerActivity.this) {/*load trailer to storage*/
	        		
	        		protected void onPostExecute(Boolean result) {
	        			/*load trailer to view*/
	        			String movie_url = getMovieCache().getTrailerFile(m.getID()).getAbsolutePath();
	        			VideoView video = (VideoView)findViewById(R.id.trailer);
	        	        if (movie_url != null) {
	        	        	
	        	            video.setMediaController(new MediaController(LoadMovieTrailerActivity.this));
	        	            video.setVideoPath(movie_url);
	        	        } else {
	        	        	/*show some information to user about trailer problem*/
	        	        	video.setVideoURI(Uri.EMPTY);
	        	        }
	        	        
	        		};
	        		
	        	}.execute(m);
	        };
        }.execute(Integer.valueOf(this.getIntent().getStringExtra(MovieConstants.KEY_ID)));
        
	}
	
	@Override
	public void onBackPressed() {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onBackPressed();
	}

	public MovieCache getMovieCache() {
		return MovieCache.getMovieCache(this);
	}
}
