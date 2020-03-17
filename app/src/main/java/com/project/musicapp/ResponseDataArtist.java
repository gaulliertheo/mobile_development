package com.project.musicapp;

import java.util.ArrayList;

public class ResponseDataArtist {

    private ArrayList<Artist> data;
    private float total;
    private String next;

    public ArrayList<Artist> getdata() {
        return data;
    }

    public float getTotal() {
        return total;
    }

    public String getNext() {
        return next;
    }
}