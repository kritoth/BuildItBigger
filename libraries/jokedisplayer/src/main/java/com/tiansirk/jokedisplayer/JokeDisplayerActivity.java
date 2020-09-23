package com.tiansirk.jokedisplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.tiansirk.jokedisplayer.databinding.ActivityJokeDisplayerBinding;

public class JokeDisplayerActivity extends AppCompatActivity {

    public static final String INTENT_KEY_JOKE = "joke";

    private ActivityJokeDisplayerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_displayer);

        initViews();
        showJoke();
    }

    private void initViews(){
        binding = ActivityJokeDisplayerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private void showJoke(){
        if(getIntent() != null && getIntent().hasExtra(INTENT_KEY_JOKE)) showData();
        else showError();
    }

    private void showData(){
        binding.tvJokeDisplayer.setText(getIntent().getStringExtra(INTENT_KEY_JOKE));
    }
    private void showError(){
        binding.tvJokeDisplayer.setText("Error receiving joke!");
    }
}