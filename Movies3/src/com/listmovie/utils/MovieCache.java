package com.listmovie.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jtmdb.Movie;
import net.sf.jtmdb.MoviePoster;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

public class MovieCache {
	
	private static final String TAG = MovieCache.class.getSimpleName();
	private static final String IMAGES_LIST_KEY       = "posters";
	private static final String THUMB_KEY			  = "thumb";
	private static final String POSTER_IMAGE_KEY      = "cover";
	private static final String EXTERNAL_FOLDER		  = "pics";
	private static final String SEPARATOR 			  = "/"; 
	private static final String PICTURE_EXTENSION	  = ".jpg"; 
	private static final String TRAILER				  = "trailer";
	private static final String TRAILER_EXTENSION     = ".mp4";
	private static final String MP4_TO_URL			  =  "&fmt=18";
	
	private static final String scheme = "http";
	private static final String host = "www.youtube.com";
			 
	private Context context;
	
	private static Map<Activity, MovieCache> actionMap = new HashMap<Activity, MovieCache>();
	
	private MovieCache(Context c) {
		context = c;
	}
	
    private String getShortName(String type, int id) {
    	return  type + id + PICTURE_EXTENSION;
    }
    
	private File getExternalFile(String filename) {
		String pathToExternalStorage = Environment.getExternalStorageDirectory().toString() + SEPARATOR + EXTERNAL_FOLDER;
    	File appDirectory = new File(pathToExternalStorage);   
    	if (!appDirectory.exists()) {
    		appDirectory.mkdirs();
    	}
    	return new File(pathToExternalStorage + SEPARATOR + filename);
	}
	
	private void saveFile(String saveTo, URL url) {
		OutputStream os = null;
		InputStream is = null;
		try {
			is = url.openConnection().getInputStream();
		    if (useExternalDevice()) {
		    	os = new FileOutputStream(getExternalFile(saveTo));
		    } else {
		    	os = context.openFileOutput(saveTo, Context.MODE_PRIVATE);
		    }
		    byte[] buffer = new byte[1024];
            int len1 = 0;
            if(is != null){
               while ((len1 = is.read(buffer)) > 0) {
                     os.write(buffer,0, len1);   
               }
               os.flush();
            }
            if(os != null){
               os.close();
            }
            
	        is.close();
	        os.close();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		} 
	}
	
	public void saveMovie(String saveTo, String id) {
		FileOutputStream os = null;
		InputStream is = null;
		try {
			String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13";
			
			List<NameValuePair> qparams = new ArrayList<NameValuePair>();
			qparams.add(new BasicNameValuePair("video_id", id));
			qparams.add(new BasicNameValuePair("fmt", "" + 18));
			URI uri = URIUtils.createURI(scheme, host, -1, "/" + "get_video_info", URLEncodedUtils.format(qparams, "UTF-8"), null);
//			URI uri = JavaYoutubeDownloader.getUri("get_video_info", qparams);
			
			HttpURLConnection con = (HttpURLConnection)uri.toURL().openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", userAgent);
			con.setRequestProperty("Accept", "text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
			con.setRequestProperty("Connection", "keep-alive");
			con.connect();
			is = new BufferedInputStream(con.getInputStream());
			
		    if (useExternalDevice()) {
		    	os = new FileOutputStream(getExternalFile(saveTo));
		    } else {
		    	os = context.openFileOutput(saveTo, Context.MODE_PRIVATE);
		    }
		    byte[] buffer = new byte[1024];
            int len1 = 0;
            if(is != null){
               while ((len1 = is.read(buffer)) > 0) {
                     os.write(buffer,0, len1);   
               }
               os.flush();
            }
            if(os != null){
               os.close();
            }
            
	        is.close();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		} 

	}
	
	
	private File getFile(String name) {
		File f = null ;
		if (useExternalDevice()) {
			f = getExternalFile(name);  
		} else {
			f = context.getFileStreamPath (name);
		}
		return f;
		
	}
	
	private String getShortTrailerFilePath(Integer movieId) {
		return TRAILER + movieId + TRAILER_EXTENSION;
	}
	
	public Boolean useExternalDevice() {
 		SharedPreferences settings = context.getSharedPreferences(MovieConstants.KEY_SHARED_PREFS, 0);
	    //return MovieConstants.KEY_EXTERNAL.equals(settings.getString(MovieConstants.KEY_MEMORY_STORAGE,  MovieConstants.KEY_INTERNAL)) && externalDeviceAvailable();	
 		return true;
	}
	
	public void addMoviePictures(Movie movie) {
		MoviePoster images = movie.getImages().posters.iterator().next();
		saveFile(getShortName(THUMB_KEY, movie.getID()), 
				images.getSmallestImage());
		saveFile(getShortName(POSTER_IMAGE_KEY, movie.getID()), 
				images.getImage(MoviePoster.Size.COVER));
	}
	
	public Bitmap getImage(int position, String key) {
		File f = getFile(getShortName(key, position));
		if (!f.exists()) {
		    return null;
		}
	    return BitmapFactory.decodeFile(f.getAbsolutePath());
	}
	
	public Bitmap getPoster(int position) {
		return getImage(position, POSTER_IMAGE_KEY);
	}
	
	public Bitmap getThumb(int position) {
		return getImage(position, THUMB_KEY);
	}
	
	public void removeImages(int i) {
		File [] fs = new File[] {getFile(getShortName(THUMB_KEY, i)), getFile(getShortName(IMAGES_LIST_KEY, i))};
		for (File f: fs) {
			f.delete();
		}
	}
	
	public boolean externalDeviceAvailable() {
		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
		    // We can read and write the media
		    mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
		    // We can only read the media
		    mExternalStorageAvailable = true;
		    mExternalStorageWriteable = false;
		} else {
		    // Something else is wrong. It may be one of many other states, but all we need
		    //  to know is we can neither read nor write
		    mExternalStorageAvailable = mExternalStorageWriteable = false;
		}
		
		return mExternalStorageAvailable && mExternalStorageWriteable;
	}
	
	public Boolean loadTrailer(Movie movie) {// return trailerSavedPath
	    try {
			//URL url = movie.getTrailer();
			URL mp4Url = new URL("http://youtube.googleapis.com/v/2b3hgy_zlkk");
					//url.toString());
			String saveTo = "test.mp4";//getShortTrailerFilePath(movie.getID());
		//	saveMovie(saveTo, mp4Url);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			return Boolean.FALSE;
		}
	    return Boolean.TRUE;
	}
	
	public File getTrailerFile(Integer movieId) {
		return getFile(getShortTrailerFilePath(movieId));
	}
	
	public static MovieCache getMovieCache(Activity activity) {
		if (!actionMap.containsKey(activity)) {
			actionMap.put(activity, new MovieCache(activity));
		}
		return actionMap.get(activity);
	}
	
}
