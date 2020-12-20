package client.models;

import lombok.Data;

@Data
public class Cell {
    private int row;
    private int column;
    private CellType cellType;

    public Cell(int row, int column, CellType cellType) {
        this.row = row;
        this.column = column;
        this.cellType = cellType;
    }
}
