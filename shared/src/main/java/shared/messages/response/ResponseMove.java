package shared.messages.response;

import shared.messages.BaseMessage;
import shared.messages.CellType;

public class ResponseMove implements BaseMessage {
    private int row;
    private int column;
    private boolean ateFruit;

    private CellType cellType;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isAteFruit() {
        return ateFruit;
    }

    public void setAteFruit(boolean ateFruit) {
        this.ateFruit = ateFruit;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public CellType setIndexCellType(int index) {
        return CellType.values()[index];
    }
}
