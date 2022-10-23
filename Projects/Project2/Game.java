import java.util.Scanner;

public class Game {

    public static int[] coordsInterpreter(String str){
        String[] inputList = str.split(" ");
        int[] coordsList = new int[2];
        for(int j = 0; j < 2;j++){
            coordsList[j] = Integer.parseInt(inputList[j]);
        }
        System.out.println(Integer.toString(coordsList[0]));
        System.out.println(Integer.toString(coordsList[1]));
        return coordsList;
    }
    public static void main(String[] args) {
        //Board gameBoard = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        Board gameBoardTesting = new Board("8/pppppppp/8/8/8/1p111111/PPPPPPPP/8");
        System.out.println(gameBoardTesting.toString());


       

        // gameBoardTesting.movePiece(6, 0, 5, 1);
        // System.out.println(gameBoardTesting.toString());

        // no piece at start
        System.out.println(gameBoardTesting.verifySourceAndDestination(2, 0, 2, 7, true)); // False
        
        // black to empty
        System.out.println(gameBoardTesting.verifySourceAndDestination(1, 0, 2, 7, true)); // True



        // ------------Game code below comment out to run test cases-----------------------
        // chcp 65001
        boolean gameOver = false;
        Board gameBoard = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        Scanner scanna = new Scanner(System.in);
        boolean isBlack = false;

        System.out.println("Welcome to Chess!");
        System.out.println("    When imputting a piece's coordinates enter them in the form of row(space)column like so: 5 4");
            
        while (gameOver == false) {
            boolean validMove = false;

            if (isBlack) {
                System.out.println("Black's Turn:");
            } else {
                System.out.println("White's Turn:");
            }

            // Continues looping until a valid input is submitted
            while (validMove == false) {
                System.out.println("    Input the coordinates of the piece you want to move.");
                String input = scanna.nextLine();

                System.out.println("    Input the coordinates of the piece you want to move.");
                String input2 = scanna.nextLine();

                // TODO: parse string for inputs to verify S and D
                int[] startCoords = coordsInterpreter(input);
                int[] endCoords = coordsInterpreter(input2);

                gameBoard.verifySourceAndDestination(startCoords[0], startCoords[1], endCoords[0], endCoords[1], gameBoard.getPiece(startCoords[0], startCoords[1]).getIsBlack());

                // Will make loop stop if valid move
                if (gameBoard.verifySourceAndDestination(0, 0, 0, 0, isBlack)) {
                    validMove = true;
                } else {
                    System.out.println("Invalid move. Try inputting a different move!");
                }
            }

            // Will continue the loop if game is not over
            if (gameBoard.isGameOver()) {
                System.out.println("Congratulations! You won chess ez clap!!");
                gameOver = true;
            }


        }








































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
