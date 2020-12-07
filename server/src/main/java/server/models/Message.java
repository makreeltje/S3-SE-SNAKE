package server.models;

import shared.messages.MessageOperationType;

public class Message {
    private MessageOperationType operation;
    private String property;
    private String username;
    private String password;
    private boolean singlePlayer;
    private int rowCount;
    private int columnCount;

    public MessageOperationType getOperation() {
        return operation;
    }

    public void setOperation(MessageOperationType operation) {
        this.operation = operation;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSinglePlayer() {
        return singlePlayer;
    }

    public void setSinglePlayer(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }

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
