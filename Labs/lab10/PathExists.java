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

    public static boolean dfs(char[][] grid,char[][] tracker, int[] startCoords, int row, int col){
        //cases:
            //if char is v, but not start
            //if char is not p
        if(grid[row][col] == 'v' && row != startCoords[0] && col != startCoords[1]){
            return true;
        }
        if(row >= grid.length || row < 0 || col < 0 || col >= tracker[row].length || tracker[row][col] == 'x' || tracker[row][col] == 'c'){}
        else{
            grid[row][col] = 'c';
            dfs(grid, tracker, startCoords, row + 1, col);
            if ( row != 0){dfs(grid, tracker, startCoords, row - 1, col);}
            dfs(grid, tracker, startCoords, row, col + 1);
            if(col != 0){dfs(grid, tracker, startCoords, row, col - 1);}
        }
        return false;
    }

    public static void main(String[] args){
        char[][] grid1 = new char[][]{ 
                    {'v', 'p', 'p', 'p', 'x'}, 
                    {'x', 'x', 'x', 'p', 'x'}, 
                    {'x', 'x', 'p', 'p', 'x'}, 
                    {'x', 'x', 'p', 'x', 'x'}, 
                    {'x', 'x', 'p', 'p', 'v'} };
        char[][] tracker = grid1;
        int[] startCoords = new int[]{0, 0};
        boolean result = dfs(grid1, tracker, startCoords, 0, 0);
        System.out.println(result);
    }
}
