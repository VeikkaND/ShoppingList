package com.example.shoppinglist.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shoppinglist.entities.ListItem;

import java.util.List;

/**
 * Data Access Object for ListItem
 *
 * @author Veikka Nevala
 *
 */
@Dao
public interface ListItemDao {


    /**
     * selects all the list items with the same ListId
     * @param listId int ListId
     * @return ListItem[] items of the list
     */
    @Query("SELECT * FROM listitem WHERE list_id LIKE :listId")
    ListItem[] findByListId(int listId);

    /**
     * selects a ListItem with it's ID
     * @param id int ListItemId
     * @return ListItem item
     */
    @Query("SELECT * FROM listitem WHERE listItemId LIKE :id")
    ListItem findById(int id);

    /**
     * Selects all the progress statuses of a list
     * @param listId int ListId
     * @return boolean[] progress
     */
    @Query("SELECT done FROM listitem WHERE list_id LIKE :listId")
    boolean[] getDones(int listId);

    /**
     * Creates a new ListItem in the database
     * @param listItems ListItem new list item
     */
    @Insert
    void insertAll(ListItem... listItems);

    /**
     * Updates a ListItem
     * @param listItems ListItem updated list item
     */
    @Update
    void updateListItem(ListItem... listItems);

    /**
     * Deletes a ListItem
     * @param listItem ListItem list item to delete
     */
    @Delete
    void delete(ListItem listItem);
}
