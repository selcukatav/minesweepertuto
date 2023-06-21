package com.week2.minesweeper2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ChooseDiff extends AppCompatActivity {

    //With choosing the difficulty we setting the mine number on GameEngine class.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_diff);


    }
    public void easy(View view){
        int BOMB_NUMBER=4;

        GameEngine.getInstance().setNumMines(BOMB_NUMBER);

        Intent intent = new Intent(ChooseDiff.this, MainActivity.class);
        startActivity(intent);

    }
    public void medium(View view){
        int BOMB_NUMBER=8;
        GameEngine.getInstance().setNumMines(BOMB_NUMBER);

        Intent intent = new Intent(ChooseDiff.this, MainActivity.class);
        startActivity(intent);
    }

    public void hard(View view){
        int BOMB_NUMBER=12;
        GameEngine.getInstance().setNumMines(BOMB_NUMBER);
        Intent intent = new Intent(ChooseDiff.this, MainActivity.class);
        startActivity(intent);
    }

    public void goBack2(View view) {
        Intent intent = new Intent(ChooseDiff.this, MainMenu.class);
        startActivity(intent);
    }
}