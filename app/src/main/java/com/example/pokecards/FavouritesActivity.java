package com.example.pokecards;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavouritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<Card> favouritesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        // Toolbar setup
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Get favourites list from FavouriteManager (singleton)
        favouritesList = FavouriteManager.getInstance().getFavourites();

        if (favouritesList.isEmpty()) {
            Toast.makeText(this, "No favourites yet!", Toast.LENGTH_SHORT).show();
        }

        // Adapter: false = hide fav button in favourites
        adapter = new CardAdapter(this, favouritesList, true);
        recyclerView.setAdapter(adapter);
    }

    // Back button
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
