package com.week2.minesweeper2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import com.week2.minesweeper2.views.grid.BaseCell;
import com.week2.minesweeper2.views.grid.Cell;
import com.week2.minesweeper2.views.grid.Grid;
import com.week2.minesweeper2.GameEngine;


public class MainActivity extends AppCompatActivity {


    public int BOMB_NUMBER=4;


    private int remainingMines;
    Grid grid;
    TextView textView, bombCounter;
    Runnable runnable;
    Handler handler;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);//MineSweeper Grid
        bombCounter = findViewById(R.id.bombCounter);//Bomb counter text view component.
        textView = findViewById(R.id.timerTxt);//timer text view

        GameEngine.getInstance().createGrid(this);//creates grid on this activity.


        handler = new Handler(Looper.getMainLooper()); //handler and runnable: its like try catch func but in android.
        runnable = new Runnable() {
            @Override
            public void run() {
                //counts up period with 1 sec.
                if (GameEngine.isGameOver == false) {
                    textView.setText("" + number);
                    number++;
                    textView.setText("" + number);
                    handler.postDelayed(runnable, 1000);
                }
            }
        };
        handler.post(runnable);
    }


    public void restart(View view) { //restarts the game grid view.
        handler.removeCallbacks(runnable);
        number = 0;
        textView.setText("" + number);
        handler.post(runnable);

        GameEngine.getInstance().createGrid(this);

    }





}