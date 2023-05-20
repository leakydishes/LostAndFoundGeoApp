package com.example.lostandfoundapp.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert
    void insertAllData(Item model);

    //Select All Data
    @Query("select * from  item_list")
    List<Item> getAllData();

    //DELETE DATA
    @Query("delete from item_list where `key`= :id")
    void deleteData(int id);

    // Delete user from DB
    @Query("DELETE FROM item_list where name = :name")
    void deleteByUserName(String name);

    //Update Data
    @Query("update item_list SET post= :post ,name= :name ,phone= :phone, detail= :detail, " +
            "date= :date, location =:location where `key`= :key")
    void updateData(String post, String name, String phone, String detail, String date,
                    String location, int key);
}