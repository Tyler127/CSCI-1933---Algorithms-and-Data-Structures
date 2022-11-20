// Names:
// x500s:

import java.util.Random;

public class MyMaze{
    Cell[][] maze;
    int startRow;
    int endRow;

    public MyMaze(int rows, int cols, int startRow, int endRow) {
        this.startRow = startRow;
        this.endRow = endRow;
        maze = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println(i + " " + j);
                maze[i][j] = new Cell();
            }
        }
        System.out.println(maze);
    }

    /* TODO: Create a new maze using the algorithm found in the writeup. */
    public static MyMaze makeMaze(int rows, int cols, int startRow, int endRow) {

        return null;
    }

    /* TODO: Print a representation of the maze to the terminal */
    public void printMaze() {
    }

    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
    }

    public static void main(String[] args){
        /* Use scanner to get user input for maze level, then make and solve maze */
    }
}
