package com.listmovie;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import com.listmovie.R;

public class LocationActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);
		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {
		    public void onLocationChanged(Location location) {

		    	Log.i("location", location.toString());
		    }

		    public void onStatusChanged(String provider, int status, Bundle extras) {
		    	Log.i("location", provider);
		    }

		    public void onProviderEnabled(String provider) {
		    	Log.i("location", provider);
		    }

		    public void onProviderDisabled(String provider) {
		    	Log.i("location", provider);
		    }
		  };

		// Register the listener with the Location Manager to receive location updates
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		
		Location lastKnown = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		String.valueOf(lastKnown.getLatitude());
		String.valueOf(lastKnown.getLongitude());
	}
	
}
