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

/**
 * Fragment for creation of new items
 *
 * @author Veikka Nevala
 *
 */
public class newItem extends Fragment {

    /** Button for going back to list fragment */
    Button backButton;

    /** Button for adding the item  */
    Button addButton;

    /** Input field for the item's name */
    EditText nameInput;

    /** Input field for the item's unit */
    EditText unitInput;

    /** Item Data Access Object */
    ItemDao itemDao;

    /**
     * Constructor for the fragment
     */
    public newItem() {
        super(R.layout.fragment_new_item);
    }

    /**
     * Finds the buttons from the layout and binds listeners to them
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