package com.example.pokecards;

import java.io.Serializable;

public class Card implements Serializable {
    private String id;
    private String name;
    private String image;
    private String type;
    private String health;
    private String rarity;
    private String pack;
    private String fullart; // JSON key = "fullart"
    private String ex;
    private String artist;

    // Default constructor for Gson
    public Card() {}

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getImage() { return image; }
    public String getType() { return type; }
    public String getHealth() { return health; }
    public String getRarity() { return rarity; }
    public String getPack() { return pack; }
    public String getFullArt() { return fullart; } // note: getter name can be different
    public String getEx() { return ex; }
    public String getArtist() { return artist; }
}
