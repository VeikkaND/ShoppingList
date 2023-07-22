package com.example.shoppinglist.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Item entity
 *
 * @author Veikka Nevala
 *
 */
@Entity
public class Item {

    /**
     * Constructor for an item without any name or unit
     */
    public Item() { }

    /**
     * Constructor for an item with a name and a unit
     * @param name String name
     * @param unit String unit
     */
    public Item(String name, String unit) {
        this.itemName = name;
        this.unitType = unit;
    }

    /** Primary key of the item */
    @PrimaryKey(autoGenerate = true)
    public int itemId;

    /** Name of the item */
    @ColumnInfo(name = "item_name")
    public String itemName;

    /** Unit of the item */
    @ColumnInfo(name = "unit_type")
    public String unitType;
}
