package server.models;

public class Board {

    private Cell cell;

    public Board(int rowCount, int columnCount) {
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < columnCount; c++){
                setCell(new Cell(r, c, CellType.EMPTY));
            }
        }
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
