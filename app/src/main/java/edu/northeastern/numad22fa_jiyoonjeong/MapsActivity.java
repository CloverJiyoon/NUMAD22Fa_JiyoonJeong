package edu.northeastern.numad22fa_jiyoonjeong;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private Boolean oke = false;
    TextView lat, lon, distanceView;
    private float distance = 0;
    Location start = null, end;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        lat = findViewById(R.id.latitude);
        lon = findViewById(R.id.longitude);
        distanceView = (TextView) findViewById(R.id.distance);
        distanceView.setOnClickListener(this);

        findViewById(R.id.reset).setOnClickListener(this);

        if(savedInstanceState != null){
            distance = savedInstanceState.getFloat("meter");
            distanceView.setText("Distance : " + String.valueOf(distance) + " meter");
        }


                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, new LocationListener() {

            @Override
            public void onLocationChanged(@NonNull Location location) {
                if(start == null){
                    start = location;
                    end = location;
                    distance = 0 ;
                }

                if (oke) {
                    LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(loc).title("Location"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
                    lat.setText("Latitude : " + String.valueOf(location.getLatitude()));
                    lon.setText("Longitude : " + String.valueOf(location.getLongitude()));

                    if(end.distanceTo(location) > 0.03){
                        distance = distance + end.distanceTo(location);
                        end = location;
                    }
//                    distance = start.distanceTo(location);
                    distanceView.setText("Distance : " + String.valueOf(distance)  + " meter");

            }

            }
        });


    }



    public void onClick (View view)
    {
        int theId = view.getId();
        if (theId == R.id.reset) {
            start = null;
            distance = 0;
            distanceView.setText("Distance : " + String.valueOf(distance)  + " meter");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat("meter", distance);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getFloat("meter");
    }



    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You will loose the total distance");
        builder.setMessage("Do you really want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        int height = (int) (getResources().getDisplayMetrics().widthPixels * 0.35);
        alertDialog.getWindow().setLayout(width, height);
    }





    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        oke = true;


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

}