package com.example.shoppinglist.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.shoppinglist.entities.Item;


/**
 * Data Access Object for Item
 *
 * @author Veikka Nevala
 *
 */
@Dao
public interface ItemDao {

    /**
     * Selects all items
     * @return Item[] items
     */
    @Query("SELECT * FROM item")
    Item[] getAll();

    /**
     * Selects all item names
     * @return String item names
     */
    @Query("SELECT item_name FROM item")
    String[] getAllNames();

    /**
     * Selects the unit of an item
     * @param name String item name
     * @return String unit
     */
    @Query("SELECT unit_type FROM item WHERE item_name LIKE :name")
    String getUnit(String name);

    /**
     * Selects the item with an ID
     * @param id int ItemId
     * @return Item item
     */
    @Query("SELECT * FROM item WHERE itemId LIKE :id")
    Item findById(int id);

    /**
     * Selects the item with a name
     * @param name String item name
     * @return Item item
     */
    @Query("SELECT * FROM item WHERE item_name LIKE :name")
    Item findByName(String name);

    /**
     * Creates a new item
     * @param items Item new item
     */
    @Insert
    void insertAll(Item... items);

    /**
     * deletes an item
     * @param item Item item to delete
     */
    @Delete
    void delete(Item item);
}
