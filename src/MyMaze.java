// Names:
// x500s:

import java.util.Random;

public class MyMaze{
    Cell[][] maze;
    int startRow;
    int endRow;
    int rows;
    int cols;

    public MyMaze(int rows, int cols, int startRow, int endRow) {
        this.startRow = startRow;
        this.endRow = endRow;
        this.rows = rows;
        this.cols = cols;
        this.maze = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println(i + " " + j);
                this.maze[i][j] = new Cell();
            }
        }
    }


    /* TODO: Create a new maze using the algorithm found in the writeup. */
    public static MyMaze makeMaze(int rows, int cols, int startRow, int endRow) {
        // Makes empty maze
        MyMaze mymaze = new MyMaze(rows, cols, startRow, endRow);

        // Makes stacks
        Stack1Gen<String> directionStack = new Stack1Gen<String>();
        Stack1Gen<Cell> cellStack = new Stack1Gen<Cell>();
        cellStack.push(mymaze.maze[startRow][0]);

        // Initializes Random
        Random r = new Random();
        int randInt;

        // Loop variables
        Cell currentCell;
        int currentRow = startRow;
        int currentCol = 0;
        Cell topNeighbor;
        Cell bottomNeighbor;
        Cell leftNeighbor;
        Cell rightNeighbor;

        while (cellStack.isEmpty() != true) {
            
            randInt = r.nextInt(0,3);
            currentCell = cellStack.top();
            currentCell.setVisited(true);

            // Assigns the cell's neighbors using the current cell's location
            if (currentRow > 0) {
                topNeighbor = mymaze.maze[currentRow][currentCol];
            }

            if (currentCell.getRight() == false){}
            

            // check if any neighbors are unvisited

            // if any unvisited.. use random number to pick one
            
            // push that neighbor cell to stack

            // update current row and col

            // update lastdirection

            // end loop iteration
            
        }


        return null;
    }

    public void printMaze() {
        String finalString = "";
        int totalRows = this.rows * 2 + 1; // *2 to add spacer rows. +1 to add the bottom boundary row
        int trueRow; // the actual row of the maze the loop is currently on 
        int spacerCount = 0; // used to determine trueRow

        for (int i = 0; i < totalRows; i++) {
            trueRow = i - spacerCount;
            /* 0, even rows, and the last row will be spacer rows. 
            Odd rows will be rows of cells. 
            mod is used to determine:
            i % 2 == 0 -> spacer row
            i % 2 != 0 -> cell row
            */ 

            // Case 1: Spacer Row
            // i == 0 and i == totalRow are top and bottom boundaries
            if (i == 0 || i == totalRows || i % 2 == 0) {
                for (int j = 0; j < this.cols; j++) {
                    // Case 1.1: i != zero, so there is a row of cells above it.
                    if (i != 0 && i != totalRows) {
                        Cell cellAbove = this.maze[trueRow-1][j];

                        if (cellAbove.getBottom() == true) {
                            if (j == this.cols - 1) {
                                finalString += "|---|\n";
                            }
                            else {
                                finalString += "|---";
                            }
                        }
                        else {
                            if (j == this.cols - 1) {
                                finalString += "|   |\n";
                            }
                            else {
                                finalString += "|   ";
                            }
                        }
                    } 
                    // Case 1.2: i == zero, so this row is the top boundary
                    else {
                        if (j == this.cols - 1) {
                            finalString += "|---|\n";
                        }
                        else {
                            finalString += "|---";
                        }
                    }
                    // System.out.println("Spacer Count: " + spacerCount);
                }
                spacerCount++;
            }

            // Case 2: Cell Row
            String stringToAppend; 
            // Creates a separate string for each cell in the row and appends each to finalString
            if (i != 0 && i % 2 != 0) {
                for (int j = 0; j < this.cols; j++) {
                    // If first col, |. otherwise empty
                    if (j == 0) {
                        stringToAppend = "|";
                    } else {
                        stringToAppend = "";
                    }

                    // System.out.println("trueRow: " + trueRow);
                    // System.out.println("trueCol " + j);

                    Cell currentCell = this.maze[trueRow][j];

                    // Adds star if visited
                    if (currentCell.getVisited() == true) {
                        stringToAppend += " + ";
                    } else {
                        stringToAppend += "   ";
                    }

                    // Adds right wall if cell has the wall
                    if (currentCell.getRight() == true) {
                        stringToAppend += "|";
                    } else {
                        stringToAppend += " ";
                    }
                    
                    // If on the end of the row adds newline    
                    if (j == this.cols - 1) {
                        stringToAppend += "\n";
                    }

                    finalString += stringToAppend;
                }
            }
        }
        System.out.println(finalString);
    }

    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
    }

    public static void main(String[] args){
        /* Use scanner to get user input for maze level, then make and solve maze */

        MyMaze maze = new MyMaze(3, 5, 1, 3);
        maze.printMaze();
    }
}
