package com.listmovie.utils;

import java.text.SimpleDateFormat;

public class MovieConstants {
	
	//public static final String URL = "http://api.themoviedb.org/2.1/Movie.browse/en-US/xml/ed2f89aa774281fcada8f17b73c8fa05?order_by=rating&order=desc&genres=18&min_votes=5&page=1&per_page=5";
	public static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yy");
	
	public static final String KEY_TITLE = "title";
    public static final String KEY_RELEASE_DATE = "release_date";
    public static final String KEY_ID = "id";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_ACTORS = "actors";
    public static final String KEY_MOVIE = "movie";
    
    public static final String API_KEY      = "ed2f89aa774281fcada8f17b73c8fa05";
    
    public static final String KEY_SHARED_PREFS = "shared_prefs";
    
    public static final String KEY_LOCALE = "locale";
    
    public static final String KEY_MEMORY_STORAGE = "storage";
    public static final String KEY_INTERNAL = "internal";
    public static final String KEY_VIDEO_SOURCE = "videoSource";
    public static final String KEY_EXTERNAL = "external";
    
    public static final String KEY_MOVIES_MAX_SIZE = "movies_max_size";
    public static final String LIMIT_BY_DEFAULT = "10";
    public static final int    MIN_LIMIT = 5;
    public static final int    MAX_LIMIT = 20;
}

