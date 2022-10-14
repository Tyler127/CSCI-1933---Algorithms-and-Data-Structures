import java.util.Arrays;

public class Matrix {
    private int nrows;
    private int ncols;
    private int[][] matrix;

    public Matrix(int initNrows, int initNcols){
        this.nrows = initNrows;
        this.ncols = initNcols;
        this.matrix = new int[this.nrows][this.ncols];
    }

    public Matrix(int[][] arr){
        this.matrix = arr;
        this.nrows = arr.length;
        this.ncols = arr[0].length;
    }

    /**
     * basic to string for the whole matrix
     * each row is printed as a newline
     */
    public String toString(){
        String toPrint = "";
        for(int i = 0; i < this.matrix.length; i++){
            toPrint = toPrint + Arrays.toString(this.matrix[i]) + "\n";
        }
        return toPrint;
    }

    /**
     * makes a matrix with reversed rows and columns
     * @return new matrix with inverted rows and columns
     */
    public Matrix transpose(){
        Matrix newMatrix = new Matrix(ncols, nrows);
        for(int i = 0; i < this.matrix.length; i++){
            for(int j = 0; j < this.matrix[i].length; j++){
                newMatrix.matrix[j][i] = this.matrix[i][j];
            }
        }
        return newMatrix;
    }

    public static void main(String[] args){
        Matrix x = new Matrix(5, 2);
        System.out.println(x);
       Matrix y = x.transpose();
       System.out.println(y);
    }
}
