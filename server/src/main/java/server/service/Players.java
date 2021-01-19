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
        return playerList.stream().filter(player -> player.getSession().getId().equals(id)).findFirst().orElse(null);
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Player getPlayerBySession(Session session) {
        return playerList.stream().filter(player -> player.getSession().equals(session)).findFirst().orElse(null);
    }
}
