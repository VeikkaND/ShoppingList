package com.example.shoppinglist.daos;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.shoppinglist.entities.Item;

import java.util.List;

@Dao
public interface ItemDao {

    //get all items
    @Query("SELECT * FROM item")
    Item[] getAll();

    //get all item names as strings
    @Query("SELECT item_name FROM item")
    String[] getAllNames();

    //get unit for item
    @Query("SELECT unit_type FROM item WHERE item_name LIKE :name")
    String getUnit(String name);

    //get item by id
    @Query("SELECT * FROM item WHERE itemId LIKE :id")
    Item findById(int id);

    //get item by name
    @Query("SELECT * FROM item WHERE item_name LIKE :name")
    Item findByName(String name);

    //new item
    @Insert
    void insertAll(Item... items);

    //delete item
    @Delete
    void delete(Item item);
}
