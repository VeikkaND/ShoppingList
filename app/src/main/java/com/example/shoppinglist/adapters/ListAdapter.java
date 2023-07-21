package com.example.shoppinglist.adapters;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.R;
import com.example.shoppinglist.entities.List;

import java.util.Arrays;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List[] localDataset;

    View.OnClickListener ListClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String listName = (String) view.getTag(R.id.textview);

            Bundle bundle = new Bundle();
            bundle.putString("listName", listName);

            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.newList, bundle);
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvListname;
        private final ProgressBar progressBar;

        public ViewHolder(View view) {
            super(view);

            tvListname = (TextView) view.findViewById(R.id.tvListName);
            progressBar = (ProgressBar) view.findViewById(R.id.pbProgressShort);
        }

        public TextView getTvListname() { return tvListname; }
        public ProgressBar getProgressBar() { return  progressBar; }
    }

    public ListAdapter(List[] dataset) {
        localDataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.itemView.setTag(R.id.textview, localDataset[position].listName);
        viewHolder.itemView.setOnClickListener(ListClickListener);
        viewHolder.getTvListname().setText(localDataset[position].listName);
        viewHolder.getProgressBar().setProgress(localDataset[position].doneProgress);
    }

    @Override
    public int getItemCount() {
        return localDataset.length;
    }
}
