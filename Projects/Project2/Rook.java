public class Rook {
    private int row;
    private int col;
    private boolean isBlack;

    public Rook(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    
    public boolean isMoveLegal(Board board, int endRow, int endCol){
        //empty space horizontal or vertical
        if((board.verifyHorizontal(this.row, this.col, endRow, endCol) || board.verifyVertical(this.row, this.col, endRow, endCol)) && board.getPiece(endRow, endCol) == null){
            return true;
        }
        //space w/ opposing
        else if((board.verifyHorizontal(this.row, this.col, endRow, endCol) || board.verifyVertical(this.row, this.col, endRow, endCol)) && board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack){
            return true;
        }
        //space w/ friendly or not horizontal or vertical
        else{
            return false;
        }
    }
}
