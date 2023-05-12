package com.week2.minesweeper2;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.week2.minesweeper2.util.Generator;
//import com.week2.minesweeper2.util.PrintGrid;
import com.week2.minesweeper2.util.PrintGrid;
import com.week2.minesweeper2.views.grid.Cell;
import com.week2.minesweeper2.views.grid.Grid;


import org.w3c.dom.Text;

public class GameEngine {



    private static GameEngine instance;
    public void setNumMines(int BOMB_NUMBER) {
        this.BOMB_NUMBER = BOMB_NUMBER;
    }
    public int BOMB_NUMBER;
    public static int WIDTH = 6;
    public static int HEIGHT = 6;


    private Context context;



    public int getNumMines() {
        return BOMB_NUMBER;
    }


    private Cell[][] MineSweeperGrid = new Cell[WIDTH][HEIGHT];

    public static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    private GameEngine() {

    }

    public void createGrid(Context context) {
        this.context = context;


        //creating the grid and storing it

        int[][] generatedGrid = Generator.generate(BOMB_NUMBER, WIDTH, HEIGHT);

        PrintGrid.print(generatedGrid, WIDTH, HEIGHT);
        setGrid(context, generatedGrid);

    }

    private void setGrid(Context context, int[][] grid) {


        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (MineSweeperGrid[x][y] == null) {
                    MineSweeperGrid[x][y] = new Cell(context, x, y);
                }
                MineSweeperGrid[x][y].setValue((grid[x][y]));
                MineSweeperGrid[x][y].invalidate();

            }
        }

    }

    public Cell getCellAt(int position) {
        int x = position % WIDTH;
        int y = position / WIDTH;


        return MineSweeperGrid[x][y];
    }

    public Cell getCellAt(int x, int y) {
        return MineSweeperGrid[x][y];
    }


    //onClick event. it checks if the button is pressed or not, if its clicked then you cannot press it again
    public void click(int x, int y) {

        if (x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && !getCellAt(x, y).isClicked()) {
            getCellAt(x, y).setClicked();

            if (getCellAt(x, y).getValue() == 0) {
                for (int xt = -1; xt <= 1; xt++) {
                    for (int yt = -1; yt <= 1; yt++) {
                        //checks if there is not mine in neighbours, if there is no mine then click on them too.
                        if (xt != yt) {
                            click(x + xt, y + yt);
                        }
                    }

                }
            }
            if (getCellAt(x, y).isBomb()) {
                onGameLoss();


            }
        }
        checkEnd();


    }
    private boolean checkEnd() {
        int bombNotFound = BOMB_NUMBER;
        int notRevealed = WIDTH * HEIGHT;
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (getCellAt(x, y).isRevealed() || getCellAt(x, y).isFlagged()) {
                    notRevealed--;
                }
                if (getCellAt(x, y).isFlagged() && getCellAt(x, y).isBomb()) {
                    bombNotFound--;

                }
            }
        }
        if (bombNotFound == 0 && notRevealed == 0) {
            Toast.makeText(context, "Game Won!", Toast.LENGTH_LONG).show();

        }
        return false;
    }

    public void flag(int x, int y) {
        boolean isFlagged = getCellAt(x, y).isFlagged();
        getCellAt(x, y).setFlagged(!isFlagged);
        getCellAt(x, y).invalidate();
    }

    private void onGameLoss() {
        //handles when player loses the game
        Toast.makeText(context, "Game Lost", Toast.LENGTH_LONG).show();
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                getCellAt(x, y).setRevealed();

            }
        }

    }

}
