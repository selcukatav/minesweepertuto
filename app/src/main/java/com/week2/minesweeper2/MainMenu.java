package com.week2.minesweeper2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import java.util.Objects;


public class MainMenu extends AppCompatActivity { //MAIN MENU


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Switch aSwitch = findViewById(R.id.dayNightswitch);
        Objects.requireNonNull(getSupportActionBar());

        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); //Makes the theme mode night.
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);//Makes the theme mode day.
            }
        });


    }

    public void newGame(View view) { //if you press the button it sends you on the ChooseDiff screen.
        Intent intent = new Intent(MainMenu.this, ChooseDiff.class);
        startActivity(intent);
    }

    public void scoreTable(View view) {//if you press the button it sends you on the ScoreTable screen.
        Intent intent = new Intent(MainMenu.this, ScoreTable.class);
        startActivity(intent);
    }
    public void goAbout(View view) { //if you press the button it sends you on the ChooseDiff screen.
        Intent intent = new Intent(MainMenu.this, AboutActivity.class);
        startActivity(intent);
    }


}