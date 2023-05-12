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


public class MainActivity extends AppCompatActivity {


    public int BOMB_NUMBER=4;
    private Cell[][] cells;

    private int remainingMines;
    Grid grid;
    TextView textView;
    Runnable runnable;
    Handler handler;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        textView = findViewById(R.id.bombCounter);
        textView = findViewById(R.id.timerTxt);
        grid=findViewById(R.id.mineSweeperGridView);
        textView=findViewById(R.id.bombCounter);



        remainingMines=BOMB_NUMBER;

        GameEngine.getInstance().createGrid(this);


        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {

                textView.setText("" + number);
                number++;
                textView.setText("" + number);
                handler.postDelayed(runnable, 1000);


            }
        };
        handler.post(runnable);
    }


    public void restart(View view) {
        handler.removeCallbacks(runnable);
        number = 0;
        textView.setText("" + number);
        handler.post(runnable);

        GameEngine.getInstance().createGrid(this);

    }
    /*private void calculateRemainingMines() {
        int flagCount = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].setFlagged(true)) {
                    flagCount++;


                }
            }
        }
        remainingMines = BOMB_NUMBER - flagCount;

    }*/




}