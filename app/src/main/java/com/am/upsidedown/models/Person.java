package com.am.upsidedown.models;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class Person implements ClusterItem {


    private final LatLng mPosition;
    private String name;
    private String cityHandle;

    public Person(double lat, double lng, String name, String cityHandle) {
        this.name = name;
        this.cityHandle = cityHandle;
        mPosition = new LatLng(lat, lng);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getSnippet() {
        return cityHandle;
    }
}
