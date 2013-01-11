package com.listmovie.cinema.googleplace;

import java.io.Serializable;

import com.google.api.client.util.Key;

public class Period implements Serializable {
	
	@Key
	public WorkTime close;
	 
	@Key
	public WorkTime open;
	 
	@Override
	public String toString() {
		if (open == null || close == null) {
			return "";
		} else {
		    return "Day: " + open.day + ", open: "+ open.time + ", close: " + close.time + ". \n";
		}
	}
}
