package com.memory_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Button newgame, quickgame, score, settings, exit;
    GameSettings gameSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the Buttons
        newgame = findViewById(R.id.btn_new_game);
        quickgame = findViewById(R.id.btn_quick_game);
        score = findViewById(R.id.btn_scores);
        settings = findViewById(R.id.btn_settings);
        exit = findViewById(R.id.btn_exit);


        setOnclickListeners();


    }

    private void setOnclickListeners() {
        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewGameActivity.class);
                startActivity(intent);
            }
        });
        quickgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setup a Quick Game with: Letters, 5x4, 1 Player
                gameSettings = new GameSettings(1, "Player 1", "", false, "", "5x4", 5, 4, 2);
                Log.d(TAG, "Quick Game: gameSettings, Mode: " + gameSettings.getMode() + " colum: " + gameSettings.getColums() + "  rows: " + gameSettings.getRows());

                // Start GameActivity with Intent containing gameSettings
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("settings", gameSettings);
                startActivity(intent);
            }
        });
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                GameScores gameScores = new GameScores(-1,"",0,"");
                intent.putExtra("lastScore", gameScores);
                startActivity(intent);

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
                finish();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}
