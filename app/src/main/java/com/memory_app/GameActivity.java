package com.memory_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get Settings from previous Activity
        GameSettings gameSettings = (GameSettings) getIntent().getSerializableExtra("settings");
    }
}
