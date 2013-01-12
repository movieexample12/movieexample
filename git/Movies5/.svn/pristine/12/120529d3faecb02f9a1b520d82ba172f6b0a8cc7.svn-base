package com.listmovie.cinema.googleplace;

import java.io.Serializable;
import java.util.List;

import com.google.api.client.util.Key;

public class OpeningHours implements Serializable {
	  
	@Key
	public Boolean open_now;
	 
	@Key
	public List<Period> periods;

	@Override
	public String toString() {
		String s = "Open now: " + open_now; 
		if (periods != null) {
			s += periods.toString();
		}
		return s; 
	}
}
