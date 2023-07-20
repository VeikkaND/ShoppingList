package com.example.shoppinglist.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.R;
import com.example.shoppinglist.entities.ListItem;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private ListItem[] localDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemName;
        private final TextView itemUnit;

        public ViewHolder(View view) {
            super(view);

            itemName = (TextView) view.findViewById(R.id.tvItemName);
            itemUnit = (TextView) view.findViewById(R.id.tvItemUnit);
        }

        public TextView getItemName() { return itemName; }
        public TextView getItemUnit() { return itemUnit; }
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
        viewHolder.getItemName().setText(localDataset[position].itemName);
        viewHolder.getItemUnit().setText(localDataset[position].itemUnit);
    }

    @Override
    public int getItemCount() {
        return localDataset.length;
    }
}
