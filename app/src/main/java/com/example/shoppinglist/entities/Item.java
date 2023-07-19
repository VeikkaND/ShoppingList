package com.example.shoppinglist.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Item {
    public Item() {

    }
    public Item(String name, String unit) {
        this.itemName = name;
        this.unitType = unit;
    }
    @PrimaryKey(autoGenerate = true)
    public int itemId;

    @ColumnInfo(name = "item_name")
    public String itemName;

    @ColumnInfo(name = "unit_type")
    public String unitType;
}
