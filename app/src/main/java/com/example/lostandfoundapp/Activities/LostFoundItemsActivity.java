package com.example.lostandfoundapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lostandfoundapp.Data.Item;
import com.example.lostandfoundapp.Data.ItemAdapter;
import com.example.lostandfoundapp.Database.Database;
import com.example.lostandfoundapp.R;

import java.util.ArrayList;
import java.util.List;

public class LostFoundItemsActivity extends AppCompatActivity implements ItemAdapter.ItemClickListener{
    // variables
    private List<Item> itemList;
    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_found_items);

        recyclerview = findViewById(R.id.recycler_view_items);
        getData();
    }

    private void getData() {
        itemList = new ArrayList<>();
        itemList = Database.getDatabase(getApplicationContext()).getDao().getAllData();


        // Create adapter passing in the sample user data
        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recycler_view_items);
        ItemAdapter adapter = new ItemAdapter(itemList, this);

        // Attach the adapter to the recyclerview to populate items
        recyclerview.setAdapter(adapter);

        // Set layout manager to position the items
        //recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onItemClick(Item item) {
        Intent intent = new Intent(LostFoundItemsActivity.this, RemoveItemActivity.class);

        // set data
        String post = item.getPost();
        String name = item.getName();
        String phone = item.getPhone();
        String detail = item.getDetail();
        String date = item.getDate();
        String location = item.getLocation();

        intent.putExtra("POST", post);
        intent.putExtra("NAME", name);
        intent.putExtra("PHONE", phone);
        intent.putExtra("DETAIL", detail);
        intent.putExtra("DATE", date);
        intent.putExtra("LOCATION", location);
        startActivity(intent);
    }
}
