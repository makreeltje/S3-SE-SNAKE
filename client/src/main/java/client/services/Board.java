package client.services;

public class Board {
    private int rowCount;
    private int colCount;

    private Cell[][] cells;

    public Board(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
    }

    public void generateFood() {

    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
}
