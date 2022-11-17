

public class Queen {
    private int row;
    private int col;
    private boolean isBlack;

    public Queen(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        //All directions
        if((board.verifyHorizontal(this.row, this.col, endRow, endCol) || board.verifyVertical(this.row, this.col, endRow, endCol) || board.verifyDiagonal(this.row, this.col, endRow, endCol)) && board.getPiece(endRow, endCol) == null){
            return true;
        }
        // all directions but enemy in end space
        else if((board.verifyHorizontal(this.row, this.col, endRow, endCol) || board.verifyVertical(this.row, this.col, endRow, endCol) || board.verifyDiagonal(this.row, this.col, endRow, endCol)) && board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack){
            return true;
        }
        else{
            return false;
        }
    }
}
