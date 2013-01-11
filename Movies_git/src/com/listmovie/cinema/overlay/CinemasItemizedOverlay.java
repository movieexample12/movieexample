package com.listmovie.cinema.overlay;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.OverlayItem;
import com.listmovie.CinemaTabsBar;
import com.listmovie.cinema.database.Cinema;

public class CinemasItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	private List<CinemaOverlayItem> cinemaOverlay = new ArrayList<CinemaOverlayItem>();
	private MapActivity activity;
	
	public CinemasItemizedOverlay(Drawable defaultMarker,MapActivity context) {
	    super(boundCenterBottom(defaultMarker));
		this.activity = context;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return cinemaOverlay.get(i);
	}

	@Override
	public int size() {
		return cinemaOverlay.size();
	}
	
	public void addOverlay(CinemaOverlayItem overlay) {
		cinemaOverlay.add(overlay);
	    populate();
	}
	
	@Override
	protected boolean onTap(int index) {
	  CinemaOverlayItem item = cinemaOverlay.get(index);
	  Intent newActivity = new Intent(activity, CinemaTabsBar.class);
	  newActivity.putExtra(Cinema.COLUMN_NAME_ID_S, item.id);
	  activity.startActivity(newActivity);
	  return true;
	}
}
