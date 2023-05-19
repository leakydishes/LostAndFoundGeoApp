package com.example.lostandfoundapp.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_list")
public class Item {

    // variables
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int key;
    @ColumnInfo(name = "post")
    public String post;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "phone")
    public String phone;
    @ColumnInfo(name = "detail")
    public String detail;
    @ColumnInfo(name = "date")
    public String date;
    @ColumnInfo(name = "location")
    public String location;

    // getters and setters

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}