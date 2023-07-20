package com.example.shoppinglist;

public class ListItem {
    private String itemName;
    private String itemUnit;

    public ListItem(String itemName, String itemUnit) {
        this.itemName = itemName;
        this.itemUnit = itemUnit;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemUnit() {
        return itemUnit;
    }
}
