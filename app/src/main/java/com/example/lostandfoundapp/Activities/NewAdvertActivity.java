package com.example.lostandfoundapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lostandfoundapp.Data.Item;
import com.example.lostandfoundapp.Database.Database;
import com.example.lostandfoundapp.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

public class NewAdvertActivity extends AppCompatActivity {

    // variables
    EditText input_name, input_phone, input_detail, input_location, input_date;
    Button btn_save, btn_currentLocation;
    String postType, location;
    RadioGroup radioGroupPost;
    LocationListener locationListener;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_advert);
        // set to screen

        input_name = findViewById(R.id.input_name);
        input_phone = findViewById(R.id.input_phone);
        input_detail = findViewById(R.id.input_detail);
        input_location = findViewById(R.id.input_location);
        input_date = findViewById(R.id.input_date);
        btn_save = findViewById(R.id.btn_save);
        btn_currentLocation = findViewById(R.id.btn_currentLocation);
        radioGroupPost = findViewById(R.id.radioGroupPost);

        //create place client to initialise
        Places.initialize(getApplicationContext(), "removed key for privacy");
        PlacesClient places = Places.createClient(this);

        // place fragment
        AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME,
                Place.Field.ADDRESS, Place.Field.LAT_LNG));

        // click listener
        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            // error message
            @Override
            public void onError(@NonNull Status status) {
                Toast.makeText(NewAdvertActivity.this, "Error Found: "+status, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPlaceSelected(@NonNull Place place) {
                LatLng latlng = place.getLatLng();
                input_location.setText(latlng.latitude+","+ latlng.longitude);
            }
        });

        // set location services
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                input_location.setText(location.getLatitude()+","+location.getLongitude());
            }
        };

        // current location button
        btn_currentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(NewAdvertActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(NewAdvertActivity.this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(NewAdvertActivity.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }else{
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }
            }
        });

        // save button
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check radio button
                if (radioGroupPost != null) {
                    int id=radioGroupPost.getCheckedRadioButtonId();
                    RadioButton rb=(RadioButton) findViewById(id);
                    postType=rb.getText().toString().trim();
                    String msg = postType;
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }

                // get user information
                String inputName = input_name.getText().toString().trim();
                String inputPhone = input_phone.getText().toString().trim();
                String inputDetail = input_detail.getText().toString().trim();
                String inputDate = input_date.getText().toString().trim();
                String inputLocation = input_location.getText().toString().trim();


                //Create new object of database
                Item model = new Item();

                // store database
                model.setName(inputName);
                model.setPost(postType);
                model.setPhone(inputPhone);
                model.setDetail(inputDetail);
                model.setDate(inputDate);
                model.setLocation(inputLocation);

                // insert into database
                Database.getDatabase(getApplicationContext()).getDao().insertAllData(model);

                // check if successful insert
                if (model != null){
                    Toast.makeText(NewAdvertActivity.this, "New Advert Inserted!", Toast.LENGTH_SHORT).show();
                    //reset variables
//                    input_name.setText("");
//                    input_phone.setText("");
//                    input_detail.setText("");
//                    input_date.setText("");
//                    input_location.setText("");

                    // go back to main activity
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(NewAdvertActivity.this, "Fill in all fields before progressing", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}