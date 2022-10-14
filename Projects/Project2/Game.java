public class Game {
    public static void main(String[] args) {
        //Board gameBoard = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        Board gameBoard = new Board("8/pppppppp/8/8/8/1p111111/PPPPPPPP/8");
        System.out.println(gameBoard.toString());


       

        gameBoard.movePiece(6, 0, 5, 1);
        System.out.println(gameBoard.toString());

        //System.out.println(gameBoard.verifyHorizontal(2, 0, 2, 7)); // False
        // chcp 65001











































         // Upper left
        // System.out.println("--------------------upper left testing");
        // System.out.println(gameBoard.verifyDiagonal(3,3,2,2)); // true
        // System.out.println(gameBoard.verifyDiagonal(3,3,1,1)); // true
        // System.out.println(gameBoard.verifyDiagonal(3,3,0,0)); // false

        // // upper right (same outputs as UL)
        // System.out.println("--------------------upper right testing");
        // System.out.println(gameBoard.verifyDiagonal(3,3,2,4)); // true
        // System.out.println(gameBoard.verifyDiagonal(3,3,1,5)); // true
        // System.out.println(gameBoard.verifyDiagonal(3,3,0,6)); // false
        
        // // lower left
        // System.out.println("--------------------lower left testing");
        // System.out.println(gameBoard.verifyDiagonal(3,4,5,2)); // true
        // System.out.println(gameBoard.verifyDiagonal(3,4,6,1)); // true
        // System.out.println(gameBoard.verifyDiagonal(3,4,7,0)); // false

        // // lower right
        // System.out.println("--------------------lower right testing");
        // System.out.println(gameBoard.verifyDiagonal(4,1,5,2)); // true
        // System.out.println(gameBoard.verifyDiagonal(4,1,6,3)); // true
        // System.out.println(gameBoard.verifyDiagonal(4,1,7,4)); // false
    }
}
