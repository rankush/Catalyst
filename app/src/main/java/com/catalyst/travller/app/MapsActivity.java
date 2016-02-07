package com.catalyst.travller.app;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.catalyst.travller.app.asyntask.MapRouteDownloadTask;
import com.catalyst.travller.app.asyntask.MapRouteParserTask;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    List<LatLng> locationList = new ArrayList<LatLng>();

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {

                LatLng location = new LatLng(point.latitude, point.longitude);

                String title = point.latitude + " , " + point.longitude;
                try {
                    Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
                    List<Address> addresses = gcd.getFromLocation(point.latitude, point.longitude, 1);
                    if (addresses.size() > 0)
                        title = addresses.get(0).getLocality();


                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (location != null) {
                    mMap.addMarker(new MarkerOptions().position(location).title(title));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

                    if (locationList.size() > 0) {
                        String url = getDirectionsUrl(locationList.get(locationList.size() - 1), location);
                        MapRouteDownloadTask downloadTask = new MapRouteDownloadTask();
                        downloadTask.setMapJsonDownLoadListener(downloadListener);
                        downloadTask.execute(url);
                    }
                    locationList.add(location);

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Location not found",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

       /* LatLng sydney = new LatLng(-34, 151);
        locationList.add(sydney);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));*/

    }

    MapRouteDownloadTask.IMapJsonDownloadListener downloadListener = new MapRouteDownloadTask.IMapJsonDownloadListener() {
        @Override
        public void onDownloadComplete(String result) {
            MapRouteParserTask parserTask = new MapRouteParserTask();
            parserTask.setMapRoutCreatorListener(mapRouteCreator);
            parserTask.execute(result);
        }
    };

    MapRouteParserTask.IMapRouteCreatorListener mapRouteCreator = new MapRouteParserTask.IMapRouteCreatorListener() {
        @Override
        public void onRouteCreated(PolylineOptions lineOptions) {
            // Drawing polyline in the Google Map for the i-th route
            if (lineOptions != null) {
                mMap.addPolyline(lineOptions);
            } else {
                Toast.makeText(getApplicationContext(),
                        "Sorry no Roads found.. Please check for ship or plane .. :) ",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    private String getDirectionsUrl(LatLng origin, LatLng dest) {
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String sensor = "sensor=false";
        String parameters = str_origin + "&" + str_dest + "&" + sensor;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
        return url;
    }


}
