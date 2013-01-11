package com.listmovie.cinema.googleplace;

import java.io.Serializable;
import java.util.List;

import com.google.api.client.util.Key;

public class GooglePlacesList implements Serializable {
	
	 @Key
	 public String status;
	 
	 @Key
	 public List<GooglePlace> results;
	
}
