package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.shoppinglist.daos.ItemDao;
import com.example.shoppinglist.daos.ListDao;
import com.example.shoppinglist.daos.ListItemDao;
import com.example.shoppinglist.entities.Item;

/**
 * Main class, initiates database and sets the home fragment to show
 *
 * @author Veikka Nevala
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Database object
     */
    public static AppDatabase db;

    /** list Data Access Object */
    ListDao listDao;

    /**
     * item Data Access Object
     * for added items
     */
    ItemDao itemDao;

    /**
     * list item Data Access Object
     * for items stored in existing lists
     */
    ListItemDao listItemDao;


    /**
     * onCreate method for MainActivity class
     * builds the database and sets the data access objects
     * @param savedInstanceState instanceState
     */
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
            // initiate the database with predetermined items
            initiateDatabase(itemDao);
        }

        setContentView(R.layout.activity_main);
    }

    /**
     * Initiates the database with a few default items
     * @param itemDao item Data Access Object
     */
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

    /**
     * Returns the item Data Access Object
     * @return ItemDao item Data Access Object
     */
    public ItemDao getItemDao() { return itemDao; }
}