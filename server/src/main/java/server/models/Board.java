package server.models;

public class Board {

    private int row;
    private int column;
    private Cell[][] cell;

    public Board(int row, int column) {
        cell = new Cell[row][column];
    }

    public Cell[][] getCell() {
        return cell;
    }

    public void setCell(Cell[][] cell) {
        this.cell = cell;
    }
}
