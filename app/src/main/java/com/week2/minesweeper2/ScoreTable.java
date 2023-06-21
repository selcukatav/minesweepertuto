package com.week2.minesweeper2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ScoreTable extends AppCompatActivity {
    ListView listView;

    private ArrayAdapter<Integer> adapter;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_table);
        listView = findViewById(R.id.scoreView);

        sharedPreferences = getSharedPreferences("com.week2.minesweeper2", Context.MODE_PRIVATE);

        //loads the scores on listView from sharedPreferences
        Set<String> scoreSet = sharedPreferences.getStringSet("scores", new HashSet<String>());
        ArrayList<Integer> scores = new ArrayList<>();
        for (String scoreString : scoreSet) {
            scores.add(Integer.parseInt(scoreString));
        }

        //lists scores reversed. new to old.
        Collections.sort(scores, Collections.reverseOrder());

        //creates listView adapter to send items to the listView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scores);
        listView.setAdapter(adapter);

    }
    public void goBack(View view){
        Intent intent=new Intent(ScoreTable.this,MainMenu.class);
        startActivity(intent);
    }


}