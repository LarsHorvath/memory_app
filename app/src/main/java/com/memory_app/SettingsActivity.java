package com.memory_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "SettingsActivity";

    Button clearScores;
    Switch aSwitch;
    AudioManager audioManager;
    BackgroundSoundService backgroundSoundService;
    int lastProgress;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        backgroundSoundService = new BackgroundSoundService();

        clearScores = findViewById(R.id.btn_clearScores);
        aSwitch = findViewById(R.id.switch2);


        clearScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("scores", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Toast.makeText(SettingsActivity.this, "Cleared all Scores", Toast.LENGTH_SHORT).show();
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //backgroundSoundService.startMusic();
                    playBackgroundSound();
                } else {
                    //backgroundSoundService.stopMusic();
                    stopBackgroundSound();
                }
            }
        });


        lastProgress = 0;


    }

    public void stopBackgroundSound() {
        Intent intent = new Intent(SettingsActivity.this, BackgroundSoundService.class);
        stopService(intent);
    }

    public void playBackgroundSound() {
        Intent intent = new Intent(SettingsActivity.this, BackgroundSoundService.class);
        startService(intent);
    }
}
