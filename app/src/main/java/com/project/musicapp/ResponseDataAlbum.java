package com.project.musicapp;

import java.util.ArrayList;

public class ResponseDataAlbum {

    private ArrayList<Album> data;

    // Getter Methods
    public ArrayList<Album> getdata() {
        return data;
    }
}

class Album {
    private String title;
    private String link;
    private String cover_big;
    private String nb_tracks;
    Artist artist;

    // Getter Methods
    public String getTitle() { return title; }
    public String getLink() { return link; }
    public String getCover_big() { return cover_big; }
    public String getNb_tracks() { return nb_tracks; }
    public Artist getArtist() { return artist; }
}