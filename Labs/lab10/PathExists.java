import java.util.*;

public class PathExists {
    public static boolean bfs(char[][] grid, int row, int col){
        //only move if equal to path character
        char[][] tracker = grid;//swap p to c when already checked, do not change grid
        int[] startCoords = new int[]{row, col};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(startCoords);

        while(queue.isEmpty() != true){
            int[] currCoords = queue.poll();
            if(grid[currCoords[0]][currCoords[1]] != 'v') {
                tracker[currCoords[0]][currCoords[1]] = 'c';}
            if(currCoords[1] - 1 >= 0 && tracker[currCoords[0]][currCoords[1] - 1] == 'p'){//left
                int[] toAdd = new int[]{currCoords[0], currCoords[1] - 1};
                queue.add(toAdd);
            }
            if(currCoords[1] + 1 < grid[currCoords[0]].length && tracker[currCoords[0]][currCoords[1] + 1] == 'p'){//right
                int[] toAdd = new int[]{currCoords[0], currCoords[1] + 1};
                queue.add(toAdd);
            }//length of overall arr
            if(currCoords[0] + 1 < grid.length && tracker[currCoords[0] + 1][currCoords[1]] == 'p'){//down
                int[] toAdd = new int[]{currCoords[0] + 1, currCoords[1]};
                queue.add(toAdd);
            }
            if(currCoords[0] - 1 >= 0 && tracker[currCoords[0] - 1][currCoords[1]] == 'p'){//up
                int[] toAdd = new int[]{currCoords[0] - 1, currCoords[1]};
                queue.add(toAdd);
            }

            //check if v
            if(currCoords[1] - 1 >= 0 && tracker[currCoords[0]][currCoords[1] - 1] == 'v' && currCoords != startCoords){//left
                return true;
            }
            if(currCoords[1] + 1 < grid[currCoords[0]].length && tracker[currCoords[0]][currCoords[1] + 1] == 'v' && currCoords != startCoords){//right
                return true;
            }//length of overall arr
            if(currCoords[0] + 1 < grid.length && tracker[currCoords[0] + 1][currCoords[1]] == 'v' && currCoords != startCoords){//down
                return true;
            }
            if(currCoords[0] - 1 >= 0 && tracker[currCoords[0] - 1][currCoords[1]] == 'v' && currCoords != startCoords){//up
                return true;
            }
        }
        return false;
    }

    public static void dfs(char[][] grid,char[][] tracker, int startRow, int startCol, int row, int col){
        //cases:
            //if char is v, but not start
            //if char is not p
        if(row >= grid.length || row < 0 || col < 0 || col >= tracker[row].length || tracker[row][col] == 'x' || tracker[row][col] == 'c'){}
        else{
            if(grid[row][col] == 'v' && row != startRow && col != startCol){
                tracker[0][0] = 'W';
            }else{
            grid[row][col] = 'c';
            dfs(grid, tracker, 0, 0, row + 1, col);//down
            dfs(grid, tracker, 0, 0, row - 1, col);//up
            dfs(grid, tracker, 0, 0, row, col + 1);//right
            dfs(grid, tracker, 0, 0, row, col - 1);//left
            }
        }
    }

    public static void main(String[] args){
        char[][] grid1 = new char[][]{ 
                    {'v', 'p'}, 
                    {'x', 'v' } };
        char[][] tracker = grid1;
        int[] startCoords = new int[]{0, 0};
        dfs(grid1, tracker, 0, 0, 0, 0);
        if(tracker[0][0] == 'W'){
            System.out.println("victory royale");
        }else{
            System.out.println("fart smella");
        }
    }
}
