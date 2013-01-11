package com.listmovie;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import net.sf.jtmdb.Movie;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.listmovie.R;
import com.listmovie.tasks.MovieInfoTask;
import com.listmovie.utils.MovieCache;
import com.listmovie.utils.MovieConstants;



public class TrailerActivity extends Activity {
	/* from JavaYoutubeDownloader*/
	public static URI getUri(String id) throws URISyntaxException {
		 List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		 qparams.add(new BasicNameValuePair("video_id", id));
		 qparams.add(new BasicNameValuePair("fmt", "18"));
		 String path = "get_video_info";
		 URI uri = URIUtils.createURI("http", "www.m.youtube.com", -1, "/" + path, URLEncodedUtils.format(qparams, "UTF-8"), null);
		 return uri;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.trailer);
	
		new MovieInfoTask() {/*load trailer to storage*/
    		
    		protected void onPostExecute(Movie result) {
    			super.onPostExecute(result);
    			/*load trailer to view*/
    			if (result != null && result.getTrailer() != null) {
    				
    				try {
    					String url = result.getTrailer().toString();
        				String id = url.substring(url.indexOf("?v=") + 3);
        				
						Uri uri = Uri.parse(getUri(id).toString());
						uri = Uri.parse(new URI(url).toString());
						Intent intent = new Intent(Intent.ACTION_VIEW, uri);
						TrailerActivity.this.startActivity(intent);
					} catch (Exception e) {
						Log.e("Wrong trailer url: ", String.valueOf(result.getTrailer()));
					}
    			}
    		};
    	}.execute(Integer.valueOf(this.getIntent().getStringExtra(MovieConstants.KEY_ID)));
//	        
        
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
