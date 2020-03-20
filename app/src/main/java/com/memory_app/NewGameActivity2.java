package com.memory_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class NewGameActivity2 extends AppCompatActivity {

    private static final String TAG = "NewGameActivity2";

    SeekBar seekBar;
    TextView txt_difficulty;
    Button back, next, numbers, letters, shapes, logos;
    String[] difficulties = {"3x2", "4x3", "5x4", "6x5", "7x6", "8x6"};

    enum ModeSelection {NUMBERS, LETTERS, SHAPES, ANIMALS}

    // Preferences
    GameSettings gameSettings;
    ModeSelection modeSelection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game2);

        txt_difficulty = findViewById(R.id.txt_difficulty);
        back = findViewById(R.id.btn_back2);
        next = findViewById(R.id.btn_next2);
        numbers = findViewById(R.id.btn_mode_123);
        letters = findViewById(R.id.btn_mode_abc);
        shapes = findViewById(R.id.btn_mode_shapes);
        logos = findViewById(R.id.btn_mode_logo);
        seekBar = findViewById(R.id.seekBar);

        gameSettings = new GameSettings();
        modeSelection = ModeSelection.NUMBERS;
        updateModes();

        // Get Bundle with Preferences from Activity before
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        gameSettings.setNumberOfPlayers(bundle.getInt("numberOfPlayers"));
        gameSettings.setNamePlayer1(bundle.getString("namePlayer1"));
        gameSettings.setNamePlayer2(bundle.getString("namePlayer2"));
        gameSettings.setAi(bundle.getBoolean("AI"));
        gameSettings.setAiString(bundle.getString("AiString"));
        gameSettings.setColums(5);
        gameSettings.setRows(4);


        setOnClickListeners();


    }

    private void setOnClickListeners() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txt_difficulty.setText(difficulties[i]);
                gameSettings.setColums(i + 3);
                if (i == 5) i = 4;
                gameSettings.setRows(i + 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modeSelection = ModeSelection.NUMBERS;
                updateModes();
            }
        });
        letters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modeSelection = ModeSelection.LETTERS;
                updateModes();
            }
        });
        shapes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modeSelection = ModeSelection.SHAPES;
                updateModes();
            }
        });
        logos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modeSelection = ModeSelection.ANIMALS;
                updateModes();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: gameSettings, Mode: " + gameSettings.getMode() + " colum: " + gameSettings.getColums() + "  rows: " + gameSettings.getRows());
                Intent intent = new Intent(NewGameActivity2.this, GameActivity.class);
                intent.putExtra("settings", gameSettings);
                startActivity(intent);
            }
        });

    }

    private void updateModes() {
        int mode = 0;

        numbers.setBackgroundTintList(NewGameActivity2.this.getResources().getColorStateList(R.color.buttongray));
        letters.setBackgroundTintList(NewGameActivity2.this.getResources().getColorStateList(R.color.buttongray));
        shapes.setBackgroundTintList(NewGameActivity2.this.getResources().getColorStateList(R.color.buttongray));
        logos.setBackgroundTintList(NewGameActivity2.this.getResources().getColorStateList(R.color.buttongray));

        switch (modeSelection) {
            case NUMBERS:
                mode = 1;
                numbers.setBackgroundTintList(NewGameActivity2.this.getResources().getColorStateList(R.color.colorAccent));
                break;
            case LETTERS:
                mode = 2;
                letters.setBackgroundTintList(NewGameActivity2.this.getResources().getColorStateList(R.color.colorAccent));
                break;
            case SHAPES:
                mode = 3;
                shapes.setBackgroundTintList(NewGameActivity2.this.getResources().getColorStateList(R.color.colorAccent));
                break;
            case ANIMALS:
                mode = 4;
                logos.setBackgroundTintList(NewGameActivity2.this.getResources().getColorStateList(R.color.colorAccent));
                break;
        }

        gameSettings.setMode(mode);
    }

}
