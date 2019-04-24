package com.example.nour.chatapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Nour on 5/31/2017.
 */

public class About extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        getSupportActionBar().setTitle("About");
    }
}
