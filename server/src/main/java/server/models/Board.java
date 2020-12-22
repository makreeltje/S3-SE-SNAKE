package server.models;

public class Board {

    private CellType[][] board;

    public Board(int rowCount, int columnCount) {
        board = new CellType[rowCount][columnCount];

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < columnCount; c++){
                this.board[r][c] = CellType.EMPTY;
            }
        }
    }

    public CellType getCellType(int row, int col) {
        return board[row][col];
    }

    public void setCellType(int row, int col, CellType cellType) {
        this.board[row][col] = cellType;
    }

    public CellType[][] getBoard() {
        return board;
    }

    public void setBoard(CellType[][] board) {
        this.board = board;
    }
}
