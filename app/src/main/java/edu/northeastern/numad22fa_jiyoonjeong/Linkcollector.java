package edu.northeastern.numad22fa_jiyoonjeong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.ArrayList;

public class Linkcollector extends AppCompatActivity{

    private List<Link> linkList;
    private LinkAdapter recyclerViewAdapter;
    private RecyclerView listRecyclerView;

    private FloatingActionButton add;
    private String name, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkcollector);

        //Instantiate the arraylist
        linkList = new ArrayList<>();
        add = findViewById(R.id.floating_add);


        // initializing our adapter class with our array list and context.
        recyclerViewAdapter = new LinkAdapter(linkList, this);
        listRecyclerView = findViewById(R.id.link_recycler_view);
        //This defines the way in which the RecyclerView is oriented
        listRecyclerView.setLayoutManager(new LinearLayoutManager(this));        if(savedInstanceState != null){
            linkList = savedInstanceState.getParcelableArrayList("linklist");
        }

        //Associates the adapter with the RecyclerView
        listRecyclerView.setAdapter(recyclerViewAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(listRecyclerView);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog  = new Dialog(Linkcollector.this);
                dialog.setContentView(R.layout.additem);
                EditText etname = dialog.findViewById(R.id.name);
                EditText eturl = dialog.findViewById(R.id.url);
                Button btsave = dialog.findViewById(R.id.savebutton);

                btsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            name = etname.getText().toString();
                            url = eturl.getText().toString();

                            linkList.add(new Link(name, url));
                            dialog.dismiss();

                            //Associates the adapter with the RecyclerView
                            listRecyclerView.setAdapter(new LinkAdapter(linkList, Linkcollector.this));
                            showSnackbar("Successfully added a Link");
                        } catch(Exception e){
                            showSnackbar("Failed to add a Link");
                        }

                    }
                });
                dialog.show();
            }
        });


        //Adding a new person object to the personList arrayList
//        linkList.add(new Link("John Doe", "www"));

    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Toast.makeText(Linkcollector.this, "on Move", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            Toast.makeText(Linkcollector.this, "on Swiped ", Toast.LENGTH_SHORT).show();
            //Remove swiped item from list and notify the RecyclerView
            if (swipeDir == ItemTouchHelper.LEFT | swipeDir == ItemTouchHelper.RIGHT){
                int position = viewHolder.getAdapterPosition();
                linkList.remove(position);
                recyclerViewAdapter.notifyItemRemoved(position);
            }
        }
    };


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("linklist", (ArrayList<? extends Parcelable>) linkList);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getParcelableArrayList("linklist");
    }

    public void showSnackbar(String input){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinativelayout),input, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}