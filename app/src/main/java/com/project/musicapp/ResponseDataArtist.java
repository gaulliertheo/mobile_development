package com.project.musicapp;

import java.util.ArrayList;

public class ResponseDataArtist {

    private ArrayList<Artist> data;

    // Getter Methods
    public ArrayList<Artist> getdata() {
        return data;
    }
}

class Artist {

    private String name;
    private String link;
    private String picture_big;
    private String nb_album;
    private String nb_fan;

    // Getter Methods
    public String getName() {
        return name;
    }
    public String getLink() {
        return link;
    }
    public String getPicture_big() {
        return picture_big;
    }
    public String getNb_album() {
        return nb_album;
    }
    public String getNb_fan() {
        return nb_fan;
    }
}