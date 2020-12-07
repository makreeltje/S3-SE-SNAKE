package server.service;

import server.models.Player;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

public class Players {
    private List<Player> playerList = new ArrayList<>();

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public Player getPlayer(String id) {
        return playerList.get(Integer.parseInt(id));
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Player getPlayerBySession(Session session) {

        for (Player player : playerList) {
            if (player.getSession().equals(session)){
                return player;
            }
        }
        return null;
    }
}
