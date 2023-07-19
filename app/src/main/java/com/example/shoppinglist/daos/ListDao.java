package com.example.shoppinglist.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.shoppinglist.entities.List;

@Dao
public interface ListDao {

    // get all lists
    @Query("SELECT * FROM list")
    List[] getAll();

    // get list by id
    @Query("SELECT * FROM list WHERE listId LIKE :id")
    com.example.shoppinglist.entities.List findById(int id);

    // get list by name
    @Query("SELECT * FROM list WHERE list_name LIKE :name")
    com.example.shoppinglist.entities.List findByName(String name);

    // new list
    @Insert
    void insertAll(com.example.shoppinglist.entities.List... lists);

    // delete list
    @Delete
    void delete(com.example.shoppinglist.entities.List list);
}
