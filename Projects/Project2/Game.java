import java.util.Scanner;
import java.util.Arrays;

public class Game {

    public static int[] coordsInterpreter(String str){
        String[] inputList = str.split(" ");
        int[] coordsList = new int[2];
        for(int j = 0; j < 2;j++){
            coordsList[j] = Integer.parseInt(inputList[j]);
        }
        //System.out.println(Integer.toString(coordsList[0]));
        //System.out.println(Integer.toString(coordsList[1]));
        return coordsList;
    }
    public static void main(String[] args) {
        //Board gameBoard = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        //Board gameBoardTesting = new Board("8/Pppppppp/8/11111111/11N11111/11111111/PPPPPPPP/8");
        //System.out.println(gameBoardTesting.toString());


       
        //tested empty destination, enemy in destination, friendly in destination, and invalid input
        //gameBoardTesting.movePiece(1, 0, 0, 0);
        //System.out.println(gameBoardTesting.toString());
        //pieces tested for isMoveLegal:Bishop, King, Queen, Rook, Knight

        // no piece at start
        //System.out.println(gameBoardTesting.verifySourceAndDestination(2, 0, 2, 7, true)); // False
        
        // black to empty
        //System.out.println(gameBoardTesting.verifySourceAndDestination(1, 0, 2, 7, true)); // True



        // ------------Game code below comment out to run test cases-----------------------
        // chcp 65001
        boolean gameOver = false;
        boolean isBlack = false;
        Scanner scanna = new Scanner(System.in);
        Board gameBoard = new Board("rnbqkbnr/pppppppp/11R11111/8/8/8/PPPPPPPP/RNBQKBNR");

        System.out.println("Welcome to Chess!");
        System.out.println("    When imputting a piece's coordinates enter them in the form of row(space)column like so: 5 4");

        System.out.println(gameBoard.toString());
            
        while (gameOver == false) {
            boolean validMove = false;
            boolean validInputs = false;
            String input = "";
            String input2 = "";

            if (isBlack) {
                System.out.println("Black's Turn:");
            } else {
                System.out.println("White's Turn:");
            }

            // Continues looping until a valid move is submitted
            while (validMove == false) {

                // Loops until a valid set of inputs are recieved
                 while (validInputs == false) {
                    System.out.println("    PIECE TO MOVE:");
                    input = scanna.nextLine();

                    System.out.println("    DESTINATION:");
                    input2 = scanna.nextLine();

                    if (input.length() == 3 && input2.length() == 3) {
                        if (Character.isDigit(input.charAt(0)) && Character.isDigit(input.charAt(2))) {
                            if (Character.isDigit(input2.charAt(0)) && Character.isDigit(input2.charAt(2))) {
                                if (Character.isWhitespace(input.charAt(1)) && Character.isWhitespace(input2.charAt(1))) {
                                    validInputs = true;
                                }
                            }
                        } 
                    } 

                    if (validInputs != true) {
                        System.out.println("Invalid input format! Try Again!");
                    }
                }

                // Turns inputs into arrays of integers
                int[] startCoords = coordsInterpreter(input);
                int[] endCoords = coordsInterpreter(input2);

                // Makes loop stop if valid move also will move piece and reprint the board
                if (gameBoard.verifySourceAndDestination(startCoords[0], startCoords[1], endCoords[0], endCoords[1], isBlack)) {
                    validMove = true;
                    gameBoard.movePiece(startCoords[0], startCoords[1], endCoords[0], endCoords[1]);
                    System.out.println(gameBoard.toString());
                } else {
                    System.out.println("Invalid move. Try inputting a different move!");
                }
            }

            // Will continue the loop if game is not over
            if (gameBoard.isGameOver()) {
                System.out.println("Congratulations! You won chess ez clap!!");
                scanna.close();
                gameOver = true;
            } else {
                if (isBlack) {
                    isBlack = false;
                } else {
                    isBlack = true;
                }
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
