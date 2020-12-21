package shared.messages.request;

import lombok.Data;
import shared.messages.BaseMessage;

public class RequestBoard extends BaseMessage {
    private int rowCount;
    private int columnCount;

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }
}
