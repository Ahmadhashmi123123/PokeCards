package com.example.pokecards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class login extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<Card> cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Toolbar setup
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // Logo ke liye title hide

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        cardList = new ArrayList<>();
        adapter = new CardAdapter(this, cardList,true);
        recyclerView.setAdapter(adapter);

        loadCardsFromAssets();
    }

    // Load JSON from assets
    private void loadCardsFromAssets() {
        try {
            InputStream is = getAssets().open("cards.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");

            Type listType = new TypeToken<List<Card>>(){}.getType();
            List<Card> cards = new Gson().fromJson(json, listType);

            cardList.addAll(cards);
            adapter.notifyDataSetChanged();

            Toast.makeText(this, "Loaded " + cards.size() + " cards", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading cards: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // Filter cards for search
    private void filterCards(String text) {
        List<Card> filteredList = new ArrayList<>();
        for (Card card : cardList) {
            if (card.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(card);
            }
        }
        adapter.updateList(filteredList);
    }

    // Inflate menu (search + favourites + about + signout)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Setup SearchView
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search Pokemon...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterCards(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterCards(newText);
                return false;
            }
        });

        return true;
    }

    // Menu item click actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Toast.makeText(this, "PokeCards App v1.0\nDeveloped by Ahmad Hashmi", Toast.LENGTH_LONG).show();
            return true;

        } else if (id == R.id.action_signout) {
            Intent intent = new Intent(login.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(this, "Signed Out Successfully", Toast.LENGTH_SHORT).show();
            return true;

        } else if (id == R.id.action_favourites) {
            // Open FavouritesActivity (create this if not exist)
            Intent intent = new Intent(login.this, FavouritesActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
