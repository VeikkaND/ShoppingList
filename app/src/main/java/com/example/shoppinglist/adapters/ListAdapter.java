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

/**
 * Adapter for the Home fragments' Recyclerview
 *
 * @author Veikka Nevala
 *
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    /** Array of all lists */
    private List[] localDataset;

    /** Click listener for every list, navigates to the wanted list */
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

    /**
     * ViewHolder for the adapter
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        /** TextView of the list's name */
        private final TextView tvListname;

        /** ProgressBar of the list */
        private final ProgressBar progressBar;

        /**
         * Constructor for the ViewHolder
         * @param view View
         */
        public ViewHolder(View view) {
            super(view);

            tvListname = (TextView) view.findViewById(R.id.tvListName);
            progressBar = (ProgressBar) view.findViewById(R.id.pbProgressShort);
        }

        /**
         * Returns the list's name TextView
         * @return TextView name
         */
        public TextView getTvListname() { return tvListname; }

        /**
         * Returns the list's progress ProgressView
         * @return ProgressBar progress
         */
        public ProgressBar getProgressBar() { return  progressBar; }
    }

    /**
     * Constructor for the adapter
     * @param dataset List[] all lists
     */
    public ListAdapter(List[] dataset) {
        localDataset = dataset;
    }

    /**
     * fills the view with lists
     *
     * @param viewGroup The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_list, viewGroup, false);

        return new ViewHolder(view);
    }

    /**
     * Binds info and listeners to each list
     * @param viewHolder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.itemView.setTag(R.id.textview, localDataset[position].listName);
        viewHolder.itemView.setOnClickListener(ListClickListener);
        viewHolder.getTvListname().setText(localDataset[position].listName);
        viewHolder.getProgressBar().setProgress(localDataset[position].doneProgress);
    }

    /**
     * Returns the amount of lists
     * @return int lists
     */
    @Override
    public int getItemCount() {
        return localDataset.length;
    }
}
