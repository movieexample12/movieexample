package com.listmovie.cinema.googleplace;

import java.io.Serializable;

import com.google.api.client.util.Key;

public class GooglePlaceDetails implements Serializable {
	  @Key
	  public String status;
	 
	  @Key
	  public GooglePlace result;
	 
	  @Override
	  public String toString() {
	      if (result!=null) {
	          return result.toString();
	      }
	      return super.toString();
	  }
}
