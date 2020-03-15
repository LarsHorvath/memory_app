package com.memory_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

public class NewGameActivity extends AppCompatActivity {

    private static final String TAG = "NewGameActivity";
    CompoundButton player1, player2, easy, medium, hard;
    Button back, next;
    EditText txt_plaxer1, txt_player2;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        player1 = findViewById(R.id.btn_1);
        player2 = findViewById(R.id.btn_2);
        easy = findViewById(R.id.btn_easy);
        medium = findViewById(R.id.btn_medium);
        hard = findViewById(R.id.btn_hard);
        back = findViewById(R.id.btn_back);
        next = findViewById(R.id.btn_next);
        txt_plaxer1 = findViewById(R.id.editText);
        txt_player2 = findViewById(R.id.editText2);

        // Set visibilities and colors
        player1.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorAccent));
        txt_player2.setVisibility(View.INVISIBLE);
        easy.setVisibility(View.INVISIBLE);
        medium.setVisibility(View.INVISIBLE);
        hard.setVisibility(View.INVISIBLE);


        setOnClickListeners();

    } // END onCreate

    public void setOnClickListeners() {
        player1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    player1.setBackgroundTintList(NewGameActivity.this.getResources().getColorStateList(R.color.colorAccent));
                } else {
                    player1.setBackgroundTintList(NewGameActivity.this.getResources().getColorStateList(R.color.buttongray));
                }
            }
        });
        player2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    player2.setBackgroundTintList(NewGameActivity.this.getResources().getColorStateList(R.color.colorAccent));
                } else {
                    player2.setBackgroundTintList(NewGameActivity.this.getResources().getColorStateList(R.color.buttongray));
                }
            }
        });
    }
}
