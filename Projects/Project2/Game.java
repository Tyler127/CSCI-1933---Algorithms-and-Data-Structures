public class Game {
    public static void main(String[] args) {
        //Board gameBoard = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        Board gameBoard = new Board("8/pppppppp/8/8/8/8/PPPPPPPP/8");
        System.out.println(gameBoard.toString());

        // // Down
        // System.out.println(gameBoard.verifyVertical(1,1,5,1)); // True
        // System.out.println(gameBoard.verifyVertical(1,1,6,1)); // True
        // System.out.println(gameBoard.verifyVertical(1,1,7,1)); // False
        // System.out.println(gameBoard.verifyVertical(2,1,7,1)); // False


        // // up:
        // System.out.println(gameBoard.verifyVertical(6,1,2,1)); // True
        // System.out.println(gameBoard.verifyVertical(6,1,1,1)); // True
        // System.out.println(gameBoard.verifyVertical(6,1,0,1)); // false

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
        System.out.println("--------------------lower right testing");
        System.out.println(gameBoard.verifyDiagonal(4,1,5,2)); // true
        System.out.println(gameBoard.verifyDiagonal(4,1,6,3)); // true
        System.out.println(gameBoard.verifyDiagonal(4,1,7,4)); // false

        //System.out.println(gameBoard.verifyHorizontal(2, 0, 2, 7)); // False
        // chcp 65001
    }
}
