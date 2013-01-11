package com.listmovie.movies.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.listmovie.R;
import com.listmovie.utils.MovieCache;
import com.listmovie.utils.MovieConstants;

public class DetailsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        ImageView image =  (ImageView)findViewById(R.id.poster);
        Bitmap poster = getMovieCache().getPoster(this.getIntent().getIntExtra(MovieConstants.KEY_ID, -1));
        if (poster != null) {
        	image.setImageBitmap(poster);        	
        }
        
        TextView title = (TextView)findViewById(R.id.title);
        title.setText(this.getIntent().getStringExtra(MovieConstants.KEY_TITLE));
        
        TextView descr = (TextView)findViewById(R.id.description);
        descr.setText(this.getIntent().getStringExtra(MovieConstants.KEY_DESCRIPTION));
        
        TextView release = (TextView)findViewById(R.id.release);
        release.setText(this.getIntent().getStringExtra(MovieConstants.KEY_RELEASE_DATE));
        
//        String movie_url = "http://www.youtube.com/watch?v=2B3HGY_zLKk";// this.getIntent().getStringExtra(MovieConstants.KEY_VIDEO_SOURCE);
//        
//        VideoView video = (VideoView)findViewById(R.id.trailer);
//        if (movie_url != null) {
//        	
//            video.setMediaController(new MediaController(this));
//            video.setVideoPath(movie_url);
//        } else {
//        	((TextView)findViewById(R.id.trailer_label)).setText(R.string.no_trailer_label);
//        	video.setEnabled(false);
//        	
//        }
        
	}
	
	public MovieCache getMovieCache() {
		return MovieCache.getMovieCache(this);
	}

}
