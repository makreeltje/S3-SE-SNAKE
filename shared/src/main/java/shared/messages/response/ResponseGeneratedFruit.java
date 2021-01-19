package shared.messages.response;

import shared.messages.BaseMessage;

import java.util.ArrayList;
import java.util.List;

public class ResponseGeneratedFruit implements BaseMessage {
    private List<Integer> row = new ArrayList<>();
    private List<Integer> column = new ArrayList<>();

    public void addRow(int row) {
        this.row.add(row);
    }

    public List<Integer> getRow() {
        return row;
    }

    public void setRow(List<Integer> row) {
        this.row = row;
    }

    public void addColumn(int column) {
        this.column.add(column);
    }

    public List<Integer> getColumn() {
        return column;
    }

    public void setColumn(List<Integer> column) {
        this.column = column;
    }
}
