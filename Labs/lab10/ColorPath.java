package lab10;

import java.util.*;

public class ColorPath {
    
    public static int[][] colorPath(int[][] image, int sourceRow, int sourceCol, int newColor){
        //bfs test
        bfs(image, sourceRow, sourceCol, newColor);

        // //dfs test
        //int startColor = image[sourceRow][sourceCol];
        //dfs(image, sourceRow, sourceCol, startColor, newColor);
        
        return image;
    }

    public static void bfs(int[][] arr, int row, int col, int newColor){
        int startColor = arr[row][col];
        int[] startCoords = new int[]{row, col};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(startCoords);

        while(queue.isEmpty() != true){
            int[] currCoords = queue.poll();
            arr[currCoords[0]][currCoords[1]] = newColor;
            if(currCoords[1] - 1 >= 0 && arr[currCoords[0]][currCoords[1] - 1] == startColor){
                int[] toAdd = new int[]{currCoords[0], currCoords[1] - 1};
                queue.add(toAdd);
            }
            if(currCoords[1] + 1 < arr[currCoords[0]].length && arr[currCoords[0]][currCoords[1] + 1] == startColor){
                int[] toAdd = new int[]{currCoords[0], currCoords[1] + 1};
                queue.add(toAdd);
            }//length of overall arr
            if(currCoords[0] + 1 < arr.length && arr[currCoords[0] + 1][currCoords[1]] == startColor){
                int[] toAdd = new int[]{currCoords[0] + 1, currCoords[1]};
                queue.add(toAdd);
            }
            if(currCoords[0] - 1 >= 0 && arr[currCoords[0] - 1][currCoords[1]] == startColor){
                int[] toAdd = new int[]{currCoords[0] - 1, currCoords[1]};
                queue.add(toAdd);
            }
        }    
    }

    public static void dfs(int[][] arr, int row, int col, int startColor, int newColor){
        if(row >= arr.length || row < 0 || col < 0 || col >= arr[row].length || arr[row][col] != startColor){
        }
        else{
            arr[row][col] = newColor;
            dfs(arr, row + 1, col, startColor, newColor);
            if ( row != 0){dfs(arr, row - 1, col, startColor, newColor);}
            dfs(arr, row, col + 1, startColor, newColor);
            if(col != 0){dfs(arr, row, col - 1, startColor, newColor);}
        }
    }
}
