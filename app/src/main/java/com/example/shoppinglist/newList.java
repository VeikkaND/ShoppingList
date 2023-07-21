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

public class newList extends Fragment {

    Button newItemButton;
    Button deleteListButton;
    Button backButton;
    Button addItemButton;
    Spinner itemSpinner;
    EditText etAmount;
    String listName;
    String spinnerItem = "";
    ProgressBar progressBar;

    RecyclerView rvItems;

    public newList() {
        super(R.layout.fragment_new_list);
    }

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
                    ListItem newListItem = new ListItem();
                    newListItem.itemName = spinnerItem;
                    newListItem.itemAmount = Float.parseFloat(etAmount.getText().toString());
                    newListItem.itemUnit = etAmount.getHint().toString();
                    newListItem.listId = MainActivity.db.listDao().getListId(listName);
                    newListItem.done = false;

                    MainActivity.db.listItemDao().insertAll(newListItem);

                    // refresh
                    Bundle bundle = new Bundle();
                    bundle.putString("listName", listName);
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.newList, bundle);
                }
            }
        });

        return view;
    }

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

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        listName = getArguments().getString("listName");
        rvItems = view.findViewById(R.id.rvItems);
        ListItem[] dataset = MainActivity.db.listItemDao()
                .findByListId(MainActivity.db.listDao().getListId(listName));

        rvItems.setAdapter(new ItemAdapter(dataset));
        rvItems.setLayoutManager(new LinearLayoutManager(getContext()));

        inputForm();
        progressBar.setProgress(MainActivity.db.listDao().findByName(listName).doneProgress);
        super.onViewCreated(view, savedInstanceState);
    }

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