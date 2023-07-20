package com.example.shoppinglist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.shoppinglist.daos.ItemDao;
import com.example.shoppinglist.entities.Item;

public class newItem extends Fragment {

    Button backButton;
    Button addButton;

    EditText nameInput;
    EditText unitInput;

    ItemDao itemDao;

    public newItem() {
        super(R.layout.fragment_new_item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_item, container, false);

        backButton = view.findViewById(R.id.backToListButton);
        addButton = view.findViewById(R.id.addItemButton);
        nameInput = view.findViewById(R.id.etName);
        unitInput = view.findViewById(R.id.etUnit);
        itemDao = ((MainActivity) getActivity()).getItemDao();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("listName", getArguments().getString("listName"));

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.newList, bundle);
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString();
                String unit = unitInput.getText().toString();

                if(!name.equals("") && !unit.equals("")) {
                    // save to db
                    Item newItem = new Item(name, unit);
                    itemDao.insertAll(newItem);

                    // reset inputs
                    nameInput.setText("");
                    unitInput.setText("");
                }
            }
        });

        return view;
    }
}