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

    public static boolean dfs(char[][] grid,int[][] usedCoords, int row, int col){
        return false;
    }

    public static void main(String[] args){
        char[][] grid1 = new char[][]{ 
                    {'v', 'p', 'p', 'p', 'x'}, 
                    {'x', 'x', 'x', 'p', 'x'}, 
                    {'x', 'x', 'p', 'p', 'x'}, 
                    {'x', 'x', 'p', 'x', 'x'}, 
                    {'x', 'x', 'p', 'p', 'v'} };
        boolean result = bfs(grid1, 0, 0);
        System.out.println(result);
    }
}
