package com.memory_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";

    Button[][] buttons;
    int[] buttonIDs;
    int columns, rows, numTries, totalTries, cardsRemaining, lastI, lastJ;
    TextView txt_tries, txt_timer;
    Button pause;
    boolean mutexTurnCard;

    enum BoardStatus {INIT, TURNED, MATCH}

    BoardStatus[][] boardStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Settings from previous Activity
        GameSettings gameSettings = (GameSettings) getIntent().getSerializableExtra("settings");
        assert gameSettings != null;


        // Select the Grid Layout
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

        columns = gameSettings.getColums();
        rows = gameSettings.getRows();
        // initialize buttons, board and counters
        buttons = new Button[gameSettings.getColums()][gameSettings.getRows()];
        boardStatus = new BoardStatus[columns][rows];
        numTries = 0;
        totalTries = 0;
        cardsRemaining = columns * rows / 2;
        lastI = 0;
        lastJ = 0;
        mutexTurnCard = false;

        findViewByIdAndOnClick();

        fillGrid(gameSettings.getMode(), columns, rows);
    } // END of onCreate

    private void turnCard(final int i, final int j) {
        Log.d(TAG, "turnCard: at position i=" + i + " j=" + j);
        if (mutexTurnCard) return;
        mutexTurnCard = true;
        numTries++;
        totalTries++;
        txt_tries.setText("" + totalTries);
        boolean match = false;
        buttons[i][j].setTextColor(getResources().getColor(android.R.color.darker_gray));
        if (numTries == 1) {
            lastI = i;
            lastJ = j;
            boardStatus[i][j] = BoardStatus.TURNED;
            mutexTurnCard = false; // release Mutex
        }
        if (numTries == 2) {
            if (buttons[i][j].getText().equals(buttons[lastI][lastJ].getText())) {
                Log.d(TAG, "turnCard: numTries = 2, MATCH");
                cardsRemaining -= 2;
                boardStatus[i][j] = BoardStatus.MATCH;
                boardStatus[lastI][lastJ] = BoardStatus.MATCH;
                match = true;
            } else {
                Log.d(TAG, "turnCard: numTries = 2, NO MATCH");
                boardStatus[i][j] = BoardStatus.INIT;
                boardStatus[lastI][lastJ] = BoardStatus.INIT;
            }
            numTries = 0;
            final boolean finalMatch = match;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "run: waited 2 sec, finalMATCH=" + finalMatch);
                    if (finalMatch) {
                        Log.d(TAG, "run: set all transparent");
                        buttons[i][j].setTextColor(getResources().getColor(android.R.color.transparent));
                        buttons[lastI][lastJ].setTextColor(getResources().getColor(android.R.color.transparent));
                        buttons[i][j].setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        buttons[lastI][lastJ].setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    } else {
                        buttons[i][j].setTextColor(getResources().getColor(android.R.color.transparent));
                        buttons[lastI][lastJ].setTextColor(getResources().getColor(android.R.color.transparent));
                    }
                    mutexTurnCard = false; // release Mutex
                }
            }, 2000);
        }


    }

    // findViewByIDs and set OnClick Listeners
    private void findViewByIdAndOnClick() {
        // Find Views By ID and set OnClickListener
        int n = 0;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
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
        txt_timer = findViewById(R.id.txt_time);
        txt_tries = findViewById(R.id.txt_trys);
        txt_tries.setText("0");
        txt_timer.setText("0:00");
    }

    // fill Grid according to size of grid and mode
    private void fillGrid(int mode, int colums, int rows) {
        int gridLength = colums * rows / 2;
        Log.d(TAG, "fillGrid: gridLenth: " + gridLength);
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < gridLength; ++i) {
            numbers.add(i);
        }
        ArrayList<String> selection = new ArrayList<>();
        if (mode == 1) {
            for (int i = 0; i < gridLength; ++i) {
                selection.add(numbers.get(i).toString());
            }
        } else if (mode == 2) {
            selection = makeLetterSelection(alphabet, gridLength);
        }
        // Duplicate selection and shuffle
        selection.addAll(selection);
        Collections.shuffle(selection);

        // fill the buttons with the shuffled selection and hide text
        int m = 0;
        for (int i = 0; i < colums; ++i) {
            for (int j = 0; j < rows; ++j) {
                buttons[i][j].setText(selection.get(m));
                buttons[i][j].setTextColor(getResources().getColor(android.R.color.transparent));
                boardStatus[i][j] = BoardStatus.INIT;
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
    public ArrayList<String> makeLetterSelection(char[] alphabet, int gridLength) {
        Log.d(TAG, "makeSelection: ");
        ArrayList<String> selection = new ArrayList<>();
        for (int i = 0; i < gridLength; i++) {
            Character s = getRandomChar(alphabet);
            while (selection.contains(s)) {
                // while s already in selection get new one
                s = getRandomChar(alphabet);
            }
            Log.d(TAG, "makeSelection: s:" + s);
            selection.add(s.toString());
        }
        return selection;
    }

}
