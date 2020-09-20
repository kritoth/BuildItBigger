package com.udacity.gradle.flavorspecificactivity.paid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.udacity.gradle.flavorspecificactivity.R;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        String joke = getIntent().getStringExtra("joke");
        TextView textView = findViewById(R.id.tv_joke);
        textView.setText(joke);
    }
}