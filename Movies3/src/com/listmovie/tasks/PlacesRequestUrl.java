package com.listmovie.tasks;

//import com.google.android.gms.maps.model.LatLng;

public class PlacesRequestUrl {

	private static final String SEPARATOR = "/"; 
	private static final String COMA = ",";
	private static final String QUESTION_MARK = "?";
	private static final String LOCATION = "location";
	private static final String EQUALS_MARK = "=";
	private static final String AMPERSAND = "&";
	private static final String NAME = "name";
	private static final String SENSOR = "sensor";
	private static final String RADIUS = "radius";
	private static final String KEY = "key";
	private String URL = "https://maps.googleapis.com/maps/api/place/search";
	private String type = "json";
	
	//https://maps.googleapis.com/maps/api/place/search/json?location=48.466601,35.018155&tpes=establishment&name=%D1%82%D0%B5%D0%B0%D1%82%D1%80&radius=5500&sensor=true&key=AIzaSyDUmmvpJHfvthXK_g-jk3Ef2FJbkosvIUc

	
	private StringBuilder result = new StringBuilder();
	
//	public PlacesRequestUrl(LatLng location, String name, boolean sensor, int radius, String key) {
//		result.append(URL).append(SEPARATOR).append(type).append(QUESTION_MARK)
//		.append(LOCATION).append(EQUALS_MARK).append(location.latitude).append(COMA).append(location.longitude).append(AMPERSAND)
//		.append(NAME).append(EQUALS_MARK).append(name).append(AMPERSAND).append(SENSOR).append(EQUALS_MARK).append(sensor).append(AMPERSAND)
//		.append(RADIUS).append(EQUALS_MARK).append(radius).append(AMPERSAND).append(KEY).append(EQUALS_MARK).append(key);
//	}
	
	@Override
	public String toString() {
		return result.toString();
	}
	
}
