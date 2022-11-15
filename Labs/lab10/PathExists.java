import java.util.*;

public class PathExists {
    public static void bfs(char[][] grid, int row, int col){
        //only move if equal to path character
        char[][] tracker = grid;//swap p to c when already checked, do not change grid
        int[] startCoords = new int[]{row, col};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(startCoords);

        while(queue.isEmpty() != true){
            int[] currCoords = queue.poll();
            tracker[currCoords[0]][currCoords[1]] = 'c';
        }
    }
}
