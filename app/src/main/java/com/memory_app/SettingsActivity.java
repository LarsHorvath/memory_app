package com.memory_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    Button clearScores;
    Switch aSwitch;
    SeekBar seekBar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        clearScores = findViewById(R.id.btn_clearScores);
        aSwitch = findViewById(R.id.switch2);
        seekBar = findViewById(R.id.seekBar2);

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
                if (isChecked){
                    playBackgroundSound();
                }else {
                    stopBackgroundSound();
                }
            }
        });

        //BackgroundSoundService backgroundSoundService = new BackgroundSoundService();
        //backgroundSoundService.mediaPlayer.setVolume(100,100);

    }

    public void stopBackgroundSound(){
        Intent intent = new Intent(SettingsActivity.this, BackgroundSoundService.class);
        stopService(intent);
    }

    public void playBackgroundSound() {
        Intent intent = new Intent(SettingsActivity.this, BackgroundSoundService.class);
        startService(intent);
    }
}
