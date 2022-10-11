import java.lang.Math;
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
     * @author Tyler
     */
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    /**
     * Updates a single cell of the board to a new piece.
     * @param row     The row of the board.
     * @param col    The column of the board.
     * @param piece The piece to be placed at [row][col]
     * @author Tyler
     */
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }





    // Game functionality methods:

    // cant verify this works until veritfy vertical is done then can test this with moving pawns
    //TODO: finish movePiece
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece movingPiece = this.getPiece(startRow, startCol);
        Piece pieceToReplace = this.getPiece(endRow, endCol);

        
        // Case 1: The move is legal. The piece is replaced.
        if (movingPiece.isMoveLegal(this, endRow, endCol)){
            this.setPiece(endRow, endCol, movingPiece);
            this.setPiece(startRow, startCol, null);
        }

        // Case 2: the move is illegal. Nothing happens.
        return false;
    }

     /**
     * Determines whether the game has been ended, i.e., if one player's King has been captured.
     * @return false if both kings remain. true if less than two kings remains.
     * @author Tyler
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
     * @author Tyler
     */
    public String toString() {
        String finalString = "Board: \n" + "   \uFF10 \uFF11 \uFF12 \uFF13 \uFF14 \uFF15 \uFF16 \uFF17 \n"; // Creates column labels
        for (int i = 0; i < board.length; i++){
            // Creates row labels
            switch (i) {
                case 0: finalString = finalString + "\uFF10" + "|"; break;
                case 1: finalString = finalString + "\uFF11" + "|"; break;
                case 2: finalString = finalString + "\uFF12" + "|"; break;
                case 3: finalString = finalString + "\uFF13" + "|"; break;
                case 4: finalString = finalString + "\uFF14" + "|"; break;
                case 5: finalString = finalString + "\uFF15" + "|"; break;
                case 6: finalString = finalString + "\uFF16" + "|"; break;
                case 7: finalString = finalString + "\uFF17" + "|"; break;
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
     * @author Tyler
     */
    public void clear() {
        for (int i = 0; i < board.length; i++){
            for (int j = 0 ; j < board.length; j++){
                board[i][j] = null;
            }
        }
    }





    // Movement helper functions

    //TODO: verifySoureandDesitnation
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - Both contain a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - Destination contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        return false;
    }

    /**
     * Checks whether the 'start' position and 'end' position are adjacent to each other.
     * @param startRow     The row of the start position.
     * @param startCol    The column of the start position.
     * @param endRow The row of the end position.
     * @param endCol The column of the end position.
     * @return true if the positions are adjacent. false if they are not.
     * @author Tyler
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
        return false;
    }

    /**
     * Checks whether a given 'start' and 'end' position are a valid horizontal move.
     * @param startRow     The row of the start position.
     * @param startCol    The column of the start position.
     * @param endRow The row of the end position.
     * @param endCol The column of the end position.
     * @return true if the horizontal move will move to open space or move onto a piece. false if attempting to move past a piece.
     * @author Tyler
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
     * @author Tyler
     */
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        int distance; 
        int testPosition;
        // Case 0: Not a vertical move.
        if (startCol != endCol) {
            return false; // Obviously not vertical.
        }

        // Case 1: If the move is going up
        if (startRow < endRow){
            distance = endRow - startRow; // checks only spaces in between
            //System.out.println("Distance: " + distance);
            // Iterates for the amount of distance wanted to move. Determines if a piece is in the way of the move
            for (int i = 0; i < distance; i++){
                testPosition = startRow + i + 1; // works from the startRow + 1 because otherwise would start testing on startCol
                //System.out.println("tstpos: " + testPosition);
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
            distance = startRow - endRow; // reversed distance calulation to keep the number positive
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

    //TODO: verifyDiagonal
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int rowDistance = Math.abs(startRow - endRow);
        int colDistance = Math.abs(startCol - endCol);
        int distance = rowDistance;
        int testRow = -1;
        int testCol = -1;
        System.out.println("start location: " + startRow +  " " + startCol);
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


            System.out.println("test location: " + testRow +  " " + testCol);
            if (board[testRow][testCol] != null){
                if (testRow == endRow && testCol == endCol){
                    System.out.println("c1 piece at locaiton:");
                    return true; // Case 1.1: There is a piece at the desired move location. Move is valid.
                }
                System.out.println("c1 Piece in way:");
                return false; // Case 1.2: There is a piece in the way. Move is invalid.
            }
        }
        System.out.println("c1 no piece in way:");
        return true; // Case 1.3: There is no piece in the way. Move is valid.
        

         // working version of case 1
        //   // Case 1: Diagonal towards upper left or upper right
        //   if (endRow < startRow && endCol < startCol || endRow < startRow && endCol > startCol){
        //     for (int i = 0; i < distance; i++){
        //         testRowAndCol = startRow - 1 - i;
        //         if (board[testRowAndCol][testRowAndCol] != null){
        //             if (testRowAndCol == endRow){
        //                 System.out.println("c1 piece at locaiton:");
        //                 return true; // Case 1.1: There is a piece at the desired move location. Move is valid.
        //             }
        //             System.out.println("c1 Piece in way:");
        //             return false; // Case 1.2: There is a piece in the way. Move is invalid.
        //         }
        //     }
        //     System.out.println("c1 no piece in way:");
        //     return true; // Case 1.3: There is no piece in the way. Move is valid.
        //  }
        // // Case 2: Diagonal towards lower left or lower right
        // else if (endRow < startRow && endCol > startCol){
        //     for (int i = 0; i < distance; i++){
        //         testRowAndCol = startRow + 1 + i;
        //         if (board[testRowAndCol][testRowAndCol] != null){
        //             if (testRowAndCol == endRow){
        //                 System.out.println("c2 piece at locaiton:");
        //                 return true; // Case 1.1: There is a piece at the desired move location. Move is valid.
        //             }
        //             System.out.println("c2 Piece in way:");
        //             return false; // Case 1.2: There is a piece in the way. Move is invalid.
        //         }
        //     }
        //     System.out.println("c2 no piece in way:");
        //     return true; // Case 1.3: There is no piece in the way. Move is valid.
        // }
        
        
        // else {
        //     System.out.println("system fail:");
        //     return false;
        // }

      
    }
}
