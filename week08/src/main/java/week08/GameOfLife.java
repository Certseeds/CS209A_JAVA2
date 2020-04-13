/* CS209A_JAVA2/week08
   Copyright (C) 2020  nanoseeds

   CS209A_JAVA2/week08 is free software: you can redistribute it and/or modify
   it under the terms of the GNU Affero General Public License as
   published by the Free Software Foundation, either version 3 of the
   License, or (at your option) any later version.

   CS209A_JAVA2/week08 is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU Affero General Public License for more details.

   You should have received a copy of the GNU Affero General Public License
   along with this program.  If not, see <https://www.gnu.org/licenses/>.
   */
/**
 * @Github: https://github.com/Certseeds/CS209A_JAVA2/week08
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-04-06 19:10:49
 * @LastEditors : nanoseeds
 */
package week08;

import java.util.Scanner;

// A simple Java program to implement Game of Life
public class GameOfLife {

    static int[][] blockPattern = {
            {0, 0, 0, 0},
            {0, 1, 1, 0}
    };
    static int[][] beePattern = {
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {0, 1, 1, 0}
    };
    static int[][] blinkerPattern = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
    };
    static int[][] gliderPattern = {
            {0, 0, 1},
            {1, 0, 1},
            {0, 1, 1}
    };
    static int[][] grid;
    static int generation = 0;
    static int M = 10;
    static int N = 10;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Displaying the original grid
//        grid = new int[M][N];
//        System.out.println("Original Generation");
//        displayGrid(grid, M, N);
//        //generate the nextGeneration and display
//        nextGeneration(grid, M, N);
        M = Integer.parseInt(args[1]);
        N = Integer.parseInt(args[2]);
        if (M < 3 || N < 5) {
            System.out.println("size of part too small");
        }
        System.out.println("choose pattern in \n 1. blockPattern:\n2. beePattern:\n3. blinkerPattern:\n4. gliderPattern :\n");
        int[][] pattern;
        int next = input.nextInt();
        String uesless = input.nextLine();
        switch (next) {
            case 1: {
                pattern = blockPattern;
                break;
            }
            case 2: {
                pattern = beePattern;
                break;
            }
            case 3: {
                pattern = blinkerPattern;
                break;
            }
            case 4: {
                pattern = gliderPattern;
                break;
            }
            default: {
                System.out.println("Error, input should between 1-4");
                return;
            }
        }
        init(pattern, M, N);
    }

    public static void init(int[][] pattern, int M, int N) {
        if (0 == pattern.length ||
                0 == pattern[0].length ||
                M <= 0 || N <= 0) {
            return;
        }
        grid = new int[M][N];
        int left = (M - pattern.length) / 2;
        int down = (N - pattern[0].length) / 2;
        for (int i = 0; i < pattern.length; i++) {
            System.arraycopy(pattern[i], 0, grid[left + i], down, pattern[0].length);
        }
        int generation = 0;
        displayGrid(grid, M, N);
        while (input.hasNext()) {
            System.out.printf("Generation: %d\n", generation);
            generation++;
            grid = nextGeneration(grid, M, N);
            displayGrid(grid, M, N);
            //generate the nextGeneration and display
            String input_str = input.nextLine();
        }
    }

    static void displayGrid(int[][] grid, int M, int N) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0) {
                    System.out.print(".");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Function to print next generation 
    static int[][] nextGeneration(int[][] grid, int M, int N) {
        int[][] future = new int[M][N];

        // Loop through every cell 
        for (int l = 1; l < M - 1; l++) {
            for (int m = 1; m < N - 1; m++) {
                // finding no Of Neighbours that are alive 
                int aliveNeighbours = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        aliveNeighbours += grid[l + i][m + j];
                    }
                }
                // The cell needs to be subtracted from 
                // its neighbours as it was counted before 
                aliveNeighbours -= grid[l][m];

                // Implementing the Rules of Life 
                // Cell is lonely and dies
                if ((grid[l][m] == 1) && (aliveNeighbours < 2)) {
                    future[l][m] = 0;
                }
                // Cell dies due to over population
                else if ((grid[l][m] == 1) && (aliveNeighbours > 3)) {
                    future[l][m] = 0;
                }
                // A new cell is born
                else if ((grid[l][m] == 0) && (aliveNeighbours == 3)) {
                    future[l][m] = 1;
                }
                // Remains the same
                else {
                    future[l][m] = grid[l][m];
                }
            }
        }
        return future;
    }
} 