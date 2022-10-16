package edu.northeastern.numad22fa_jiyoonjeong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class getLocation extends FragmentActivity {

    TextView loc, alamat;
    private Boolean oke = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);
        loc = findViewById(R.id.loc);

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 0, new LocationListener() {
            List<Address> addressList = null;
            @Override
            public void onLocationChanged(@NonNull Location location) {
                try{
                    addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if(addressList != null){
                        Address returnAdd = addressList.get(0);
                        StringBuilder stringBuilder = new StringBuilder("");
                        for(int i = 0; i< returnAdd.getMaxAddressLineIndex(); i++){
                            stringBuilder.append(returnAdd.getAddressLine(i)).append("\n");
                        }
                        Log.w("My location Address", stringBuilder.toString());
                    } else{
                        Log.w("My Location Address", "no address");
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
                if(oke){
                    String addressLines = addressList.get(0).getAddressLine(0);
                    loc.setText(String.valueOf("Location\nLatitude : " + location.getLatitude() + "\nLongitude : " + location.getLongitude()));
                }
            }
        });


    }


}


