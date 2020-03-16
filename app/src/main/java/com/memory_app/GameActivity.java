package com.memory_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";

    Button[][] buttons;
    int[] buttonIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Settings from previous Activity
        GameSettings gameSettings = (GameSettings) getIntent().getSerializableExtra("settings");
        assert gameSettings != null;
        buttons = new Button[gameSettings.getColums()][gameSettings.getRows()];

        // Select the Grid
        switch (gameSettings.getColums()) {
            case 3:
                buttonIDs = new int[]{R.id.tile3_1, R.id.tile3_2, R.id.tile3_3, R.id.tile3_4, R.id.tile3_5, R.id.tile3_6};
                setContentView(R.layout.activity_game_3x2);
                break;
            case 4:
                buttonIDs = new int[]{R.id.tile4_1, R.id.tile4_2, R.id.tile4_3, R.id.tile4_4, R.id.tile4_5, R.id.tile4_6, R.id.tile4_7, R.id.tile4_8, R.id.tile4_9, R.id.tile4_10, R.id.tile4_11, R.id.tile4_12};
                setContentView(R.layout.activity_game_4x3);
                break;
            case 5:
                buttonIDs = new int[]{R.id.tile5_1, R.id.tile5_2, R.id.tile5_3, R.id.tile5_4, R.id.tile5_5, R.id.tile5_6, R.id.tile5_7, R.id.tile5_8, R.id.tile5_9, R.id.tile5_10, R.id.tile5_11, R.id.tile5_12, R.id.tile5_13, R.id.tile5_14, R.id.tile5_15, R.id.tile5_16, R.id.tile5_17, R.id.tile5_18, R.id.tile5_19, R.id.tile5_20};
                setContentView(R.layout.activity_game_5x4);
                break;
            case 6:
                buttonIDs = new int[]{R.id.tile6_1, R.id.tile6_2, R.id.tile6_3, R.id.tile6_4, R.id.tile6_5, R.id.tile6_6, R.id.tile6_7, R.id.tile6_8, R.id.tile6_9, R.id.tile6_10, R.id.tile6_11, R.id.tile6_12, R.id.tile6_13, R.id.tile6_14, R.id.tile6_15, R.id.tile6_16, R.id.tile6_17, R.id.tile6_18, R.id.tile6_19, R.id.tile6_20, R.id.tile6_21, R.id.tile6_22, R.id.tile6_23, R.id.tile6_24, R.id.tile6_25, R.id.tile6_26, R.id.tile6_27, R.id.tile6_28, R.id.tile6_29, R.id.tile6_30};
                setContentView(R.layout.activity_game_6x5);
                break;
            case 7:
                buttonIDs = new int[]{R.id.tile7_1, R.id.tile7_2, R.id.tile7_3, R.id.tile7_4, R.id.tile7_5, R.id.tile7_6, R.id.tile7_7, R.id.tile7_8, R.id.tile7_9, R.id.tile7_10, R.id.tile7_11, R.id.tile7_12, R.id.tile7_13, R.id.tile7_14, R.id.tile7_15, R.id.tile7_16, R.id.tile7_17, R.id.tile7_18, R.id.tile7_19, R.id.tile7_20, R.id.tile7_21, R.id.tile7_22, R.id.tile7_23, R.id.tile7_24, R.id.tile7_25, R.id.tile7_26, R.id.tile7_27, R.id.tile7_28, R.id.tile7_29, R.id.tile7_30, R.id.tile7_31, R.id.tile7_32, R.id.tile7_33, R.id.tile7_34, R.id.tile7_35, R.id.tile7_36, R.id.tile7_37, R.id.tile7_38, R.id.tile7_39, R.id.tile7_40, R.id.tile7_41, R.id.tile7_42};
                setContentView(R.layout.activity_game_7x6);
                break;
            default:
                setContentView(R.layout.activity_game);
        }

        // Find Views By ID and set OnClickListener
        int n = 0;
        for (int i = 0; i < gameSettings.getColums(); i++) {
            for (int j = 0; j < gameSettings.getRows(); j++) {
                buttons[i][j] = findViewById(buttonIDs[n]);
                final int finalI = i;
                final int finalJ = j;
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        turnCard(finalI, finalJ);
                    }
                });
                n++;
            }
        }

        fillGrid(gameSettings.getMode(),gameSettings.getColums(),gameSettings.getRows());
    } // END of onCreate

    private void turnCard(int i, int j) {
        Log.d(TAG, "turnCard: i=" + i + " j=" + j);
        buttons[i][j].setTextColor(getResources().getColor(android.R.color.darker_gray));
    }

    // fill Grid according to size of grid and mode
    private void fillGrid(int mode, int colums, int rows) {
        int gridLength = colums * rows / 2;
        Log.d(TAG, "fillGrid: gridLenth: "+gridLength);
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        ArrayList<Character> selection = makeSelection(alphabet, gridLength);

        // Duplicate selection and shuffle
        selection.addAll(selection);
        Collections.shuffle(selection);

        // fill the buttons with the shuffled selection and hide text
        int m = 0;
        for (int i = 0; i < colums; ++i) {
            for (int j = 0; j < rows; ++j) {
                buttons[i][j].setText(selection.get(m).toString());
                buttons[i][j].setTextColor(getResources().getColor(android.R.color.transparent));
                ++m;
            }
        }

    }

    // gets random chars from char array
    public char getRandomChar(char[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    // makes a valid selection out of a char array
    public ArrayList<Character> makeSelection(char[] alphabet, int gridLength) {
        Log.d(TAG, "makeSelection: ");
        ArrayList<Character> selection = new ArrayList<>();
        for (int i = 0; i < gridLength; i++) {
            char s = getRandomChar(alphabet);
            while (selection.contains(s)) {
                // while s already in selection get new one
                s = getRandomChar(alphabet);
            }
            Log.d(TAG, "makeSelection: s:"+s);
            selection.add(s);
        }
        return selection;
    }
}
