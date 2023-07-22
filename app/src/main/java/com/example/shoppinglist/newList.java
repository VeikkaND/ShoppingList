package com.example.shoppinglist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppinglist.adapters.ItemAdapter;
import com.example.shoppinglist.daos.ListItemDao;
import com.example.shoppinglist.entities.List;
import com.example.shoppinglist.entities.ListItem;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Arrays;

/**
 * Fragment for list overview
 *
 * @author Veikka Nevala
 *
 */
public class newList extends Fragment {

    /** Button for creating a new item to use */
    Button newItemButton;

    /** Button for deleting the list */
    Button deleteListButton;

    /** Button for returning back to Home fragment */
    Button backButton;

    /** Button for adding an item to the list */
    Button addItemButton;

    /** Dropdown selection menu for choosing an item to add */
    Spinner itemSpinner;

    /** Text input field for an amount of the new item */
    EditText etAmount;

    /** Name of the list */
    String listName;

    /** Name of the chosen item from the dropdown menu */
    String spinnerItem = "";

    /** Progress bar to show collected items progress */
    ProgressBar progressBar;

    /** RecyclerView for items of the list */
    RecyclerView rvItems;

    /**
     * Constructor of the fragment
     */
    public newList() {
        super(R.layout.fragment_new_list);
    }

    /**
     * Finds the fragments elements and binds listeners to the buttons
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
        View view = inflater.inflate(R.layout.fragment_new_list, container, false);

        newItemButton = view.findViewById(R.id.newItemButton);
        deleteListButton = view.findViewById(R.id.deleteListButton);
        backButton = view.findViewById(R.id.backButton);
        addItemButton = view.findViewById(R.id.addItemToListButton);
        itemSpinner = view.findViewById(R.id.itemSpinner);
        etAmount = view.findViewById(R.id.etNewItemAmount);
        progressBar = view.findViewById(R.id.pbProgress);

        newItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("listName", listName);

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.newItem, bundle);
            }
        });

        deleteListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());

                builder.setMessage(getResources()
                        .getString(R.string.deleteListMessage, listName));

                builder.setPositiveButton(R.string.dialogConfirm,
                        ((dialogInterface, i) -> deleteList(view)));

                builder.setNegativeButton(R.string.dialogCancel, (dialogInterface, i) -> {});

                builder.show();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.home2);
            }
        });

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // don't save items with missing info to DB
                if(!spinnerItem.equals("") && !etAmount.getText().toString().equals("")) {
                    int listId = MainActivity.db.listDao().getListId(listName);
                    ListItem newListItem = new ListItem();
                    newListItem.itemName = spinnerItem;
                    newListItem.itemAmount = Float.parseFloat(etAmount.getText().toString());
                    newListItem.itemUnit = etAmount.getHint().toString();
                    newListItem.listId = listId;
                    newListItem.done = false;

                    MainActivity.db.listItemDao().insertAll(newListItem);

                    //update progress
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

                    // refresh view (list)
                    Bundle bundle = new Bundle();
                    bundle.putString("listName", listName);
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.newList, bundle);
                }
            }
        });

        return view;
    }

    /**
     * Deletes the current list and its' ListItems from the database
     * @param view View
     */
    private void deleteList(View view) {
        int listId = MainActivity.db.listDao().getListId(listName);
        List list = MainActivity.db.listDao().findById(listId);

        // delete list
        MainActivity.db.listDao().delete(list);

        // delete ListItems associated with list
        ListItem[] listItems = MainActivity.db.listItemDao().findByListId(listId);
        for(ListItem listItem : listItems) {
            MainActivity.db.listItemDao().delete(listItem);
        }

        // navigate back to home
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.home2);
    }

    /**
     * Fills the RecyclerView with ListItems of the list
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        listName = getArguments().getString("listName");
        rvItems = view.findViewById(R.id.rvItems);
        ListItem[] dataset = MainActivity.db.listItemDao()
                .findByListId(MainActivity.db.listDao().getListId(listName));

        rvItems.setAdapter(new ItemAdapter(dataset, view.findViewById(R.id.pbProgress)));
        rvItems.setLayoutManager(new LinearLayoutManager(getContext()));

        inputForm();
        progressBar.setProgress(MainActivity.db.listDao().findByName(listName).doneProgress);
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Initiates the form for adding new items to the list
     */
    private void inputForm() {
        String[] names = MainActivity.db.itemDao().getAllNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_dropdown_item, names);
        itemSpinner.setAdapter(adapter);

        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                etAmount.setHint(MainActivity.db.itemDao().getUnit(item));
                spinnerItem = item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}