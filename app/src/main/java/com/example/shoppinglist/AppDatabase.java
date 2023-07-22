package com.example.shoppinglist;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.shoppinglist.daos.ItemDao;
import com.example.shoppinglist.daos.ListDao;
import com.example.shoppinglist.daos.ListItemDao;
import com.example.shoppinglist.entities.Item;
import com.example.shoppinglist.entities.List;
import com.example.shoppinglist.entities.ListItem;

/**
 * The App's database
 *
 * @author Veikka Nevala
 *
 */
@Database(entities = {List.class, Item.class, ListItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    /** Item Data Access Object */
    public abstract ItemDao itemDao();

    /** List Data Access Object */
    public abstract ListDao listDao();

    /** ListItem Data Access Object */
    public abstract ListItemDao listItemDao();
}
