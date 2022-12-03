// Names:
// x500s:

import java.util.Random;
import java.util.Scanner;

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
                //System.out.println(i + " " + j);
                this.maze[i][j] = new Cell();
            }
        }
    }

    // TODO: make this have no inputs
    public static MyMaze makeMaze(int rows, int cols, int startRow, int endRow) {
        // Initialize empty maze
        MyMaze mymaze = new MyMaze(rows, cols, startRow, endRow);
        mymaze.maze[startRow-1][0].setVisited(true); // set start cell to visited

        // Initialize stack
        Stack1Gen<int[]> indexStack = new Stack1Gen<int[]>();
        int[] startLocation = new int[2];
        startLocation[0] = startRow - 1;
        startLocation[1] = 0;
        indexStack.push(startLocation);

        int iterations=0;

        // Loop until there is no more possible moves
        while (indexStack.isEmpty() != true) {
            iterations++;
            int[] currentIndex = indexStack.top();
            int currentRow = currentIndex[0];
            int currentCol = currentIndex[1];
            Cell currentCell = mymaze.maze[currentRow][currentCol];

            //System.out.println("currentRow: " + currentRow);
            //System.out.println("currentCol: " + currentCol);

            String nextMove = determineMove(rows - 1, cols - 1, currentRow, currentCol, mymaze);
            //System.out.println(nextMove);

            Cell nextCell;
            int[] nextCellIndex = new int[2];
            switch (nextMove) {
                // No possible move (dead end)
                case "N":
                    indexStack.pop();
                    break;

                // Move up
                case "T":
                    nextCell = mymaze.maze[currentRow - 1][currentCol];
                    nextCell.setVisited(true);
                    nextCell.setBottom(false);
                    nextCellIndex[0] = currentRow - 1;
                    nextCellIndex[1] = currentCol;
                    indexStack.push(nextCellIndex);
                    break;

                // Move right
                case "R":
                    nextCell = mymaze.maze[currentRow][currentCol + 1];
                    nextCell.setVisited(true);
                    currentCell.setRight(false);
                    nextCellIndex[0] = currentRow;
                    nextCellIndex[1] = currentCol + 1;
                    indexStack.push(nextCellIndex);
                    break;

                // Move Down
                case "B":
                    nextCell = mymaze.maze[currentRow + 1][currentCol];
                    nextCell.setVisited(true);
                    currentCell.setBottom(false);
                    nextCellIndex[0] = currentRow + 1;
                    nextCellIndex[1] = currentCol;
                    indexStack.push(nextCellIndex);
                    break;

                // Move left
                case "L":
                    nextCell = mymaze.maze[currentRow][currentCol - 1];
                    nextCell.setVisited(true);
                    nextCell.setRight(false);
                    nextCellIndex[0] = currentRow;
                    nextCellIndex[1] = currentCol - 1;
                    indexStack.push(nextCellIndex);
                    break;
            }
        }

        // Set every cell to unvisited
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mymaze.maze[i][j].setVisited(false);
            }
        }
        
        mymaze.maze[endRow-1][cols-1].setRight(false); // remove right border on exit cell
        mymaze.printMaze();
        System.out.println(iterations);
        return mymaze;
    }

    private static String determineMove(int rows, int cols, int currentRow, int currentCol, MyMaze mymaze) {
        // Cells
        Cell topNeighbor = null;
        Cell bottomNeighbor = null;
        Cell leftNeighbor = null;
        Cell rightNeighbor = null;

        /*
           Neighbor cells start as null
           Then updated to their location if in bounds
           Then if cell visited -> neighbor = null; 
        */ 

        // System.out.println("currentRow: " + currentRow);
        // System.out.println("Rows: " + rows);
        // System.out.println("currentCol: " + currentCol);

        // Assigns the cell's neighbors using the current cell's location
        // Top 
        if (currentRow > 0) {
            topNeighbor = mymaze.maze[currentRow-1][currentCol];
            if (topNeighbor.getVisited() == true) {
                topNeighbor = null;
            }
            //topNeighbor.setVisited(true); // test code remove this
        }
        //System.out.println("Top Neighbor: " + topNeighbor);
        
        // Bottom
        if (currentRow < rows) {
            bottomNeighbor = mymaze.maze[currentRow+1][currentCol];
            if (bottomNeighbor.getVisited() == true) {
                bottomNeighbor = null;
            }
            //bottomNeighbor.setVisited(true); // test code remove this
        }
        //System.out.println("Bottom Neighbor: " + bottomNeighbor);

        // Left
        if (currentCol > 0) {
            leftNeighbor = mymaze.maze[currentRow][currentCol-1];
            if (leftNeighbor.getVisited() == true) {
                leftNeighbor = null;
            }
            //leftNeighbor.setVisited(true); // test code remove this
        }
        //System.out.println("Left Neighbor: " + leftNeighbor);

        // Right
        if (currentCol < cols) {
            rightNeighbor = mymaze.maze[currentRow][currentCol+1];
            if (rightNeighbor.getVisited() == true) {
                rightNeighbor = null;
            }
            //rightNeighbor.setVisited(true); // test code remove this
        }
        //System.out.println("Right Neighbor: " + rightNeighbor);

        // Case 1: Returns N if no valid directional moves
        if (topNeighbor == null && bottomNeighbor == null && 
        leftNeighbor == null && rightNeighbor == null) {
            return "N";
        } 
        // Case 2: Chooses random ints until one is chosen that matches a direction that exists
        else {
            // Initializes Random
            Random r = new Random();
            int rInt;

            while (true) {
                rInt = r.nextInt(0,4); // 0-3
                //System.out.println(randInt);
                // Returns a 
                if (rInt == 0 && topNeighbor != null) {
                    //System.out.println("T");
                    return "T";
                }
                if (rInt == 1 && rightNeighbor != null) {
                    //System.out.println("R");
                    return "R";
                }
                if (rInt == 2 && bottomNeighbor != null) {
                    //System.out.println("B");
                    return "B";
                }
                if (rInt == 3 && leftNeighbor != null) {
                    //System.out.println("L");
                    return "L";
                }
            }
        }
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
            if (i != 0 && i % 2 != 0) {
                String stringToAppend;  // Creates a separate string for each cell.

                for (int j = 0; j < this.cols; j++) {
                    // Case 2.1: on start row and col. -> adds start opening
                    if (trueRow == this.startRow-1 && j == 0) {
                        stringToAppend = " ";
                    } 
                    // Case 2.2: on any other col -> adds left boundary
                    else {
                        if (j == 0) {
                            stringToAppend = "|";
                        } else {
                            stringToAppend = "";
                        }
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
    public void solveMaze() {//FINDS VIABLE CELLS INCONSISTENTLY, UNSURE WHY. TRY SAVING MORE VARIABLES OT REDUCE TPEING ERRORS< REFORMAT IF NEEDED.
        //initialize a queue with the start inded(startRow, 0)
        Q1Gen<int[]> queue = new Q1Gen<int[]>();
        int[] coordSaver = new int[]{startRow - 1, 0};
        queue.add(coordSaver);
        //loop through until queue is empty
        while(queue.length() != 0){
            //dequeue the front index of the queue and mark the visited attribute as true
                //becomes current working cell
            System.out.println();
            int[] workingCoords = queue.remove();
            System.out.println("new working cell");
            maze[workingCoords[0]][workingCoords[1]].setVisited(true);
            System.out.println("visited at: " + workingCoords + " set to true");
            //if current cell is the finish point, break loop, maze has been solved
                //print something like "maze solved"
            System.out.println("checking if maze finished");
            if(workingCoords[0] == endRow && workingCoords[1] == cols - 1){
                System.out.println("maze finished!");
                break;
            }
            // //queue all reachable neighbors
            //     //check barriers in cell above, cell to the left, if viable add those cells to queue
            if(workingCoords[0] != 0){
                System.out.println("row not 0, checking cell above");
                if(maze[workingCoords[0] - 1][workingCoords[1]].getBottom() == false && maze[workingCoords[0] - 1][workingCoords[1]].getVisited() == false){//checks bottom of cell above and if visited
                    System.out.println("up cell viable");
                    coordSaver[1] = workingCoords[1];
                    coordSaver[0] = coordSaver[0] - 1;
                    queue.add(coordSaver);
                }
            }
            if(workingCoords[1] != 0){
                System.out.println("col not 0, checking cell to left");
                if(maze[workingCoords[0]][workingCoords[1] - 1].getRight() == false && maze[workingCoords[0]][workingCoords[1] - 1].getVisited() == false){//checks right of cell left and if visited
                    System.out.println("left cell viable");
                    coordSaver[0] = workingCoords[0];
                    coordSaver[1] = coordSaver[1] - 1;
                    queue.add(coordSaver);
                }
            }
            //     //check right and bottom barriers of current working cell. if viable add right/ below cell to queue
            System.out.println("checking cell below");
            if(maze[workingCoords[0]][workingCoords[1]].getBottom() == false && workingCoords[0] + 1 != maze.length && maze[workingCoords[0] + 1][workingCoords[1]].getVisited() == false){//check bottom barrier, and if would be out of bounds
                System.out.println("bottom cell viable");
                coordSaver[1] = workingCoords[1];
                coordSaver[0] = coordSaver[0] + 1;
                queue.add(coordSaver);
            }
            System.out.println("checking cell to right");
            if(maze[workingCoords[0]][workingCoords[1]].getRight() == false && workingCoords[1] + 1 != maze[workingCoords[0]].length && maze[workingCoords[0]][workingCoords[1] + 1].getVisited() == false){//check bottom barrier, and if would be out of bounds
                System.out.println("right cell viable");
                coordSaver[0] = workingCoords[0];
                coordSaver[1] = coordSaver[1] + 1;
                queue.add(coordSaver);
            }
        }
        //at end of funtion, call printMaze
        printMaze();
    }

    public static void main(String[] args){
        /* Use scanner to get user input for maze level, then make and solve maze */

        //MyMaze maze = new MyMaze(3, 5, 1, 3);
        //maze.printMaze();
        MyMaze maze = makeMaze(10, 10, 1, 5);
        maze.solveMaze();
    }
}