package com.listmovie;

import android.app.Activity;
import android.app.AlertDialog ;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri ;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View ;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.listmovie.utils.MovieCache;
import com.listmovie.utils.MovieConstants;

public class DetailsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        ImageView image =  (ImageView)findViewById(R.id.poster);
        MovieCache mc = MovieCache.getCacheFromMap(this.getIntent().getStringExtra(MovieConstants.KEY_CALLED_ACTIVITY));
        Bitmap poster = mc.getPoster(this.getIntent().getIntExtra(MovieConstants.KEY_ID, -1), this);
        if (poster != null) {
        	image.setImageBitmap(poster);        	
        }
        
        TextView title = (TextView)findViewById(R.id.title);
        title.setText(getIntent().getStringExtra(MovieConstants.KEY_TITLE));
        
        TextView descr = (TextView)findViewById(R.id.description);
        descr.setText(getIntent().getStringExtra(MovieConstants.KEY_DESCRIPTION));
        
        TextView release = (TextView)findViewById(R.id.release);
        release.setText(getIntent().getStringExtra(MovieConstants.KEY_RELEASE_DATE));
        
        TextView casts = (TextView)findViewById(R.id.casts);
        String castsS = getIntent().getStringExtra(MovieConstants.KEY_CASTS);
        if (castsS != null) {
             casts.setText(castsS); 	
        }
        
        TextView runtime = (TextView)findViewById(R.id.runtime);
        runtime.setText(this.getIntent().getStringExtra(MovieConstants.KEY_RUNTIME));
        
        TextView fanRating = (TextView)findViewById(R.id.rating);
        fanRating.setText(this.getIntent().getStringExtra(MovieConstants.KEY_RATING));
        
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
		    	Intent main = new Intent(DetailsActivity.this, MainActivity.class);
		    	main.putExtra(MovieConstants.KEY_SEARCH, query);
		    	DetailsActivity.this.startActivity(main);
		        return true;
		    }
		};
	    if (searchView != null) {
	    	searchView.setOnQueryTextListener(queryTextListener);
	    }
	    
	    return super.onCreateOptionsMenu(menu);
	}
	
	public void watchTrailer(View view) {
	    String url = getIntent().getStringExtra(MovieConstants.KEY_VIDEO_SOURCE);
	    if (url == null) {
	        AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        builder.setMessage(R.string.null_trailer_dialog_message)
	               .setTitle(R.string.exception_dialog_title);
	        AlertDialog dialog = builder.create();
	        dialog.show();
	    } else {
	        Intent i = new Intent(Intent.ACTION_VIEW);
	        i.setData(Uri.parse(url));
	        startActivity(i);
	    }
	}

}
