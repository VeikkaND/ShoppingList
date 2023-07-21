package com.example.shoppinglist.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.MainActivity;
import com.example.shoppinglist.R;
import com.example.shoppinglist.entities.List;
import com.example.shoppinglist.entities.ListItem;

import java.util.Arrays;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private ListItem[] localDataset;

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
            list.doneProgress = Math.round((float) trues/dones.length * 100);
            MainActivity.db.listDao().updateList(list);
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemName;
        private final TextView itemUnit;

        private final CheckBox done;

        public ViewHolder(View view) {
            super(view);

            itemName = (TextView) view.findViewById(R.id.tvItemName);
            itemUnit = (TextView) view.findViewById(R.id.tvItemUnit);
            done = (CheckBox) view.findViewById(R.id.cbDone);
        }

        public TextView getItemName() { return itemName; }
        public TextView getItemUnit() { return itemUnit; }

        public CheckBox getDone() { return done; }
    }

    public ItemAdapter(ListItem[] dataset) {
        localDataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);

        return new ViewHolder((view));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getDone().setTag(localDataset[position].listItemId);
        viewHolder.getItemName().setText(localDataset[position].itemName);
        viewHolder.getItemUnit().setText(localDataset[position].itemUnit);
        viewHolder.getDone().setChecked(localDataset[position].done);
        viewHolder.getDone().setOnCheckedChangeListener(CheckChangeListener);
    }

    @Override
    public int getItemCount() {
        return localDataset.length;
    }
}
