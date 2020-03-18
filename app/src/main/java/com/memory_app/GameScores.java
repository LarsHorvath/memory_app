package com.memory_app;

import android.content.Intent;

import java.io.Serializable;

public class GameScores implements Comparable<GameScores>, Serializable {
    private int columns;
    private String namePlayer;
    private int score;
    private String time;

    public GameScores(){
        columns = 0;
        namePlayer = "";
        score = 0;
        time = "0:00";
    }

    public GameScores(int columns, String namePlayer, int score, String time) {
        this.columns = columns;
        this.namePlayer = namePlayer;
        this.score = score;
        this.time = time;
    }

    @Override
    public int compareTo(GameScores o) {
        return Integer.compare(this.score, o.score);
    }

    public int getIntMode(){
        return columns;
    }

    public String getMode() {
        String mode = new String();
        switch (columns){
            case 3:
                mode = "3x2";
                break;
            case 4:
                mode = "4x3";
                break;
            case 5:
                mode = "5x4";
                break;
            case 6:
                mode = "6x5";
                break;
            case 7:
                mode = "7x6";
                break;
            case 8:
                mode= "8x6";
                break;
        }
        return mode;
    }

    public void setMode(int columns) {
        this.columns = columns;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
