package com.week2.minesweeper2.util;

import androidx.annotation.NonNull;

import java.util.Random;

public class Generator {

    public static int[][] generate(int bombNumber,  final int width, final int height) {
        //random placing the bombs.
        Random r = new Random();
        int[][] grid1 = new int[width][height];
        for (int x= 0; x < width; x++) {
            grid1[x] = new int[height];

        }

        while (bombNumber > 0) {
            int x = r.nextInt(width);
            int y = r.nextInt(height);
            //Checks the mines, if there is no mine; place mine.
            if (grid1[x][y] != -1) {
                grid1[x][y] = -1;
                bombNumber--;
            }

        }
        grid1=calculateNeighbours(grid1,width,height);

        return grid1;
    }

    private static int[][] calculateNeighbours(int[][] grid, final int width, final int height) {
        for (int x = 0; x < width; x++) {
            //calculates that how many bombs are there in close neighbours
            for (int y = 0; y < height; y++) {
                grid[x][y] = getNeighbourNumber(grid, x, y, width, height);
            }
        }
        return grid;
    }

    private static int getNeighbourNumber(final int[][] grid, final int x, final int y, final int width, final int height) {
      if (grid[x][y] == -1) {
            return -1;
        }
            int count = 0;
            //at this point, we are counting the empty places around that how many mines around it.
            if (isMineAt(grid, x - 1, y + 1, width, height))//top-left
                count++;
            if (isMineAt(grid, x, y + 1, width, height))//top
                count++;
            if (isMineAt(grid, x + 1, y + 1, width, height))//top-right
                count++;
            if (isMineAt(grid, x - 1, y, width, height))//left
                count++;
            if (isMineAt(grid, x + 1, y, width, height))//right
                count++;
            if (isMineAt(grid, x, y - 1, width, height))//bottom
                count++;
            if (isMineAt(grid, x - 1, y - 1, width, height))//bottom-left
                count++;
            if (isMineAt(grid, x + 1, y - 1, width, height))//bottom-right
                count++;
            return count;

    }

    private static boolean isMineAt(final int[][] grid, final int x, final int y, final int width, final int height) {
        if (x >= 0 && y >= 0 && x < width && y < height) {
            if (grid[x][y] == -1) {
                //checking is the mine  at the spot
                return true;
            }
        }
        return false;
    }
}
