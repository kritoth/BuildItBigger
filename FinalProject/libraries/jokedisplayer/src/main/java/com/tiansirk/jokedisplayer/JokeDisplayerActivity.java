package com.tiansirk.jokedisplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class JokeDisplayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_displayer);

        initViews();

        showJoke();
    }

    private void initViews(){

    }

    private void showJoke(){

    }
}