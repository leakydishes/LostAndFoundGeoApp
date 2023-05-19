package com.example.lostandfoundapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.lostandfoundapp.Data.Item;
import com.example.lostandfoundapp.Data.ItemDao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
@androidx.room.Database(entities={Item.class}, version =1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract ItemDao getDao();

    private static Database instance;

    public static Database getDatabase(final Context context) {
        if (instance == null) {
            synchronized (Database.class) {
                instance = Room.databaseBuilder(context, Database.class, "DATABASE").allowMainThreadQueries().build();
            }
        }
        return instance;
    }

}