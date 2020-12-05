package server.models;

import shared.SnakeWebSocketMessageOperation;

public class Message {
    private SnakeWebSocketMessageOperation operation;
    private String property;
    private String username;
    private String password;
    private boolean singlePlayer;

    public SnakeWebSocketMessageOperation getOperation() {
        return operation;
    }

    public void setOperation(SnakeWebSocketMessageOperation operation) {
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
}
