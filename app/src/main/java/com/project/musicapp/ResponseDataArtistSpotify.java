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
    External_urls external_urls;

    // Getter Methods
    public Followers getFollowers() { return followers; }
    public External_urls getExternal_urls() { return external_urls; }
}

class External_urls {

    private String spotify;

    // Getter Methods
    public String getSpotify() { return spotify; }
}

class Followers {

    private String total;

    // Getter Methods
    public String getTotal() { return total; }
}