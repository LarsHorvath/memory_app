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
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "SettingsActivity";

    Button clearScores;
    Switch aSwitch;
    SeekBar seekBar;
    TextView txt_delay;
    BackgroundSoundService backgroundSoundService;
    boolean musicIsChecked;
    float delay;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        backgroundSoundService = new BackgroundSoundService();

        clearScores = findViewById(R.id.btn_clearScores);
        aSwitch = findViewById(R.id.switch2);
        seekBar = findViewById(R.id.seekBar2);
        txt_delay = findViewById(R.id.txt_settings_delay);

        musicIsChecked = false;
        SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        musicIsChecked = sharedPreferences.getBoolean("musicIsChecked", musicIsChecked);
        delay = sharedPreferences.getFloat("delay",2);
        aSwitch.setChecked(musicIsChecked);
        seekBar.setProgress((int) (delay * 10), true);
        txt_delay.setText(""+delay+" s");


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
                SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (isChecked) {
                    editor.putBoolean("musicIsChecked", true);
                    playBackgroundSound();
                } else {
                    editor.putBoolean("musicIsChecked", false);
                    stopBackgroundSound();
                }
                editor.apply();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float tempProg = progress;
                delay = tempProg / 10;

                SharedPreferences sharedPreferences1 = getSharedPreferences("settings", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putFloat("delay", delay);
                editor.apply();
                Log.d(TAG, "onProgressChanged: delay = " + delay);
                txt_delay.setText("" +delay+" s");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


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
