package server.models;

public class Board {

    private int[][] grid;

    public Board(int rowCount, int columnCount) {
        grid = new int[rowCount][columnCount];
        for (int c = 0; c < columnCount; c++) {
            for (int r = 0; r < rowCount; r++){
                this.grid[r][c] = 0;
            }
        }
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void setCellValue(int row, int column, int value) {
        grid[row][column] = value;
    }

    public int getCellValue(int row, int column) {
        return grid[row][column];
    }

    /**
     * 0   = empty
     * 1-8 = players
     * 9   = fruit
     */
}
