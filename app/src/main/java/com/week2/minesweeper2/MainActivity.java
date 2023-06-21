package com.week2.minesweeper2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class MainActivity extends AppCompatActivity {


    SharedPreferences sharedPreferences;
    TextView textView, bombCounter_view;
    Runnable runnable;
    Handler handler;
    int number;
    int score;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);//MineSweeper Grid

        textView = findViewById(R.id.timerTxt);//timer text view
        button = findViewById(R.id.saveScoreBtn);
        GameEngine.getInstance().createGrid(this);//creates grid on this activity.

        bombCounter_view = findViewById(R.id.bombCounter);
        handler = new Handler(Looper.getMainLooper()); //handler and runnable: its like try catch func but in android.
        runnable = new Runnable() {
            @Override
            public void run() {
                //counts up period with 1 sec.
                if (GameEngine.isGameOver == false) {
                    textView.setText("" + number);
                    number++;

                    bombCounter_view.setText("" + GameEngine.getInstance().bombNumberCounter);
                    handler.postDelayed(runnable, 1000);
                }
            }
        };
        handler.post(runnable);
        button.setEnabled(true);
    }


    public void restart(View view) { //restarts the game grid view.
        handler.removeCallbacks(runnable);
        number = 0;
        textView.setText("" + number);
        handler.post(runnable);

        GameEngine.getInstance().createGrid(this);

    }

    public void saveScore(View view) {


        if (GameEngine.isGameWin==true) {
            String remainingTime = textView.getText().toString();
            int number = Integer.parseInt(remainingTime);
            int bombNumber = GameEngine.getInstance().BOMB_NUMBER;
            int score = bombNumber * 400 / number;
            int MAX_SCORE_NUMBER = 8;
            //gets the score data and stores it in shared preferences.
            sharedPreferences = getSharedPreferences("com.week2.minesweeper2", Context.MODE_PRIVATE);


            Set<String> scoreSet = sharedPreferences.getStringSet("scores", new HashSet<String>());
            ArrayList<Integer> scores = new ArrayList<>();
            for (String scoreString : scoreSet) {
                scores.add(Integer.parseInt(scoreString));
            }
            scores.add(score);

            Collections.sort(scores, Collections.reverseOrder());

            if (scores.size() > MAX_SCORE_NUMBER) {
                scores = new ArrayList<>(scores.subList(0, MAX_SCORE_NUMBER));
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            scoreSet = new HashSet<>();
            for (int scoreValue : scores) {
                scoreSet.add(String.valueOf(scoreValue));
            }
            editor.putStringSet("scores", scoreSet);
            editor.apply();
            //sends data to ScoreTable.
            Intent intent = new Intent(this, ScoreTable.class);
            startActivity(intent);

        }else{
            Toast.makeText(this, "Lütfen önce oyunu kazanın!", Toast.LENGTH_LONG).show();
        }

    }


}
