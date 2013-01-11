package com.listmovie;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        final int ID = this.getIntent().getIntExtra(MovieConstants.KEY_ID, -1);
        Bitmap poster = getMovieCache().getPoster(ID);
        if (poster != null) {
        	image.setImageBitmap(poster);        	
        }
        
        TextView title = (TextView)findViewById(R.id.title);
        title.setText(this.getIntent().getStringExtra(MovieConstants.KEY_TITLE));
        
        TextView descr = (TextView)findViewById(R.id.description);
        descr.setText(this.getIntent().getStringExtra(MovieConstants.KEY_DESCRIPTION));
        
        TextView release = (TextView)findViewById(R.id.release);
        release.setText(this.getIntent().getStringExtra(MovieConstants.KEY_RELEASE_DATE));
        

        final Button button = (Button) findViewById(R.id.trailer_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent newActivity = new Intent(DetailsActivity.this, TrailerActivity.class);
            	newActivity.putExtra(MovieConstants.KEY_ID, String.valueOf(ID));
            	DetailsActivity.this.startActivity(newActivity);
            }
        });
        
	}
	
	public MovieCache getMovieCache() {
		return MovieCache.getMovieCache(this);
	}

}
