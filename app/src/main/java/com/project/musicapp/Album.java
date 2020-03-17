package com.project.musicapp;

public class Album {
    private float id;
    private String title;
    private String link;
    private String cover_big;
    private String nb_tracks;
    private boolean explicit_lyrics;
    Artist ArtistObject;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getCover_big() {
        return cover_big;
    }

    public String getNb_tracks() {
        return nb_tracks;
    }

    public Artist getArtist() {
        return ArtistObject;
    }
}
