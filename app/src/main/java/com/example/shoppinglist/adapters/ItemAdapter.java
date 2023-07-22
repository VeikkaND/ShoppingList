package com.example.shoppinglist.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.MainActivity;
import com.example.shoppinglist.R;
import com.example.shoppinglist.entities.List;
import com.example.shoppinglist.entities.ListItem;

/**
 * Adapter for newList fragment's RecyclerView
 *
 * @author Veikka Nevala
 *
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    /** Array of items belonging to the list */
    private ListItem[] localDataset;

    /** ProgressBar of current progress */
    private ProgressBar progressBar;

    /** Listener for the "done" checkbox */
    CompoundButton.OnCheckedChangeListener CheckChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            int itemId = (int) compoundButton.getTag();

            ListItem listItem = MainActivity.db.listItemDao().findById(itemId);
            listItem.done = b;

            MainActivity.db.listItemDao().updateListItem(listItem);

            // update progress
            int listId = listItem.listId;
            boolean[] dones = MainActivity.db.listItemDao().getDones(listId);
            int trues = 0;
            for(boolean bool : dones) {
                if(bool) {
                    trues++;
                }
            }
            List list = MainActivity.db.listDao().findById(listId);
            int progress = Math.round((float) trues/dones.length * 100);
            list.doneProgress = progress;
            progressBar.setProgress(progress);
            MainActivity.db.listDao().updateList(list);
        }
    };

    /**
     * ViewHolder for the RecyclerView
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        /** TextView of the item's name */
        private final TextView itemName;

        /** TextView of the item's unit */
        private final TextView itemUnit;

        /** CheckBox of the item */
        private final CheckBox done;

        /**
         * Constructor of the ViewHolder
         * @param view View
         */
        public ViewHolder(View view) {
            super(view);

            itemName = (TextView) view.findViewById(R.id.tvItemName);
            itemUnit = (TextView) view.findViewById(R.id.tvItemUnit);
            done = (CheckBox) view.findViewById(R.id.cbDone);
        }

        /**
         * Returns the item name's TextView
         * @return TextView
         */
        public TextView getItemName() { return itemName; }

        /**
         * Returns the item unit's Textview
         * @return Textview
         */
        public TextView getItemUnit() { return itemUnit; }

        /**
         * Returns the item's CheckBox
         * @return CheckBox
         */
        public CheckBox getDone() { return done; }
    }

    /**
     * Constructor for the adapter
     * @param dataset ListItem[] items of the list
     * @param pb ProgressBar ProgressBar of the list
     */
    public ItemAdapter(ListItem[] dataset, ProgressBar pb) {
        localDataset = dataset;
        progressBar = pb;
    }

    /**
     * fills the view with items
     *
     * @param viewGroup The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return View view
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);

        return new ViewHolder((view));
    }

    /**
     * Binds info and listeners to each item of the list
     *
     * @param viewHolder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getDone().setTag(localDataset[position].listItemId);
        viewHolder.getItemName().setText(localDataset[position].itemName);
        viewHolder.getItemUnit().setText(formatAmount(position));
        viewHolder.getDone().setChecked(localDataset[position].done);
        viewHolder.getDone().setOnCheckedChangeListener(CheckChangeListener);
    }

    /**
     * Formats the amount of an item to an integer and turns it into a string
     * @param position Int position
     * @return String formatted amount
     */
    private String formatAmount(int position) {
        String amount = Float.toString(localDataset[position].itemAmount);

        if(amount.endsWith(".0")) {
            amount = amount.replace(".0", "");
        }

        return amount + " " + localDataset[position].itemUnit;
    }

    /**
     * Returns the amount of items in the list
     * @return int items
     */
    @Override
    public int getItemCount() {
        return localDataset.length;
    }
}
