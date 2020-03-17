package com.project.musicapp;

import java.util.ArrayList;

public class ResponseDataTrack {

    private ArrayList<Track> data;
    private float total;
    private String next;

    public ArrayList<Track> getdata() {
        return data;
    }

    public float getTotal() {
        return total;
    }

    public String getNext() {
        return next;
    }
}