package com.am.upsidedown;

import android.content.Context;
import android.os.Bundle;

import com.am.upsidedown.models.Person;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class MapsActivity extends BaseGoogleMapsActivity {

    private ClusterManager<Person> mClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        setupMap(googleMap);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.6252695, 20.8959508), 10));

        mClusterManager = new ClusterManager<>(this, googleMap);

        googleMap.setOnCameraIdleListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);
        googleMap.setOnInfoWindowClickListener(mClusterManager);
        addPersonItems();
        mClusterManager.cluster();
    }

    private void addPersonItems() {
        for (int i = 0; i < 10; i++) {
            mClusterManager.addItem(new Person(42.6252695, 20.8959508, "Medina", "Drenas" ));
            mClusterManager.addItem(new Person(43.01883455, 20.80966632, "Medina1", "Prishtine" ));
            mClusterManager.addItem(new Person(43.08894918, 19.03106689, "Arbena","Gjakove" ));
            mClusterManager.addItem(new Person(42.2089656, 20.7404242, "Arbena1","Prizren"));
        }
    }

    private class RenderClusterInfoWindow extends DefaultClusterRenderer<Person> {

        RenderClusterInfoWindow(Context context, GoogleMap map, ClusterManager<Person> clusterManager) {
            super(context, map, clusterManager);
        }

        @Override
        protected void onClusterRendered(Cluster<Person> cluster, Marker marker) {
            super.onClusterRendered(cluster, marker);
        }

        @Override
        protected void onBeforeClusterItemRendered(Person item, MarkerOptions markerOptions) {
            markerOptions.title(item.getName());

            super.onBeforeClusterItemRendered(item, markerOptions);
        }
    }
}
