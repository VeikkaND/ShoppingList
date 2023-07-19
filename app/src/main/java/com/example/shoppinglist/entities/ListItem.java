package com.example.shoppinglist.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ListItem {
    @PrimaryKey(autoGenerate = true)
    public int listItemId;

    @ColumnInfo(name = "list_id")
    public int listId;

    @ColumnInfo(name = "item_name")
    public String itemName;

    @ColumnInfo(name = "item_amount")
    public float itemAmount;

    @ColumnInfo(name = "item_unit")
    public String itemUnit;
}
