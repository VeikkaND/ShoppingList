package com.example.shoppinglist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.adapters.ListAdapter;
import com.example.shoppinglist.entities.List;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * Home fragment for lists overview
 *
 * @author Veikka Nevala
 *
 */
public class Home extends Fragment {

    /** Button for creating new lists */
    Button newButton;

    /** RecyclerView for created lists */
    RecyclerView rvLists;

    /**
     * Constructor for the fragment
     */
    public Home() {
        super(R.layout.fragment_home);
    }

    /**
     * Finds the new list button and binds a listener to it
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return View view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        newButton = view.findViewById(R.id.newButton);

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create a new list in DB
                Date time = Calendar.getInstance().getTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                String currentDate = dateFormat.format(time);
                List newList = new List();
                newList.listName = currentDate;
                newList.doneProgress = 0;
                MainActivity.db.listDao().insertAll(newList);

                //navigate to list view
                Bundle bundle = new Bundle();
                bundle.putString("listName", currentDate);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.newList, bundle);
            }
        });

        return view;
    }

    /**
     * Fills the RecyclerView with lists and initiates it
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstance If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstance) {
        rvLists = view.findViewById(R.id.rvLists);
        List[] dataset = MainActivity.db.listDao().getAll();

        Collections.reverse(Arrays.asList(dataset));

        rvLists.setAdapter(new ListAdapter(dataset));
        rvLists.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}