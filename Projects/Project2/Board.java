import java.lang.Math;
import java.util.Scanner;
public class Board {

    // Instance variables
    private Piece[][] board = new Piece[8][8];

    // Constructor
    public Board(String setup) {
        Fen.load(setup, this); // sends this board obj to be populated by load
    }


    
    // Accessor Methods:

    /**
     * Returns the Piece object stored at a given row and column.
     * @param row     The row of the board.
     * @param col    The column of the board.
     * @return The Piece object stored at [row][col] or null
     */
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    /**
     * Updates a single cell of the board to a new piece.
     * @param row     The row of the board.
     * @param col    The column of the board.
     * @param piece The piece to be placed at [row][col]
     */
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }





    // Game functionality methods:

    /**
     * Moves a Piece object from one cell in the board to another, provided that the movement is legal.
     * @param startRow     The row of the start position.
     * @param startCol    The column of the start position.
     * @param endRow The row of the end position.
     * @param endCol The column of the end position.
     * @return true if the piece is successfully moved and false if the piece was not moved.
     */
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece movingPiece = this.getPiece(startRow, startCol);
        
        // Case 1: The move is legal. The piece is replaced.
        if (movingPiece.isMoveLegal(this, endRow, endCol)){
            this.setPiece(endRow, endCol, movingPiece);
            movingPiece.setPosition(endRow, endCol);
            this.setPiece(startRow, startCol, null);
            Scanner promoScanner = new Scanner(System.in);
            //if pawn is moving to back row of enemy will be changed to any piece but King, is PAWN PROMOTION
            if (movingPiece.getCharacter() == '\u265f' && endRow == 7){//Black Pawn, lowercase p
                System.out.println("Black, your Pawn is available for promotion, what do you choose?");
                System.out.println("Queen(Q or q), Knight(N or n), Bishop(B or b), or Rook(R or r)");
                String promoInput = promoScanner.nextLine();
                switch (promoInput){
                    case "Q":
                    case "q": 
                        movingPiece.setCharacter('\u265b');//black queen
                        break;

                    case "N":
                    case "n": 
                        movingPiece.setCharacter('\u265e');//black knight
                        break;

                    case "B":
                    case "b":
                        movingPiece.setCharacter('\u265d');//black bishop
                        break;

                    case "R":
                    case "r":
                        movingPiece.setCharacter('\u265c');//black rook
                        break;
                }
                //promoScanner.close();
                return true;
                //movingPiece.setCharacter('\u265b');//black queen
            }//white pawn, uppercase P
            else if(movingPiece.getCharacter() == '\u2659' && endRow == 0){
                System.out.println("White, your Pawn is available for promotion, what do you choose?");
                System.out.println("Queen(Q or q), Knight(N or n), Bishop(B or b), or Rook(R or r)");
                String promoInput = promoScanner.nextLine();
                switch (promoInput){
                    case "Q":
                    case "q": 
                        movingPiece.setCharacter('\u2655');//white queen
                        break;

                    case "N":
                    case "n": 
                        movingPiece.setCharacter('\u2658');//white knight
                        break;

                    case "B":
                    case "b":
                        movingPiece.setCharacter('\u2657');//white bishop
                        break;

                    case "R":
                    case "r":
                        movingPiece.setCharacter('\u2656');// rook
                        break;
                }
                //promoScanner.close();
                return true;
                //movingPiece.setCharacter('\u2655');//white queen
            }
            else{
                //promoScanner.close();
                return true;
            }
        }

        // Case 2: the move is illegal. Nothing happens.
        return false;
    }

     /**
     * Determines whether the game has been ended, i.e., if one player's King has been captured.
     * @return false if both kings remain. true if less than two kings remains.
     */
    public boolean isGameOver() {
        int kingCount = 0;
        char kingWhite = '\u265a';
        char kingBlack = '\u2654';
        // Searches the board and counts the amount of kings
        for (int i = 0; i < board.length; i++){
            for (int j = 0 ; j < board.length; j++){
                // Will error if trys to .getCharacter() a null.
                if (board[i][j] != null){
                    char currentPieceCharacter = board[i][j].getCharacter();
                    if (currentPieceCharacter == kingWhite || currentPieceCharacter == kingBlack){
                        kingCount+=1;
                    }
                }
            }
        }
        // Case 1: Less than two kings, someone has won
        if (kingCount < 2){
            return true;
        }
        // Case 2: There are two kings remaining
        return false;
    }

    /**
     * Creates a string of the board to display in the terminal.
     * @return The board string
     */
    public String toString() {
        String finalString = "   \uFF10 \uFF11 \uFF12 \uFF13 \uFF14 \uFF15 \uFF16 \uFF17 \n"; // Creates column labels
        for (int i = 0; i < board.length; i++){
            // Creates row labels 
            switch (i) {
                case 0: 
                    finalString = finalString + "\uFF10" + "|"; 
                    break;
                case 1: 
                    finalString = finalString + "\uFF11" + "|"; 
                    break;
                case 2: 
                    finalString = finalString + "\uFF12" + "|"; 
                    break;
                case 3: 
                    finalString = finalString + "\uFF13" + "|"; 
                    break;
                case 4: 
                    finalString = finalString + "\uFF14" + "|"; 
                    break;
                case 5: 
                    finalString = finalString + "\uFF15" + "|"; 
                    break;
                case 6: 
                    finalString = finalString + "\uFF16" + "|"; 
                    break;
                case 7: 
                    finalString = finalString + "\uFF17" + "|"; 
                    break;
            }

            // Creates chess piece icons and null space 
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null){
                    if (j == 7){
                        finalString = finalString + board[i][j].getCharacter() + " |\n";
                    } else {
                        finalString = finalString + board[i][j].getCharacter() + " |";
                    }
                } else {
                    if (j == 7){
                        finalString = finalString + "\u2001 |\n";
                    } else {
                        finalString = finalString + "\u2001" + " |";
                    }
                }
            }
        }
        return finalString;
    }

    /**
     * Sets every cell of the array to null. For debugging and grading purposes.
     */
    public void clear() {
        for (int i = 0; i < board.length; i++){
            for (int j = 0 ; j < board.length; j++){
                board[i][j] = null;
            }
        }
    }





    // Movement helper functions

     /**
     * Checks to ensure the following: 
     * 1. The given coordinates are within the boards boundaries.
     * 2. The start position contains a piece.
     * 3. Start piece color matches the isBlack parameter.
     * 4. The destination is either empty or a piece of opposite color.
     * @param startRow     The row of the start position.
     * @param startCol    The column of the start position.
     * @param endRow The row of the end position.
     * @param endCol The column of the end position.
     * @return true if all 4 conditions are true. false if any is not true.
     */
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        boolean inBounds= false;
        boolean startPieceValid = false;
        boolean destinationCheck = false;
        boolean startPieceNotNull = false;

        // true if piece is within the boards bounds
        if (startRow <= 7 && startRow >= 0 && endRow <= 7 && endRow >= 0) {
            inBounds = true;
        } 
        else {
            System.out.println("Error: Out of Bounds");
            return false;
        }

        // creates these piece holders here so invalid row and col inputs aren't used to make them
        Piece startPiece = this.getPiece(startRow, startCol);
        System.out.println(startPiece);
        Piece endPiece = this.getPiece(endRow, endCol);

        // true if start contains a piece and matches input color
        if (startPiece != null) {
            startPieceNotNull = true;
        }
        else {
            System.out.println("Error: Start Piece is Null");
            return false;
        }

        // true if current color matches the piece being moved
        if ((startPiece.getIsBlack() == true && isBlack == true) || (startPiece.getIsBlack() == false && isBlack == false)) {
            startPieceValid = true;
        }
        else {
            System.out.println("Error: Invalid Color");
            return false;
        }

        // true if the other location is empty or the opposite color
        if (endPiece == null || endPiece.getIsBlack() != startPiece.getIsBlack()) {
            destinationCheck = true;
        }
        else {
            System.out.println("Error: End Location Invalid");
            return false;
        }

        // returns true if none of the false cases trigger
        return true;
    }

    /**
     * Checks whether the 'start' position and 'end' position are adjacent to each other.
     * @param startRow     The row of the start position.
     * @param startCol    The column of the start position.
     * @param endRow The row of the end position.
     * @param endCol The column of the end position.
     * @return true if the positions are adjacent. false if they are not.
     */
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        // Case 1: Move Right
        if (startRow == endRow && startCol + 1 == endCol){
            return true;
        }
        // Case 2: Move Left
        if (startRow == endRow && startCol - 1 == endCol){
            return true;
        }
        // Case 3: Move Up
        if (startRow + 1 == endRow && startCol == endCol){
            return true;
        }
        // Case 4: Move Down
        if (startRow - 1 == endRow && startCol == endCol){
            return true;
        }
        //Case 5: diag up right
        if(startRow - 1 == endRow && startCol - 1 == endCol){
            return true;
        }
        //Case 6: diag up left
        if(startRow - 1 == endRow && startCol + 1 == endCol){
            return true;
        }
        //Case 7: diag down right
        if(startRow + 1 == endRow && startCol - 1 == endCol){
            return true;
        }
        //Case 8: diag down left
        if(startRow + 1 == endRow && startCol + 1 == endCol){
            return true;
        }
        return false;
    }

    /**
     * Checks whether a given 'start' and 'end' position are a valid horizontal move.
     * @param startRow     The row of the start position.
     * @param startCol    The column of the start position.
     * @param endRow The row of the end position.
     * @param endCol The column of the end position.
     * @return true if the horizontal move will move to open space or move onto a piece. false if attempting to move past a piece.
     */
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        int distance; 
        int testPosition;
        // Case 0: Not a horizontal move.
        if (startRow != endRow) {
            return false; // Obviously not horizontal.
        }

        // Case 1: If the move is going to the right
        if (startCol < endCol){
            distance = endCol - startCol; // checks only spaces in between
            //System.out.println("Distance: " + distance);
            // Iterates for the amount of distance wanted to move. Determines if a piece is in the way of the move
            for (int i = 0; i < distance; i++){
                testPosition = startCol + i + 1; // works from the startCol + 1 because otherwise would start testing on startCol
                //System.out.println("tstpos: " + testPosition);
                if (board[startRow][testPosition] != null){
                    if (testPosition == endCol){
                        return true; // Case 1.1: There is a piece at the desired movement location, move is valid
                    }
                    return false; // Case 1.2: There is a piece in the way and the move is invalid
                }
            }
            return true; // Case 1.3: There is no piece in the way, move must be valid
        } 
        // Case 2: If the move is going to the left
        else {
            distance = startCol - endCol; // reversed distance calulation to keep the number positive
            for (int i = 0; i < distance; i++){
                testPosition = startCol - i - 1; // works from the startCol - 1 instead
                if (board[startRow][testPosition] != null){
                    if (testPosition == endCol){
                        return true; // Case 2.1: There is a piece at the desired movement location, move is valid
                    }
                    return false; // Case 2.2: There is a piece in the way and the move is invalid
                }
            }
            return true; // Case 2.3: There is no piece in the way, move must be valid
        }
    }

     /**
     * Checks whether a given 'start' and 'end' position are a valid vertical move.
     * @param startRow     The row of the start position.
     * @param startCol    The column of the start position.
     * @param endRow The row of the end position.
     * @param endCol The column of the end position.
     * @return true if the vertical move will move to open space or move onto a piece. false if attempting to move past a piece.
     */
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        int distance; 
        int testPosition;
        // Case 0: Not a vertical move.
        if (startCol != endCol) {
            return false; 
        }

        // Case 1: If the move is going up
        if (startRow < endRow){
            distance = endRow - startRow; // checks only spaces in between
            // Iterates for the amount of distance wanted to move. Determines if a piece is in the way of the move
            for (int i = 0; i < distance; i++){
                testPosition = startRow + i + 1; // works from the startRow + 1 because otherwise would start testing on startCol
                if (board[testPosition][startCol] != null){
                    if (testPosition == endRow){
                        return true; // Case 1.1: There is a piece at the desired movement location, move is valid
                    }
                    return false; // Case 1.2: There is a piece in the way and the move is invalid
                }
            }
            return true; // Case 1.3: There is no piece in the way, move must be valid
        } 
        // Case 2: If the move is going down
        else {
            distance = startRow - endRow; 
            for (int i = 0; i < distance; i++){
                testPosition = startRow - i - 1; // works from the startRow - 1 instead
                if (board[testPosition][startCol] != null){
                    if (testPosition == endRow){
                        return true; // Case 2.1: There is a piece at the desired movement location, move is valid
                    }
                    return false; // Case 2.2: There is a piece in the way and the move is invalid
                }
            }
            return true; // Case 2.3: There is no piece in the way, move must be valid
        }
    }

    /**
     * Checks whether a given 'start' and 'end' position are a valid diagonal move.
     * @param startRow     The row of the start position.
     * @param startCol    The column of the start position.
     * @param endRow The row of the end position.
     * @param endCol The column of the end position.
     * @return true if the diagonal move will move to open space or move onto a piece. false if attempting to move past a piece.
     */
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int rowDistance = Math.abs(startRow - endRow);
        int colDistance = Math.abs(startCol - endCol);
        int distance = rowDistance;
        int testRow = -1;
        int testCol = -1;

        // In order to be a diagonal move, row and col distance must be equivalent
        if (rowDistance != colDistance){
            return false; // Case 0: Not a diagonal move.
        }

        // Case 1: Is on a diagonal.
        for (int i = 0; i < distance; i++){
            // For when diagonal is going up
            if (endRow < startRow){
                testRow = startRow - 1 - i; 
            } 
            // For when diagonal is going down
            if (endRow > startRow){
                testRow = startRow + 1 + i;
            } 

            // Diagonal going left
            if (endCol < startCol){
                testCol = startCol - 1 - i;
            } 

            // Diagonal going right
            if (endCol > startCol) {
                testCol = startCol + 1 + i;
            }

            if (board[testRow][testCol] != null){
                if (testRow == endRow && testCol == endCol){
                    return true; // Case 1.1: There is a piece at the desired move location. Move is valid.
                }
                return false; // Case 1.2: There is a piece in the way. Move is invalid.
            }
        }
        return true; // Case 1.3: There is no piece in the way. Move is valid.
    }
}
