package com.example.shoppinglist.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shoppinglist.entities.ListItem;

import java.util.List;

@Dao
public interface ListItemDao {

    // select list items belonging to specific ListId
    @Query("SELECT * FROM listitem WHERE list_id LIKE :listId")
    ListItem[] findByListId(int listId);

    // select list item by id
    @Query("SELECT * FROM listitem WHERE listItemId LIKE :id")
    ListItem findById(int id);

    // get listItemId with name & unit

    // new listItem
    @Insert
    void insertAll(ListItem... listItems);

    // change done -status
    @Update
    void updateListItem(ListItem... listItems);

    // delete listItem
    @Delete
    void delete(ListItem listItem);
}
