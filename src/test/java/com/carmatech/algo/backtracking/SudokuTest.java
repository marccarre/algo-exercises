package com.carmatech.algo.backtracking;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SudokuTest {

    private final int[][] grid = new int[][] {
            { 0, 0, 0,   2, 6, 0,   7, 0, 1 },
            { 6, 8, 0,   0, 7, 0,   0, 9, 0 },
            { 1, 9, 0,   0, 0, 4,   5, 0, 0 },

            { 8, 2, 0,   1, 0, 0,   0, 4, 0 },
            { 0, 0, 4,   6, 0, 2,   9, 0, 0 },
            { 0, 5, 0,   0, 0, 3,   0, 2, 8 },

            { 0, 0, 9,   3, 0, 0,   0, 7, 4 },
            { 0, 4, 0,   0, 5, 0,   0, 3, 6 },
            { 7, 0, 3,   0, 1, 8,   0, 0, 0 }
    };

    private final Sudoku sudoku = new Sudoku(grid);

    @Test
    public void createSudokuGrid() {
        assertThat(sudoku.numEmptyCells(), is(45));
        assertThat(sudoku.isSolutionFound(), is(false));
    }

    @Test
    public void sudokuShouldPrintNicely() {
        assertThat(sudoku.toString(), is(
                "+---+---+---+\n" +
                "|000|260|701|\n" +
                "|680|070|090|\n" +
                "|190|004|500|\n" +
                "+---+---+---+\n" +
                "|820|100|040|\n" +
                "|004|602|900|\n" +
                "|050|003|028|\n" +
                "+---+---+---+\n" +
                "|009|300|074|\n" +
                "|040|050|036|\n" +
                "|703|018|000|\n" +
                "+---+---+---+\n"));
    }

    @Test
    public void invalidMoveInSquareShouldBacktrack() {
        assertThat(sudoku.setIfValid(9, 0, 0), is(false));
        assertThat(sudoku.numEmptyCells(), is(45));
        assertThat(sudoku.grid(), is(grid));
    }

    @Test
    public void invalidMoveInRowShouldBacktrack() {
        assertThat(sudoku.setIfValid(2, 0, 0), is(false));
        assertThat(sudoku.numEmptyCells(), is(45));
        assertThat(sudoku.grid(), is(grid));
    }

    @Test
    public void invalidMoveInColumnShouldBacktrack() {
        assertThat(sudoku.setIfValid(5, 0, 1), is(false));
        assertThat(sudoku.numEmptyCells(), is(45));
        assertThat(sudoku.grid(), is(grid));
    }

    @Test
    public void validMoveShouldStay() {
        assertThat(sudoku.setIfValid(3, 0, 0), is(true));
        grid[0][0] = 3;
        assertThat(sudoku.grid(), is(grid));
        assertThat(sudoku.numEmptyCells(), is(44));
    }

    @Test
    public void validMoveForLastRemainingEmptyCellShouldGiveSolution() {
        int[][] grid = new int[][] {
                { 0, 3, 4,   6, 7, 8,   9, 1, 2 },
                { 6, 7, 2,   1, 9, 5,   3, 4, 8 },
                { 1, 9, 8,   3, 4, 2,   5, 6, 7 },

                { 8, 5, 9, 7, 6, 1,   4, 2, 3 },
                { 4, 2, 6,   8, 5, 3,   7, 9, 1 },
                { 7, 1, 3,   9, 2, 4,   8, 5, 6 },

                { 9, 6, 1,   5, 3, 7,   2, 8, 4 },
                { 2, 8, 7,   4, 1, 9,   6, 3, 5 },
                { 3, 4, 5,   2, 8, 6,   1, 7, 9 }
        };
        Sudoku sudoku = new Sudoku(grid);
        assertThat(sudoku.numEmptyCells(), is(1));
        assertThat(sudoku.isSolutionFound(), is(false));

        assertThat(sudoku.setIfValid(5, 0, 0), is(true));
        grid[0][0] = 5;
        assertThat(sudoku.grid(), is(grid));
        assertThat(sudoku.numEmptyCells(), is(0));
        assertThat(sudoku.isSolutionFound(), is(true));
    }

    @Test
    public void resolveMediumDifficultySudoku() {
        sudoku.resolve();
        assertThat(sudoku.isSolutionFound(), is(true));
        assertThat(sudoku.grid(), is(new int[][] {
                { 4, 3, 5,   2, 6, 9,   7, 8, 1 },
                { 6, 8, 2,   5, 7, 1,   4, 9, 3 },
                { 1, 9, 7,   8, 3, 4,   5, 6, 2 },

                { 8, 2, 6,   1, 9, 5,   3, 4, 7 },
                { 3, 7, 4,   6, 8, 2,   9, 1, 5 },
                { 9, 5, 1,   7, 4, 3,   6, 2, 8 },

                { 5, 1, 9,   3, 2, 6,   8, 7, 4 },
                { 2, 4, 8,   9, 5, 7,   1, 3, 6 },
                { 7, 6, 3,   4, 1, 8,   2, 5, 9 }
        }));
    }

    @Test
    public void resolveHardDifficultySudoku() {
        int[][] grid = new int[][] {
                { 0, 0, 0,   0, 0, 0,   0, 1, 2 },
                { 0, 0, 0,   0, 3, 5,   0, 0, 0 },
                { 0, 0, 0,   6, 0, 0,   0, 7, 0 },

                { 7, 0, 0,   0, 0, 0,   3, 0, 0 },
                { 0, 0, 0,   4, 0, 0,   8, 0, 0 },
                { 1, 0, 0,   0, 0, 0,   0, 0, 0 },

                { 0, 0, 0,   1, 2, 0,   0, 0, 0 },
                { 0, 8, 0,   0, 0, 0,   0, 4, 0 },
                { 0, 5, 0,   0, 0, 0,   6, 0, 0 }
        };
        Sudoku sudoku = new Sudoku(grid);
        sudoku.resolve();

        assertThat(sudoku.isSolutionFound(), is(true));
        assertThat(sudoku.grid(), is(new int[][] {
                { 6, 7, 3,   8, 9, 4,   5, 1, 2 },
                { 9, 1, 2,   7, 3, 5,   4, 8, 6 },
                { 8, 4, 5,   6, 1, 2,   9, 7, 3 },

                { 7, 9, 8,   2, 6, 1,   3, 5, 4 },
                { 5, 2, 6,   4, 7, 3,   8, 9, 1 },
                { 1, 3, 4,   5, 8, 9,   2, 6, 7 },

                { 4, 6, 9,   1, 2, 8,   7, 3, 5 },
                { 2, 8, 7,   3, 5, 6,   1, 4, 9 },
                { 3, 5, 1,   9, 4, 7,   6, 2, 8 }
        }));
    }
}
