package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import com.example.shoppinglist.daos.ItemDao;
import com.example.shoppinglist.daos.ListDao;
import com.example.shoppinglist.daos.ListItemDao;
import com.example.shoppinglist.entities.Item;

public class MainActivity extends AppCompatActivity {

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "shoppinglist-db")
                    .allowMainThreadQueries()
                    .build();
        ListDao listDao = db.listDao();
        ItemDao itemDao = db.itemDao();
        ListItemDao listItemDao = db.listItemDao();

        Item[] allItems = itemDao.getAll();
        if(allItems.length < 1) {
            Log.i("db", "initiating database");
        }

        setContentView(R.layout.activity_main);
    }
}