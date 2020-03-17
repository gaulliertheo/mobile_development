package com.project.musicapp;

import java.util.ArrayList;

public class ResponseDataAlbum {

    private ArrayList<Album> data;
    private float total;
    private String next;

    public ArrayList<Album> getdata() {
        return data;
    }

    public float getTotal() {
        return total;
    }

    public String getNext() {
        return next;
    }
}