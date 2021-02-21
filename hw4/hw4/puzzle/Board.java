package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class Board implements WorldState {

    private static final int BLANK = 0;
    private final int[][] tiles;
    private final int size;

    /**
     * Constructs a board from an N-by-N array of tiles where
     * tiles[i][j] = tile at row i, column j.
     */
    public Board(int[][] tiles) {
        this.size = tiles.length;
        this.tiles = copy2DArray(tiles);
    }

    private int[][] copy2DArray(int[][] srcArray) {
        int[][] destArray = new int[srcArray.length][];
        for (int i = 0; i < srcArray.length; i += 1) {
            destArray[i] = new int[srcArray[i].length];
            System.arraycopy(srcArray[i], 0 , destArray[i], 0, srcArray[i].length);
        }
        return destArray;
    }


    /**
     * Returns value of tile at row i, column j (or 0 if blank).
     */
    public int tileAt(int i, int j) {
        if (!validPosition(i, j)) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return tiles[i][j];
    }

    /**
     * Check if the given position of the tile is valid.
     */
    private boolean validPosition(int i, int j) {

        if (i < size && i >= 0 && j < size && j >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the board size N.
     */
    public int size() {
        return size;
    }

    /**
     * Returns the neighbors of the current board.
     * @author SPOILERZ (cited from http://joshh.ug/neighbors.html)
     */
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    private int[][] createGoal(){
        int count = 0;
        int[][] goal = new int[size][size];
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                goal[i][j] = count;
                count += 1;
            }
            goal[size - 1][size - 1] = BLANK;
        }
        return goal;
    }

    /**
     *  Hamming estimate: The number of tiles in the wrong position.
     */
    public int hamming() {
        int distance = 0;
        int[][]goal = createGoal();
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                if (i == j && i == size - 1) {
                    break;
                }
                if (tileAt(i, j) != goal[i][j]) {
                    distance += 1;
                }
            }
        }
        return distance;
    }

    /**
     * Manhattan estimate: The sum of the Manhattan distances (sum of the vertical and horizontal distance)
     * from the tiles to their goal positions.
     */
    public int manhattan() {
        int estimatedDistance = 0;
        for (int r = 0; r < size; r += 1) {
            for (int c = 0; c < size; c += 1) {
                if (tileAt(r,c) == BLANK) {
                    continue;
                }
                int expectedR = (tileAt(r,c) - 1) / size;
                int expectedC = (tileAt(r,c) - 1) % size;
                estimatedDistance += Math.abs((expectedR - r));
                estimatedDistance += Math.abs((expectedC - c));
            }
        }
        return estimatedDistance;
    }

    /**
     * Estimated distance to goal. This method should
     * simply return the results of manhattan() when submitted to
     * Gradescope.
     */
    public int estimatedDistanceToGoal() {
        return manhattan();
        // return hamming();
    }

    /**
     * Returns true if this board's tile values are the same
     * position as y's.
     */
    public boolean equals(Object y) {

        Board board1 = (Board) y;
        int[][] tiles1 = board1.tiles;

        if (Arrays.deepEquals(tiles, tiles1)) {
            return true;
        } else {
            return false;
        }
    }

    /** Returns the string representation of the board.
     * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
