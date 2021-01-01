package shared.messages.response;

import shared.messages.BaseMessage;

public class ResponseRegister implements BaseMessage {
    private int playerId;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
