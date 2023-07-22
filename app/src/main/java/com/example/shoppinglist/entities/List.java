package com.example.shoppinglist.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * List entity
 *
 * @author Veikka Nevala
 *
 */
@Entity
public class List {

    /** Primary key of the list */
    @PrimaryKey(autoGenerate = true)
    public int listId;

    /** Name of the list */
    @ColumnInfo(name = "list_name")
    public String listName;

    /** Progress of the list */
    @ColumnInfo(name = "done_progress")
    public int doneProgress;
}
