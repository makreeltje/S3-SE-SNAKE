package shared.messages.response;

import shared.messages.BaseMessage;

public class ResponseMove implements BaseMessage {
    private int[][] cells = new int[75][40];

    private int playerId;


    public int[][] getCells() {
        return cells;
    }

    public void setCells(int[][] cells) {
        this.cells = cells;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
