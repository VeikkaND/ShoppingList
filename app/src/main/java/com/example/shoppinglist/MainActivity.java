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
    ListDao listDao;
    ItemDao itemDao;
    ListItemDao listItemDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "shoppinglist-db")
                    .allowMainThreadQueries()
                    .build();
        listDao = db.listDao();
        itemDao = db.itemDao();
        listItemDao = db.listItemDao();

        Item[] allItems = itemDao.getAll();
        if(allItems.length < 1) {
            initiateDatabase(itemDao);
        }

        setContentView(R.layout.activity_main);
    }

    private void initiateDatabase(ItemDao itemDao) {
        Item item1 = new Item();
        item1.itemName = "bread";
        item1.unitType = "pcs";

        Item item2 = new Item();
        item2.itemName = "tomato";
        item2.unitType = "pcs";

        Item item3 = new Item();
        item3.itemName = "milk";
        item3.unitType = "l";

        itemDao.insertAll(item1, item2, item3);
    }

    public ItemDao getItemDao() { return itemDao; }
}