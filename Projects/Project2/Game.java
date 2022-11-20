

import java.util.Scanner;

public class Game {

    public static int[] coordsInterpreter(String str){
        String[] inputList = str.split(" ");
        int[] coordsList = new int[2];
        for(int j = 0; j < 2;j++){
            coordsList[j] = Integer.parseInt(inputList[j]);
        }
        return coordsList;
    }
    public static void main(String[] args) {
        // chcp 65001
        boolean gameOver = false;
        boolean isBlack = false;
        Scanner scanna = new Scanner(System.in);
        Board gameBoard = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");

        System.out.println("Welcome to Chess!");
        System.out.println("    When imputting a piece's coordinates enter them in the form of row(space)column like so: 5 4");

        System.out.println(gameBoard.toString());

        int[] startCoords = null;
        int[] endCoords = null;
            
        while (gameOver == false) {
            boolean validMove = false;
            boolean validInputs = false;
            String input = "";
            String input2 = "";
            startCoords = null;
            endCoords = null;

            if (isBlack) {
                System.out.println("Black's Turn:");
            } else {
                System.out.println("White's Turn:");
            }

            // Continues looping until a valid move is submitted
            while (validMove == false) {
                validInputs = false;
                // Loops until a valid set of inputs are recieved
                 while (validInputs == false) {
                    System.out.println("    PIECE TO MOVE:");
                    input = scanna.nextLine();

                    System.out.println("    DESTINATION:");
                    input2 = scanna.nextLine();

                    // Outside Source #1 - Ensures that input is exactly digit space digit and length 3
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
                startCoords = coordsInterpreter(input);
                endCoords = coordsInterpreter(input2);

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
                System.out.println("Congratulations! You win chess!");
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
    }
}
