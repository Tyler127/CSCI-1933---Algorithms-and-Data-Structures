package Projects.Project2;

public class Knight {
    private int row;
    private int col;
    private boolean isBlack;

    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean horseLaw(int startRow, int startCol, int endRow, int endCol){//made to account for knight unique movement
        //8 possible spaces, with a 2 1 displacement
        //both "up" options, ROW is up(minus)/down(plus), COL is left(minus)/right(plus)
        if((startRow + 2 == endRow && startCol + 1 == endCol) || (startRow + 2 == endRow && startCol - 1 == endCol)){
            return true;
        }
        //both "down" options
        else if((startRow - 2 == endRow && startCol + 1 == endCol) || (startRow - 2 == endRow && startCol - 1 == endCol)){
            return true;
        }
        //Both "left" options
        else if((startRow + 1 == endRow && startCol - 2 == endCol) || (startRow - 1 == endRow && startCol - 2 == endCol)){
            return true;
        }
        //both "right" options
        else if((startRow + 1 == endRow && startCol + 2 == endCol) || (startRow - 1 == endRow && startCol + 2 == endCol)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol){
        //moving in L to empty space
        if(this.horseLaw(this.row, this.col, endRow, endCol) && board.getPiece(endRow, endCol) == null){
            return true;
        }
        //move in L with opposing piece
        else if(this.horseLaw(this.row, this.col, endRow, endCol) && board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack){
            return true;
        }
        //if attempted space is not in L or is occupied by friendly
        else{
            return false;
        }
    }
}
