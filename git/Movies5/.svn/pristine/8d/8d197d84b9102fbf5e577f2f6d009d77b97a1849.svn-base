package com.listmovie.cinema.ticket;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

import com.listmovie.cinema.database.Cinema;
import com.listmovie.cinema.database.CinemaProvider;
/** get place from bd*/
public class SavedPlace implements ISavedPlace {

	private Activity activity;
	private Cursor cursor = null;
	
	public SavedPlace (Activity a) {
		this.activity = a;
		if (cursor == null) {
    		cursor = activity.managedQuery(CinemaProvider.CONTENT_ID_URI_BASE, null, Cinema.COLUMN_NAME_ID_S,
                new String[] {getIdCinema()}, null);
    	}
	}
	
	private Intent getIntent() {
		return activity.getIntent();
	}
	
	private String getIdCinema() {
	  	return getIntent().getStringExtra(Cinema.COLUMN_NAME_ID_S);
	}
	 
	private Cursor getCursor() {
		return cursor;
	}
	
	public String getName(){
		return getCursor().getString( getCursor().getColumnIndex(Cinema.COLUMN_NAME_NAME));
	}
	
	public String getVicinity() {
		return getCursor().getString( getCursor().getColumnIndex(Cinema.COLUMN_NAME_VICINITY));
	}
	
	public String getOpenHours() {
		return getCursor().getString( getCursor().getColumnIndex(Cinema.COLUMN_NAME_OPENING_HOURS));
	}
	
	public List<Integer> getFreePlacesArray() {
		String s = getCursor().getString( getCursor().getColumnIndex(Cinema.COLUMN_NAME_SEATS));
		s = s.substring(1, s.length() - 1);
		String [] strarr = s.split(", ");
		List<Integer> iarr = new ArrayList<Integer>();
		
		for (String str: strarr) {
			if (!str.isEmpty()) {
				iarr.add(Integer.valueOf(str));
			}
		}
		return iarr;
	}
	
	public int getPlacesCount() {
		return Integer.valueOf(getCursor().getString( getCursor().getColumnIndex(Cinema.COLUMN_NAME_SEATSSIZE)));
	}

}
