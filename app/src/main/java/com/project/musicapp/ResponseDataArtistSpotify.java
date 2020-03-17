package com.project.musicapp;

import java.util.ArrayList;

public class ResponseDataArtistSpotify {

    Artists artists;

    // Getter Methods
    public Artists getArtists() {
        return artists;
    }
}

class Artists {

    private String href;
    ArrayList<Object> items = new ArrayList<Object>();

    // Getter Methods
    public ArrayList<Object> getItems() { return items; }
    public String getHref() { return href; }
}

class Object {

    Followers followers;

    // Getter Methods
    public Followers getFollowers() {
        return followers;
    }
}

class Followers {

    private String total;

    // Getter Methods
    public String getTotal() {
        return total;
    }
}