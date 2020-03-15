package com.memory_app;

import java.io.Serializable;

public class GameSettings implements Serializable {
    private int numberOfPlayers;
    private String namePlayer1, namePlayer2;
    private boolean ai;
    private String aiString;
    private String difficulty;
    private int colums, rows;
    private int mode;

    public GameSettings() {
        numberOfPlayers = 0;
        namePlayer1 = "Player 1";
        namePlayer2 = "Player 2";
        ai = false;
        aiString = "";
        difficulty = "0x0";
        colums = 3;
        rows = 2;
        mode = 0;
    }

    // Constructor
    public GameSettings(int numberOfPlayers, String namePlayer1, String namePlayer2, boolean ai, String aiString, String difficulty, int colums, int rows, int mode) {
        this.numberOfPlayers = numberOfPlayers;
        this.namePlayer1 = namePlayer1;
        this.namePlayer2 = namePlayer2;
        this.ai = ai;
        this.aiString = aiString;
        this.difficulty = difficulty;
        this.colums = colums;
        this.rows = rows;
        this.mode = mode;
    }

    // Getters & Setters


    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getNamePlayer1() {
        return namePlayer1;
    }

    public void setNamePlayer1(String namePlayer1) {
        this.namePlayer1 = namePlayer1;
    }

    public String getNamePlayer2() {
        return namePlayer2;
    }

    public void setNamePlayer2(String namePlayer2) {
        this.namePlayer2 = namePlayer2;
    }

    public boolean isAi() {
        return ai;
    }

    public void setAi(boolean ai) {
        this.ai = ai;
    }

    public String getAiString() {
        return aiString;
    }

    public void setAiString(String aiString) {
        this.aiString = aiString;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getColums() {
        return colums;
    }

    public void setColums(int colums) {
        this.colums = colums;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows > 6) rows = 6;
        this.rows = rows;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
