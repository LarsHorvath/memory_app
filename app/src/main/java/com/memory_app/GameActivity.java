package com.memory_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Settings from previous Activity
        GameSettings gameSettings = (GameSettings) getIntent().getSerializableExtra("settings");

        switch (gameSettings.getMode()){
            case 1:
                setContentView(R.layout.activity_game_3x2);
                break;
            case 2:
                setContentView(R.layout.activity_game_4x3);
                break;
            case 3:
                setContentView(R.layout.activity_game_5x4);
                break;
            default: setContentView(R.layout.activity_game);
        }


    }
}
