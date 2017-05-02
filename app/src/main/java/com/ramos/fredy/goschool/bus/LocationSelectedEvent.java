package com.ramos.fredy.goschool.bus;

import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

/**
 * Created by fredy on 30/04/2017.
 */

public class LocationSelectedEvent {

    private LatLng latLng;

    public LocationSelectedEvent(LatLng latLng) {
        this.latLng = latLng;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getLocationToString() {
        return String.format(Locale.getDefault(), "%s; %s", latLng.latitude, latLng.longitude);
    }
}
