package com.example.shoppinglist.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shoppinglist.entities.List;

/**
 * Data Access Object for List
 *
 * @author Veikka Nevala
 *
 */
@Dao
public interface ListDao {

    /**
     * Selects all the lists
     * @return List[] lists
     */
    @Query("SELECT * FROM list")
    List[] getAll();

    /**
     * Selects a list with it's ID
     * @param id int ListId
     * @return List
     */
    @Query("SELECT * FROM list WHERE listId LIKE :id")
    com.example.shoppinglist.entities.List findById(int id);

    /**
     * Selects the id of a list
     * @param name String list name
     * @return int ListId
     */
    @Query("SELECT listId FROM list WHERE list_name LIKE :name")
    int getListId(String name);

    /**
     * Selects a list with a name
     * @param name String list name
     * @return List list
     */
    @Query("SELECT * FROM list WHERE list_name LIKE :name")
    com.example.shoppinglist.entities.List findByName(String name);

    /**
     * Creates a new list
     * @param lists List new list
     */
    @Insert
    void insertAll(com.example.shoppinglist.entities.List... lists);

    /**
     * Updates a list
     * @param lists List updated list
     */
    @Update
    void updateList(List... lists);

    /**
     * Deletes a list
     * @param list List list to delete
     */
    @Delete
    void delete(com.example.shoppinglist.entities.List list);
}
