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
import java.util.Calendar;
import java.util.Date;

public class Home extends Fragment {
    Button newButton;
    RecyclerView rvLists;

    public Home() {
        super(R.layout.fragment_home);
    }

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

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstance) {
        rvLists = view.findViewById(R.id.rvLists);
        List[] dataset = MainActivity.db.listDao().getAll();

        rvLists.setAdapter(new ListAdapter(dataset));
        rvLists.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}