package com.example.pokecards;

import java.util.ArrayList;
import java.util.List;

public class FavouriteManager {

    private static FavouriteManager instance;
    private List<Card> favourites;

    private FavouriteManager() {
        favourites = new ArrayList<>();
    }

    public static FavouriteManager getInstance() {
        if (instance == null) {
            instance = new FavouriteManager();
        }
        return instance;
    }

    public List<Card> getFavourites() {
        return favourites;
    }

    public void addToFavourites(Card card) {
        if (!favourites.contains(card)) {
            favourites.add(card);
        }
    }

    public void removeFromFavourites(Card card) {
        favourites.remove(card);
    }

    public boolean isFavourite(Card card) {
        return favourites.contains(card);
    }
}
