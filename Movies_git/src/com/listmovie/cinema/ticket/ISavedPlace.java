package com.listmovie.cinema.ticket;

import java.util.List;

public interface ISavedPlace {
	public String getName();
	public String getVicinity();
	public String getOpenHours();
	public List<Integer> getFreePlacesArray();
	public int getPlacesCount();
}
