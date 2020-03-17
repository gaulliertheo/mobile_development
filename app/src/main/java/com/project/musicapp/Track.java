package com.project.musicapp;

public class Track {
    private float id;
    private String title;
    private String link;
    private String rank;
    private String preview;
    private Album AlbumObject;


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

    public String getRank() {
        return rank;
    }

    public String getPreview() {
        return preview;
    }

    public Album getAlbum() {
        return AlbumObject;
    }
}