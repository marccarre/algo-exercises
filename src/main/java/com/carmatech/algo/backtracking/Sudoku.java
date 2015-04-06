package com.carmatech.algo.backtracking;

import com.google.common.collect.Sets;

import java.util.Set;

public class Sudoku {
    private static final Set<Integer> VALID_VALUES = Sets.newHashSet(1, 2, 3, 4, 5, 6, 7, 8, 9);
    private static final int SQUARE_SIZE = 3;
    private static final int GRID_SIZE = SQUARE_SIZE * SQUARE_SIZE;

    private final int[][] grid;
    private int numEmptyCells;

    public Sudoku(final int[][] grid) {
        this.grid = validate(grid).clone(); // safe copy.
        this.numEmptyCells = countEmptyCells();
    }

    private int[][] validate(final int[][] grid) {
        if (grid == null)
            throw new NullPointerException();
        if ((grid.length != GRID_SIZE) || (grid[0].length != GRID_SIZE))
            throw new IllegalArgumentException(String.format("Grid is not a %dx%d matrix but a %dx%d matrix.", GRID_SIZE, GRID_SIZE, grid.length, grid[0].length));
        return grid;
    }

    private int countEmptyCells() {
        int count = 0;
        for (int i = 0; i < GRID_SIZE; ++i)
            for (int j = 0; j < GRID_SIZE; ++j)
                if (grid[i][j] == 0)
                    ++count;
        return count;
    }

    public int numEmptyCells() {
        return numEmptyCells;
    }

    public int[][] grid() {
        return grid.clone(); // safe copy.
    }

    public boolean setIfValid(final int value, final int x, final int y) {
        validateValue(value);
        validateCoordinate(x);
        final int previousValue = grid[x][y];

        grid[x][y] = value;
        if (isValid(x, y)) {
            if (previousValue == 0)
                --numEmptyCells;
            return true;
        }

        grid[x][y] = previousValue;
        return false;
    }

    private int validateValue(final int value) {
        if (!VALID_VALUES.contains(value))
            throw new IllegalArgumentException("Value must be in " + VALID_VALUES + " but was " + value);
        return value;
    }

    private int validateCoordinate(final int coordinate) {
        if (!VALID_VALUES.contains(coordinate + 1))
            throw new IllegalArgumentException("Coordinate must be an integer between 0 and 8 included, but was " + coordinate);
        return coordinate;
    }

    private boolean isValid(final int x, final int y) {
        return isValidRow(x) &&  isValidColumn(y) && isValidSquare(x, y);
    }

    private boolean isValidRow(final int x) {
        final Set<Integer> values = Sets.newHashSet();
        for (int y = 0; y < GRID_SIZE; ++y) {
            final int value = grid[x][y];
            if (values.contains(value))
                return false;
            else if (value != 0)
                values.add(value);
        }
        return true;
    }

    private boolean isValidColumn(final int y) {
        final Set<Integer> values = Sets.newHashSet();
        for (int x = 0; x < GRID_SIZE; ++x) {
            final int value = grid[x][y];
            if (values.contains(value))
                return false;
            else if (value != 0)
                values.add(value);
        }
        return true;
    }

    private boolean isValidSquare(final int x, final int y) {
        final int row = x / SQUARE_SIZE;
        final int column = y / SQUARE_SIZE;

        final Set<Integer> values = Sets.newHashSet();
        for (int i = row * SQUARE_SIZE; i < (row + 1) * SQUARE_SIZE; ++i) {
            for (int j = column * SQUARE_SIZE; j < (column + 1) * SQUARE_SIZE; ++j) {
                final int value = grid[i][j];
                if (values.contains(value))
                    return false;
                else if (value != 0)
                    values.add(value);
            }
        }
        return true;
    }

    public boolean isSolutionFound() {
        if (numEmptyCells != 0)
            return false;

        for (int x = 0; x < GRID_SIZE; ++x)
            if (!isValidRow(x))
                return false;

        for (int y = 0; y < GRID_SIZE; ++y)
            if (!isValidColumn(y))
                return false;

        for (int x = 0; x < SQUARE_SIZE; x += 3)
            for (int y = 0; y < SQUARE_SIZE; y += 3)
                if (!isValidSquare(x, y))
                    return false;

        return true;
    }

    /**
     * Resolve using a classic backtracking DFS algorithm.
     */
    public void resolve() {
        resolveUsingBacktrackingDFS(0);
    }

    private boolean resolveUsingBacktrackingDFS(final int depth) {
        if (isSolutionFound())
            return true;

        // Heuristic to speed-up backtracking DFS, otherwise, there may be up to 6,670,903,752,021,072,936,960 different solutions (for an empty grid):
        int[] bestNextFreeCell = findFreeCellWithMinimumDegreeOfFreedom();
        int x = bestNextFreeCell[X];
        int y = bestNextFreeCell[Y];

        for (final int value : VALID_VALUES) {
            // Try move:
            int previousValue = grid[x][y];
            if (setIfValid(value, x, y)) {
                if (resolveUsingBacktrackingDFS(depth + 1))
                    return true; // Propagate solution so that we finish earlier.
                else {
                    // If eventually failed, undo move:
                    grid[x][y] = previousValue;
                    if (previousValue == 0)
                        ++numEmptyCells;
                }
            }
        }

        return false;
    }

    private int[] findFreeCellWithMinimumDegreeOfFreedom() {
        int min = 9;

        int[] row = findRowWithMinFreeCell(min);
        min = row[MIN]; // Update min in order to speed up search, in case we found a better value.

        int[] column = findColumnWithMinFreeCell(min);
        min = column[MIN]; // Update min in order to speed up search, in case we found a better value.

        int[] square = findSquareWithMinFreeCell(min);

        return (row[MIN] < column[MIN])
                ? (row[MIN] < square[MIN])
                        ? new int[] { row[X], row[Y] }
                        : new int[] { square[X], square[Y] }
                : (column[MIN] < square[MIN])
                        ? new int[] { column[X], column[Y] }
                        : new int[] { square[X], square[Y] };
    }

    private static final int X = 0;
    private static final int Y = 1;
    private static final int MIN = 2;

    private int[] findRowWithMinFreeCell(int min) {
        int xMin = -1;
        int yMin = -1;

        for (int x = 0; x < GRID_SIZE; ++x) {

            // Count zeros in row 'x':
            int y;
            int numZeroes = 0;
            for (y = 0; y < GRID_SIZE; ++y) {
                if (grid[x][y] == 0)
                    ++numZeroes;
                if (numZeroes >= min) // Stop counting earlier.
                    break;
            }

            if ((numZeroes > 0) && (numZeroes <= min)) {
                min = numZeroes;
                xMin = x;
                yMin = y;
            }
        }

        return new int[] { xMin, yMin, min };
    }

    private int[] findColumnWithMinFreeCell(int min) {
        int xMin = -1;
        int yMin = -1;

        for (int y = 0; y < GRID_SIZE; ++y) {

            // Count zeros in column 'y':
            int x;
            int numZeroes = 0;
            for (x = 0; x < GRID_SIZE; ++x) {
                if (grid[x][y] == 0)
                    ++numZeroes;
                if (numZeroes >= min) // Stop counting earlier.
                    break;
            }

            if ((numZeroes > 0) && (numZeroes <= min)) {
                min = numZeroes;
                xMin = x;
                yMin = y;
            }
        }

        return new int[] { xMin, yMin, min };
    }

    private int[] findSquareWithMinFreeCell(int min) {
        int xMin = -1;
        int yMin = -1;

        for (int row = 0; row < SQUARE_SIZE; ++row) {
            for (int column = 0; column < SQUARE_SIZE; ++column) {

                int latestX = -1;
                int latestY = -1;
                int numZeroes = 0;
                boolean aboveMin = false;

                for (int x = row * SQUARE_SIZE; x < (row + 1) * SQUARE_SIZE; ++x) {
                    for (int y = column * SQUARE_SIZE; y < (column + 1) * SQUARE_SIZE; ++y) {
                        if (grid[x][y] == 0) {
                            latestX = x;
                            latestY = y;
                            ++numZeroes;
                        } if (numZeroes >= min) { // Stop counting earlier.
                            aboveMin = true;
                            break;
                        }
                    }
                    if (aboveMin)
                        break;
                }

                if ((numZeroes > 0) && (numZeroes <= min)) {
                    min = numZeroes;
                    xMin = latestX;
                    yMin = latestY;
                }
            }
        }

        return new int[] { xMin, yMin, min };
    }

    public String toString() {
        final StringBuilder string = new StringBuilder();
        for (int x = 0; x < GRID_SIZE; ++x) {
            if (x % 3 == 0)
                appendRowSeparator(string);

            for (int y = 0; y < GRID_SIZE; ++y) {
                if (y % 3 == 0)
                    string.append('|');

                string.append(grid[x][y]);

                if (y == GRID_SIZE - 1) {
                    string.append('|');
                    string.append('\n');
                }
            }
        }
        appendRowSeparator(string);
        return string.toString();
    }

    private void appendRowSeparator(final StringBuilder string) {
        for (int y = 0; y < GRID_SIZE; ++y) {
            if (y % 3 == 0)
                string.append('+');
            string.append('-');
            if (y == GRID_SIZE - 1)
                string.append('+');
        }
        string.append('\n');
    }
}
