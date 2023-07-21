package com.example.shoppinglist.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.MainActivity;
import com.example.shoppinglist.R;
import com.example.shoppinglist.entities.ListItem;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private ListItem[] localDataset;

    CompoundButton.OnCheckedChangeListener CheckChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            int itemId = (int) compoundButton.getTag();

            ListItem listItem = MainActivity.db.listItemDao().findById(itemId);
            listItem.done = b;

            MainActivity.db.listItemDao().updateListItem(listItem);
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
