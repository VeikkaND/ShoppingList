package com.example.shoppinglist;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.shoppinglist.daos.ItemDao;
import com.example.shoppinglist.daos.ListDao;
import com.example.shoppinglist.daos.ListItemDao;
import com.example.shoppinglist.entities.Item;
import com.example.shoppinglist.entities.List;
import com.example.shoppinglist.entities.ListItem;

@Database(entities = {List.class, Item.class, ListItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();
    public abstract ListDao listDao();
    public abstract ListItemDao listItemDao();
}
