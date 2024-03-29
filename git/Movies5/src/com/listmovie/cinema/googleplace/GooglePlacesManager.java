package com.listmovie.cinema.googleplace;

import org.apache.http.client.HttpResponseException;

import android.util.Log;

 
import com.google.api.client.googleapis.GoogleHeaders;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpParser;
import com.google.api.client.json.jackson.JacksonFactory;

public class GooglePlacesManager {
	/** Global instance of the HTTP transport. */
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
 
    // Google API Key
    private static final String API_KEY = "AIzaSyDUmmvpJHfvthXK_g-jk3Ef2FJbkosvIUc";
 
    // Google Places serach url's
    private static final String PLACES_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/search/json?";
    private static final String PLACES_DETAILS_URL = "https://maps.googleapis.com/maps/api/place/details/json?";
 
    /**
     * Searching places
     * @param latitude - latitude of place
     * @params longitude - longitude of place
     * @param radius - radius of searchable area
     * @param types - type of place to search
     * @return list of places
     * */
    public static GooglePlacesList search(double latitude, double longitude, double radius, String type)
            throws Exception {
 
        HttpRequestFactory httpRequestFactory = createRequestFactory(HTTP_TRANSPORT);
        HttpRequest request = httpRequestFactory
                .buildGetRequest(new GenericUrl(PLACES_SEARCH_URL));
       
        request.getUrl().put("key", API_KEY);
        request.getUrl().put("location", latitude + "," + longitude);
        request.getUrl().put("radius", radius); // in meters
        request.getUrl().put("sensor", "false");
        if(type != null)
            request.getUrl().put("types", type);
            HttpResponse resp = request.execute();
            GooglePlacesList list = resp.parseAs(GooglePlacesList.class);
            // Check log cat for places response status
        Log.d("Places Status", String.valueOf(list.status));
            return list;
    }
 
    /**
     * Searching single place full details
     * @param refrence - reference id of place
     *                 - which you will get in search api request
     * */
    public static GooglePlaceDetails getPlaceDetails(String reference) throws Exception {
        try {
 
            HttpRequestFactory httpRequestFactory = createRequestFactory(HTTP_TRANSPORT);
            HttpRequest request = httpRequestFactory
                    .buildGetRequest(new GenericUrl(PLACES_DETAILS_URL));
            request.getUrl().put("key", API_KEY);
            request.getUrl().put("reference", reference);
            request.getUrl().put("sensor", "false");
 
            GooglePlaceDetails place = request.execute().parseAs(GooglePlaceDetails.class);
 
            return place;
 
        } catch (HttpResponseException e) {
            Log.e("Error in Perform Details", e.getMessage());
            throw e;
        }
    }
 
    /**
     * Creating http request Factory
     * */
    private static HttpRequestFactory createRequestFactory(
            final HttpTransport transport) {
        return transport.createRequestFactory(new HttpRequestInitializer() {
            public void initialize(HttpRequest request) {
                GoogleHeaders headers = new GoogleHeaders();
                headers.setApplicationName("AndroidHive-Places-Test");
                request.setHeaders(headers);
                JsonHttpParser parser = new JsonHttpParser(new JacksonFactory());
                request.addParser(parser);
            }
        });
    }
}
