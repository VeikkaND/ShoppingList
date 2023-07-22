package com.example.shoppinglist.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * ListItem entity
 *
 * @author Veikka Nevala
 *
 */
@Entity
public class ListItem {

    /** Primary key of the item */
    @PrimaryKey(autoGenerate = true)
    public int listItemId;

    /** Id of the listItem's list */
    @ColumnInfo(name = "list_id")
    public int listId;

    /** Name of the item */
    @ColumnInfo(name = "item_name")
    public String itemName;

    /** Amount of the item */
    @ColumnInfo(name = "item_amount")
    public float itemAmount;

    /** Unit of the item */
    @ColumnInfo(name = "item_unit")
    public String itemUnit;

    /** state of the item */
    @ColumnInfo(name = "done")
    public boolean done;
}
