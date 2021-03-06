package com.memory_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class ScoreActivity extends AppCompatActivity {

    private static final String TAG = "ScoreActivity";
    ArrayList<GameScores> scores;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        back = findViewById(R.id.btn_scoreToHome);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        scores = new ArrayList<>();

        GameScores lastScore = (GameScores) getIntent().getSerializableExtra("lastScore");
        assert lastScore != null;
        Log.d(TAG, "onCreate: gameScores: column: " + lastScore.getMode() + " name: " + lastScore.getNamePlayer() + " score: " + lastScore.getScore());

        loadScores();
        if (lastScore.getIntMode() != -1) {
            scores.add(lastScore);
        }

        initRecyclerView();

        saveScores();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.scores_RecyclerView);
        ScoreTileAdapter adapter = new ScoreTileAdapter(this, scores);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void saveScores() {
        SharedPreferences sharedPreferences = getSharedPreferences("scores", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(scores);
        editor.putString("scoreList", json);
        editor.apply();
    }

    public void loadScores() {
        Log.d(TAG, "loadScores: Load Shared Preferences");
        SharedPreferences sharedPreferences = getSharedPreferences("scores", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("scoreList", null);
        Log.d(TAG, "loadScores: JSON String: " + json);
        Type type = new TypeToken<ArrayList<GameScores>>() {
        }.getType();
        if (gson.fromJson(json, type) == null) {
            scores = new ArrayList<>();
        } else {
            scores = gson.fromJson(json, type);
        }

    }
}
