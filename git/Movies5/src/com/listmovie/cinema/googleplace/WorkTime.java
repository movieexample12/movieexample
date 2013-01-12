package com.listmovie.cinema.googleplace;

import java.io.Serializable;

import com.google.api.client.util.Key;

public class WorkTime implements Serializable {
	
	@Key
	public Integer day;
	 
	@Key
	public String time;

	@Override
	public String toString() {
		return "Day: " + day + ", time: " + time;
	}
}
