package server.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import server.models.Board;
import server.models.Player;
import server.models.Snake;
import shared.messages.Direction;

import javax.websocket.Session;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GameTest {

    private Game game;
    private Board board;
    private Session session;
    private Player player;
    private Players players = new Players();

    @BeforeEach
    void setUp() {
        this.game = new Game();
        this.board = new Board(40, 75);
        session.getAsyncRemote().sendText("test");
        player = new Player(session, "Test user", true, new Snake());
        players.addPlayer(player);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void updateBoard() {
        game.updateBoard(players, board);
    }

    @Test
    void generateFruit() {
    }

    @Test
    void toggleReady() {
    }

    @Test
    void getTicks() {
    }

    @Test
    void setTicks() {
    }

    @Test
    void getSnakes() {
    }

    @Test
    void setSnakes() {
    }
}