package shared.messages.response;

import shared.messages.BaseMessage;

import java.util.List;

public class ResponseRegister implements BaseMessage {
    private int playerId;
    private String playerName;
    private List<String> playerList;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<String> getPlayerList() {
        return playerList;
    }

    public void addPlayerList(String item) {
        playerList.add(item);
    }

    public void setPlayerList(List<String> playerList) {
        this.playerList = playerList;
    }
}
