package com.example.lostandfoundapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.lostandfoundapp.Data.Item;
import com.example.lostandfoundapp.Database.Database;
import com.example.lostandfoundapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class ShowMapActivity extends FragmentActivity implements OnMapReadyCallback {

    // variables
    private GoogleMap map;
    List<Item> locations;
    LatLng myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);

        // get data
        locations = new ArrayList<>();
        locations = Database.getDatabase(getApplicationContext()).getDao().getAllData();

        // set fragment to screen
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        // add markers for lost and found items
        for (int i =0;i <locations.size();i++){
            String[] loc = locations.get(i).getLocation().split(",");
            try {
                LatLng addresses = new LatLng(Double.parseDouble(loc[0]), Double.parseDouble(loc[1]));
                map.addMarker(new MarkerOptions().position(addresses).title(locations.get(i).getName()+" "+locations.get(i).getPost()));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(addresses, 15));
                //map.moveCamera(CameraUpdateFactory.newLatLng(addresses));
            }catch(Exception exception){ }
        }
    }
}