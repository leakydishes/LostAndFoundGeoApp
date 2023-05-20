package com.example.lostandfoundapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lostandfoundapp.R;

public class MainActivity extends AppCompatActivity {

    // variables
    Button BtnNewAdvert, BtnShowFound, btnShowMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set to screen
        BtnNewAdvert = findViewById(R.id.BtnNewAdvert);
        BtnShowFound = findViewById(R.id.BtnShowFound);
        btnShowMap = findViewById(R.id.btnShowMap);

        // open new advert
        BtnNewAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,NewAdvertActivity.class);
                startActivity(i);
            }
        });

        // show list of all items
        BtnShowFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,LostFoundItemsActivity.class);
                startActivity(i);
            }
        });

        // show map view
        btnShowMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,ShowMapActivity.class);
                startActivity(i);
            }
        });
    }
}