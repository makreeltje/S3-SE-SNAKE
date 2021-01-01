package server.models;

public class Board {

    private int[][] board;

    public Board(int rowCount, int columnCount) {
        board = new int[rowCount][columnCount];
        for (int c = 0; c < columnCount; c++) {
            for (int r = 0; r < rowCount; r++){
                this.board[r][c] = 0;
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setCellValue(int row, int column, int value) {
        board[row][column] = value;
    }

    public int getCellValue(int row, int column) {
        return board[row][column];
    }

    /**
     * 0   = empty
     * 1-8 = players
     * 9   = fruit
     */
}
