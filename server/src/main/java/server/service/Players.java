package server.service;

import server.models.Player;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private List<Player> playerList = new ArrayList<>();

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public Player getPlayer(int id) {
        return playerList.get(id);
    }
}
