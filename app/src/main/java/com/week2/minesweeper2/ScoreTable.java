package com.week2.minesweeper2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ScoreTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_table);
    }
    public void goBack(View view){
        Intent intent=new Intent(ScoreTable.this,MainMenu.class);
        startActivity(intent);
    }
}