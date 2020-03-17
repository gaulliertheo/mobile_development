package com.project.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Lance l'activité ListsActivity
    public void Go_to_lists(View v) {
        Intent intent = new Intent(this, ListsActivity.class);
        startActivity(intent);
    }

}
