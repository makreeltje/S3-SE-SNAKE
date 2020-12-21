package shared.messages.response;

import lombok.Data;
import shared.messages.BaseMessage;
import shared.messages.CellType;

public class ResponseMove extends BaseMessage {
    private int row;
    private int column;
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

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public CellType setIndexCellType(int index) {
        return cellType.values()[index];
    }
}
