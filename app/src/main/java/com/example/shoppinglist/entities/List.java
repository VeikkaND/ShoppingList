package com.example.shoppinglist.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class List {
    @PrimaryKey(autoGenerate = true)
    public int listId;

    @ColumnInfo(name = "list_name")
    public String listName;

}
