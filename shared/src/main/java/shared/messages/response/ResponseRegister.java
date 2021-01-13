package shared.messages.response;

import shared.messages.BaseMessage;
import shared.models.Player;

import java.util.List;

public class ResponseRegister implements BaseMessage {
    private int playerId;
    private String playerName;
    private List<Player> playerList;

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

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
