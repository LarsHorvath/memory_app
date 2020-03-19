package com.memory_app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreTileAdapter extends RecyclerView.Adapter<ScoreTileAdapter.ScoreViewHolder> {

    private static final String TAG = "RecyclerViewAdapter Scores";
    private String[] difficulties = {"3x2", "4x3", "5x4", "6x5", "7x6", "8x6"};
    private ArrayList<GameScores> scores;
    private Context mContext;

    public ScoreTileAdapter(Context ct, ArrayList<GameScores> scores) {
        mContext = ct;
        this.scores = scores;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.score_tile, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: position: " + position);
        Log.d(TAG, "onBindViewHolder: scores.size()=" + scores.size());

        ArrayList<GameScores> currentMode = new ArrayList<>();
        boolean flag = false;
        int addedElements = 0;

        // position = difficulty
        for (int i = 0; i < scores.size(); i++) {
            Log.d(TAG, "onBindViewHolder: scores.get(i).getIntMode()=" + scores.get(i).getIntMode());
            if (scores.get(i).getIntMode() == position + 3) {
                flag = true;
                addedElements++;
                currentMode.add(scores.get(i));
            }
        }

        Collections.sort(currentMode);

        holder.mModeTitle.setText(difficulties[position]);

        // Clear other Entries
        holder.mPlayer1.setText("-");
        holder.mScore1.setText("-");
        holder.mTime1.setText("-");
        holder.mPlayer2.setText("-");
        holder.mScore2.setText("-");
        holder.mTime2.setText("-");
        holder.mPlayer3.setText("-");
        holder.mScore3.setText("-");
        holder.mTime3.setText("-");

        if (flag) {
            Log.d(TAG, "onBindViewHolder: flag = ture, addedElements=" + addedElements);
            holder.mPlayer1.setText(currentMode.get(0).getNamePlayer());
            holder.mScore1.setText(Integer.toString(currentMode.get(0).getScore()));
            holder.mTime1.setText(currentMode.get(0).getTime());
            if (addedElements > 1) {
                holder.mPlayer2.setText(currentMode.get(1).getNamePlayer());
                holder.mScore2.setText(Integer.toString(currentMode.get(1).getScore()));
                holder.mTime2.setText(currentMode.get(1).getTime());
            }
            if (addedElements > 2) {
                holder.mPlayer3.setText(currentMode.get(2).getNamePlayer());
                holder.mScore3.setText(Integer.toString(currentMode.get(2).getScore()));
                holder.mTime3.setText(currentMode.get(2).getTime());
            }
        }


    }

    @Override
    public int getItemCount() {
        /*
        int itemCount = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0, count7 = 0, count8 = 0;
        for (int i = 0; i < scores.size(); i++) {
            int mode = scores.get(i).getIntMode();
            switch (mode) {
                case 3:
                    count3++;
                    break;
                case 4:
                    count4++;
                    break;
                case 5:
                    count5++;
                    break;
                case 6:
                    count6++;
                    break;
                case 7:
                    count7++;
                    break;
                case 8:
                    count8++;
                    break;
            }
        }

        if (count3 > 0) itemCount++;
        if (count4 > 0) itemCount++;
        if (count5 > 0) itemCount++;
        if (count6 > 0) itemCount++;
        if (count7 > 0) itemCount++;
        if (count8 > 0) itemCount++;

        return itemCount;*/
        return 6;
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder {

        TextView mModeTitle, mPlayer1, mPlayer2, mPlayer3, mScore1, mScore2, mScore3, mTime1, mTime2, mTime3;
        ConstraintLayout mainLayout;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            mModeTitle = itemView.findViewById(R.id.txt_score_mode);
            mPlayer1 = itemView.findViewById(R.id.txt_score_namePlayer1);
            mPlayer2 = itemView.findViewById(R.id.txt_score_namePlayer2);
            mPlayer3 = itemView.findViewById(R.id.txt_score_namePlayer3);
            mScore1 = itemView.findViewById(R.id.txt_score_1);
            mScore2 = itemView.findViewById(R.id.txt_score_2);
            mScore3 = itemView.findViewById(R.id.txt_score_3);
            mTime1 = itemView.findViewById(R.id.txt_score_time1);
            mTime2 = itemView.findViewById(R.id.txt_score_time2);
            mTime3 = itemView.findViewById(R.id.txt_score_time3);

            mainLayout = itemView.findViewById(R.id.mainLayout_scores);
        }
    }


}
