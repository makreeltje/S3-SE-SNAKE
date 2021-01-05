package server.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.websocket.Session;
import org.junit.jupiter.api.*;
import server.models.Player;
import server.models.Snake;

import java.util.ArrayList;
import java.util.List;


class PlayersTest {

    private Session session;
    private Player player;
    private Players players = new Players();
    private List<Player> playerList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        player = new Player(session, "Test user", true, new Snake());
        players.addPlayer(player);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addPlayer() {
        int expected = players.getPlayerList().size() + 1;

        players.addPlayer(player);

        assertEquals(expected, players.getPlayerList().size());
    }

    @Test
    void getPlayerList() {
        int expected = 1;

        int actual = players.getPlayerList().size();

        assertEquals(expected, actual);
    }

    @Test
    void setPlayerList() {
        int expected = 1;

        playerList.add(this.player);

        players.setPlayerList(playerList);

        int actual = players.getPlayerList().size();

        assertEquals(expected, actual);
    }
}