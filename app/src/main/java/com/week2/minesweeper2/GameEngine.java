package com.week2.minesweeper2;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import com.week2.minesweeper2.util.Generator;
//import com.week2.minesweeper2.util.PrintGrid;
import com.week2.minesweeper2.util.PrintGrid;
import com.week2.minesweeper2.views.grid.Cell;
import com.week2.minesweeper2.views.grid.Grid;


import org.w3c.dom.Text;

public class GameEngine  {

    private static GameEngine instance;
    public void setNumMines(int BOMB_NUMBER) {
        this.BOMB_NUMBER = BOMB_NUMBER;
    }
    public int BOMB_NUMBER;
    public static int WIDTH = 6;
    public static int HEIGHT = 6;

    public static boolean isGameOver = false;


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
        isGameOver = false;

        //creating the grid and storing it
        int[][] generatedGrid = Generator.generate(BOMB_NUMBER, WIDTH, HEIGHT);

        PrintGrid.print(generatedGrid, WIDTH, HEIGHT);
        setGrid(context, generatedGrid);

    }

    private void setGrid(Context context, int[][] grid) {
        //gives each cell unique positions and invalidates it.

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
        //sends to the view
    }

    public Cell getCellAt(int x, int y) {
        return MineSweeperGrid[x][y];
        //gets actual position of the cell. takes it from grid.
    }


    //onClick event. it checks if the button is pressed or not, if its clicked then you cannot press it again
    public void click(int x, int y) {
        //click event. checks if the click is on the grid and not clicked.
        if (!isGameOver) {
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


    }
    private boolean checkEnd() {
        //checks is the game reach the end or not. calculates the bombs
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
        if ((bombNotFound == 0 && notRevealed == 0 )||BOMB_NUMBER==notRevealed) {
            Toast.makeText(context, "Game Won!", Toast.LENGTH_LONG).show();
            isGameOver=true;


        }
        return false;
    }

    public void flag(int x, int y) {
        //checks the cell if its flagged or not
        boolean isFlagged = getCellAt(x, y).isFlagged();
        getCellAt(x, y).setFlagged(!isFlagged);
        getCellAt(x, y).invalidate();
    }

    private void onGameLoss() {
        //when the player steps on the bomb. game ends.
        Toast.makeText(context, "Game Lost", Toast.LENGTH_LONG).show();
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                getCellAt(x, y).setRevealed();
                isGameOver = true;
            }
        }

    }

}
