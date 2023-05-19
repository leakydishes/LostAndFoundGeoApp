package com.example.lostandfoundapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NewAdvertActivity extends AppCompatActivity {

    // variables
    EditText input_name, input_phone, input_detail, input_location, input_date;
    Button btn_save;
    String postType;
    RadioGroup radioGroupPost;

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
        radioGroupPost = findViewById(R.id.radioGroupPost);

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