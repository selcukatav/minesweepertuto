package com.week2.minesweeper2.views.grid;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.week2.minesweeper2.GameEngine;

public abstract class BaseCell extends View {

    private int value;
    private boolean isBomb;//if there is a bomb then it's true, if its not then it's false.
    private boolean isRevealed; //if clicks the not mine button, the all the zeros that connected will be revealed.
    private boolean isClicked;//if the bomb is clicked or not
    private boolean isFlagged;//marking the bomb
    private int flagCount;

    private int x, y;
    public BaseCell(Context context){
        super(context);
    }

    private int position;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        isBomb = false;
        isRevealed = false;
        isClicked = false;
        isFlagged = false;

        if (value == -1) {
            isBomb = true;
        }
        this.value = value;


    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed() {

        isRevealed = true;
        invalidate();
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked() {
        this.isClicked = true;
        this.isRevealed=true;
        invalidate();
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public boolean setFlagged(boolean flagged) {

        isFlagged = flagged;

        return flagged;
    }

    public  int getXPos() {
        return x;
    }


    public int getYPos() {
        return y;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int x,int y) {
        this.x=x;
        this.y=y;

        this.position=y*GameEngine.WIDTH+x;


        invalidate();
    }



}
