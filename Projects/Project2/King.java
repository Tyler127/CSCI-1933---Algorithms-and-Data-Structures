public class King {
    private int row;
    private int col;
    private boolean isBlack;

    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        //moving adjacent into empty space
        if(board.verifyAdjacent(this.row, this.col, endRow, endCol) && board.getPiece(endRow, endCol) == null){
            return true;
        }
        //move adjacent into space w/ opposing piece
        else if(board.verifyAdjacent(this.row, this.col, endRow, endCol) && board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack){
            return true;
        }
        //if attempted space is not adjacent or is occupied by friendly
        else{
            return false;
        }
    }
}
