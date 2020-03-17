package com.project.musicapp;

import java.util.ArrayList;

public class ResponseDataTrack {

    private ArrayList<Track> data;

    // Getter Methods
    public ArrayList<Track> getdata() {
        return data;
    }
}

class Track {

    private String title;
    private String link;
    private String rank;
    private String preview;
    private Album album;

    // Getter Methods
    public String getTitle() {
        return title;
    }
    public String getLink() {
        return link;
    }
    public String getRank() {
        return rank;
    }
    public String getPreview() {
        return preview;
    }
    public Album getAlbum() {
        return album;
    }
}