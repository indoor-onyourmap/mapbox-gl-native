package com.mapbox.mapboxsdk.annotations;

import android.graphics.Point;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.mapbox.mapboxsdk.R;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.views.InfoWindow;

public final class Marker extends Annotation {

    private float anchorU;
    private float anchorV;
    private boolean draggable;
    private boolean flat;
    private float infoWindowAnchorU;
    private float infoWindowAnchorV;
    LatLng position; // not private due to JNI
    private float rotation;
    private String snippet;
    String sprite = "default_marker"; // not private due to JNI
    private String title;
    private InfoWindow infoWindow = null;
    private boolean infoWindowShown = false;

    /**
     * Constructor
     */
    Marker() {
        super();
    }

    /**
     * If two markers have the same LatLng, they are equal.
     *
     * @param other object
     * @return boolean - do they have the same LatLng
     */
    public boolean equals(Object other) {
        if (!(other instanceof Marker)) return false;
        double lat = position.getLatitude();
        double lng = position.getLongitude();
        LatLng otherPosition = ((Marker)other).getPosition();
        double otherLat = otherPosition.getLatitude();
        double otherLng = otherPosition.getLongitude();
        return (lat == otherLat && otherLng == lng);
    }

    public Point getAnchor() {
        return new Point((int)anchorU, (int)anchorV);
    }

    public float getAnchorU() {
        return anchorU;
    }

    public float getAnchorV() {
        return anchorV;
    }

    public float getInfoWindowAnchorU() {
        return infoWindowAnchorU;
    }

    public float getInfoWindowAnchorV() {
        return infoWindowAnchorV;
    }

    public LatLng getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getTitle() {
        return title;
    }

    public void hideInfoWindow() {
        if (infoWindow != null) {
            infoWindow.close();
        }
        infoWindowShown = false;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public boolean isFlat() {
        return flat;
    }

    public boolean isInfoWindowShown () {
        return infoWindowShown;
    }

    void setAnchor(float u, float v) {
        this.anchorU = u;
        this.anchorV = v;
    }

    void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    void setFlat(boolean flat) {
        this.flat = flat;
    }

    void setInfoWindowAnchor(float u, float v) {
        infoWindowAnchorU = u;
        infoWindowAnchorV = v;
    }

    void setPosition(LatLng latlng) {
        this.position = position;
    }

    void setRotation(float rotation) {
        this.rotation = rotation;
    }

    void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    /**
     * You can specify the name of a sprite to get a marker other than the default marker.
     * This name can be found in the sprite json file:
     *
     * https://github.com/mapbox/mapbox-gl-styles/blob/mb-pages/sprites/mapbox-streets.json
     *
     * If null you will get the default marker.
     *
     * @param sprite The name of the sprite.
     */
    void setSprite(@Nullable String sprite) {
        if (!TextUtils.isEmpty(sprite)) {
            this.sprite = sprite;
        }
    }

    public String getSprite() {
        return sprite;
    }

    void setTitle(String title) {
        this.title = title;
    }

    public void showInfoWindow() {

        if (!isVisible() || mapView == null) {
            return;
        }

        getInfoWindow().adaptDefaultMarker(this);
        getInfoWindow().open(this, getPosition(), (int) anchorU, (int) anchorV);
        getInfoWindow().setBoundMarker(this);
        infoWindowShown = true;
    }

    public void showInfoWindow(View view){

        if (!isVisible() || mapView == null) {
            return;
        }

        infoWindow = new InfoWindow(view, mapView);
        infoWindow.open(this, getPosition(), (int) anchorU, (int) anchorV);
        infoWindow.setBoundMarker(this);
        infoWindowShown = true;
    }

    /**
     * Use to set a custom OnTouchListener for the InfoWindow.
     * By default the InfoWindow will close on touch.
     * @param listener Custom OnTouchListener
     */
    void setInfoWindowOnTouchListener(View.OnTouchListener  listener) {
        if (listener == null) {
            return;
        }
        getInfoWindow().setOnTouchListener(listener);
    }

    /**
     * Common internal InfoWindow initialization method
     * @return InfoWindow for Marker
     */
    private InfoWindow getInfoWindow() {
        if (infoWindow == null) {
            infoWindow = new InfoWindow(R.layout.infowindow, mapView);
        }
        return infoWindow;
    }

    @Override
    void setVisible(boolean visible) {
        super.setVisible(visible);
        if (!visible && infoWindowShown) {
            hideInfoWindow();
        }
    }

    //  TODO Method in Google Maps Android API
//    public int hashCode()

    // TODO: Implement this method of Google Maps Android API
//    void setIcon(BitmapDescriptor icon) {
//
//    }

}
