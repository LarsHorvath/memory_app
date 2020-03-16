package com.memory_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Settings from previous Activity
        GameSettings gameSettings = (GameSettings) getIntent().getSerializableExtra("settings");
        Log.d(TAG, "onCreate: gameSettings, Mode: "+gameSettings.getMode()+" colum: "+gameSettings.getColums()+"  rows: "+gameSettings.getRows());


        switch (gameSettings.getColums()){
            case 3:
                setContentView(R.layout.activity_game_3x2);
                break;
            case 4:
                setContentView(R.layout.activity_game_4x3);
                break;
            case 5:
                setContentView(R.layout.activity_game_5x4);
                break;
            case 6:
                setContentView(R.layout.activity_game_6x5);
                break;
            case 7:
                setContentView(R.layout.activity_game_7x6);
                break;
            default: setContentView(R.layout.activity_game);
        }

        Log.d(TAG, "onCreate: gameSettings, Mode: "+gameSettings.getMode()+" colum: "+gameSettings.getColums()+"  rows: "+gameSettings.getRows());


    }
}
