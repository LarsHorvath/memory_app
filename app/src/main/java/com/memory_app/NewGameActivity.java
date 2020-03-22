package com.memory_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;


public class NewGameActivity extends AppCompatActivity {

    private static final String TAG = "NewGameActivity";

    CompoundButton player1, player2;
    Button easy, medium, hard;
    Button back, next;
    Switch computer;
    EditText txt_player1, txt_player2;

    // Preferences
    int numberOfPlayers;
    boolean ai;
    String aiString = "";

    enum PlayerSelection {Player1, Player2}
    enum AiSelection {EASY, MEDIUM, HARD}

    PlayerSelection playerSelection = PlayerSelection.Player1;
    AiSelection aiSelection = AiSelection.EASY;


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
        back = findViewById(R.id.btn_back2);
        next = findViewById(R.id.btn_next2);
        txt_player1 = findViewById(R.id.editText);
        txt_player2 = findViewById(R.id.editText2);
        computer = findViewById(R.id.switch1);

        // Set visibilities and colors
        player1.setBackgroundTintList(this.getResources().getColorStateList(R.color.colorAccent));
        txt_player2.setVisibility(View.INVISIBLE);
        easy.setVisibility(View.INVISIBLE);
        medium.setVisibility(View.INVISIBLE);
        hard.setVisibility(View.INVISIBLE);


        setOnClickListeners();

    } // END onCreate

    public void setOnClickListeners() {
        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerSelection = PlayerSelection.Player1;
                updatePlayerSelection();
                txt_player2.setVisibility(View.INVISIBLE);
            }
        });
        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerSelection = PlayerSelection.Player2;
                updatePlayerSelection();
                txt_player2.setVisibility(View.VISIBLE);
            }
        });
        computer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ai = true;
                    easy.setVisibility(View.VISIBLE);
                    medium.setVisibility(View.VISIBLE);
                    hard.setVisibility(View.VISIBLE);
                } else {
                    ai = false;
                    easy.setVisibility(View.INVISIBLE);
                    medium.setVisibility(View.INVISIBLE);
                    hard.setVisibility(View.INVISIBLE);
                }
            }
        });
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aiSelection = AiSelection.EASY;
                updateAiSelection();
            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aiSelection = AiSelection.MEDIUM;
                updateAiSelection();
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aiSelection = AiSelection.HARD;
                updateAiSelection();
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
                startNextActicity();
            }
        });


    } // END of setOnclickListener

    private void updatePlayerSelection() {
        if (playerSelection == PlayerSelection.Player1) {
            numberOfPlayers = 1;
            player1.setBackgroundTintList(NewGameActivity.this.getResources().getColorStateList(R.color.colorAccent));
            player2.setBackgroundTintList(NewGameActivity.this.getResources().getColorStateList(R.color.buttongray));
        } else {
            numberOfPlayers = 2;
            player2.setBackgroundTintList(NewGameActivity.this.getResources().getColorStateList(R.color.colorAccent));
            player1.setBackgroundTintList(NewGameActivity.this.getResources().getColorStateList(R.color.buttongray));
        }
    } // END of updatePlayerSelection()

    private void updateAiSelection() {
        switch (aiSelection) {
            case EASY:
                aiString = "EASY";
                easy.setBackgroundTintList(NewGameActivity.this.getResources().getColorStateList(R.color.colorAccent));
                medium.setBackgroundTintList(NewGameActivity.this.getResources().getColorStateList(R.color.buttongray));
                hard.setBackgroundTintList(NewGameActivity.this.getResources().getColorStateList(R.color.buttongray));
                break;
            case MEDIUM:
                aiString = "MEDIUM";
                easy.setBackgroundTintList(NewGameActivity.this.getResources().getColorStateList(R.color.buttongray));
                medium.setBackgroundTintList(NewGameActivity.this.getResources().getColorStateList(R.color.colorAccent));
                hard.setBackgroundTintList(NewGameActivity.this.getResources().getColorStateList(R.color.buttongray));
                break;
            case HARD:
                aiString = "HARD";
                easy.setBackgroundTintList(NewGameActivity.this.getResources().getColorStateList(R.color.buttongray));
                medium.setBackgroundTintList(NewGameActivity.this.getResources().getColorStateList(R.color.buttongray));
                hard.setBackgroundTintList(NewGameActivity.this.getResources().getColorStateList(R.color.colorAccent));
                break;
        }
    } // END of updateAiSelection()

    private void startNextActicity() {
        Intent intent = new Intent(NewGameActivity.this, NewGameActivity2.class);
        Bundle bundle = new Bundle();

        // parse infos from editText
        String namePlayer1 = txt_player1.getText().toString();
        if (namePlayer1.isEmpty()) namePlayer1 = "Player 1";
        String namePlayer2 = txt_player2.getText().toString();
        if (namePlayer2.isEmpty()) namePlayer2 = "Player 2";


        // put Information into Bundle
        bundle.putInt("numberOfPlayers", numberOfPlayers);
        bundle.putString("namePlayer1", namePlayer1);
        bundle.putString("namePlayer2", namePlayer2);
        bundle.putBoolean("AI", ai);
        bundle.putString("AiString", aiString);

        // Send Bundle with intent
        intent.putExtras(bundle);
        startActivity(intent);
    } // END of startNextActicity()
}
