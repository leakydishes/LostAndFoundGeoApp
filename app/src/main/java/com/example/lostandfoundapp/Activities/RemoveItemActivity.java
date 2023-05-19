package com.example.lostandfoundapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostandfoundapp.Data.Item;
import com.example.lostandfoundapp.Database.Database;
import com.example.lostandfoundapp.R;

import java.util.ArrayList;
import java.util.List;

public class RemoveItemActivity extends AppCompatActivity {

    // variables
    private List<Item> itemList;
    TextView post, name, phone, date, detail, location;
    Button btn_remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);

        itemList = new ArrayList<>();
        itemList = Database.getDatabase(getApplicationContext()).getDao().getAllData();

        //set to screen
        post = findViewById(R.id.post);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        detail = findViewById(R.id.detail);
        date = findViewById(R.id.date);
        location = findViewById(R.id.location);
        btn_remove = findViewById(R.id.btn_remove);

        // get data from database
        String setPost = getIntent().getStringExtra("POST");
        String setName = getIntent().getStringExtra("NAME");
        String setPhone = getIntent().getStringExtra("PHONE");
        String setDetail = getIntent().getStringExtra("DETAIL");
        String setDate = getIntent().getStringExtra("DATE");
        String setLocation = getIntent().getStringExtra("LOCATION");

        post.setText(setPost);
        name.setText(setName);
        phone.setText(setPhone);
        detail.setText(setDetail);
        date.setText(setDate);
        location.setText(setLocation);

        // Inflate the layout for this fragment
        btn_remove = findViewById(R.id.btn_remove);
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Button Pressed", "Remove");
                Database.getDatabase(getApplicationContext()).getDao().deleteByUserName(setName);
                //create object
                String msg = "Deleted Item";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                // go back to main activity
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}