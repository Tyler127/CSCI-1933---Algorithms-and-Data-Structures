public class Bishop {
    private int row;
    private int col;
    private boolean isBlack;

    public Bishop(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        //needed cases: diagforright diagforleft, diagbackright, diagbackleft

        //move diag into empty space
        if(board.verifyDiagonal(this.row, this.col, endRow, endCol) && board.getPiece(endRow, endCol) == null){
            return true;
        }//move diag into space w/ opposing piece, checking if piece at end is not null, and verifying its an enemy
        else if(board.verifyDiagonal(this.row, this.col, endRow, endCol) && board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack){
            return true;
        }else{//if attempted space is not diagonal, or occupying piece is ally
            return false;
        }
    }
}
