package server.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import server.models.Board;
import server.models.Player;
import server.models.Snake;

import javax.websocket.Session;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class GameTest {

    @Mock
    private Session session;
    private Player player;
    private Game game;
    private Board board;
    private final Players players = new Players();

    @BeforeEach
    void setUp() {
        session = mock(Session.class);
        when(session.isOpen()).thenReturn(true);
        when(session.getId()).thenReturn("1");
        player = new Player(session, "Test user", true, new Snake());
        players.addPlayer(player);
        game = new Game();
        board = new Board(40, 75);
        board.setCellValue(0, 0, 1);
    }

    @AfterEach
    void tearDown() throws IOException {
        player.getSession().close();
    }

    @Test
    void updateBoard_MoveSnakeOneCell_CheckIfThereIsOnlyOnePlayerCell() {
        int expected = 0;

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 75; j++) {
                if (board.getCellValue(i, j) == 1){
                    expected++;
                }
            }
        }

        game.updateBoard(players,board);

        int actual = 0;

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 75; j++) {
                if (board.getCellValue(i, j) == 1){
                    actual++;
                }
            }
        }


        assertEquals(expected,actual);
    }

    @Test
    void updateBoard_MoveSnakeOneCell_CheckIfPlayerCellMovedOneCell() {
        int[] expected = {0, 1};

        game.updateBoard(players,board);

        int[] actual = {0, 0};

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 75; j++) {
                if (board.getCellValue(i, j) == 1){
                    actual[0] = i;
                    actual[1] = j;
                }
            }
        }


        assertEquals(expected[0],actual[0]);
        assertEquals(expected[1],actual[1]);
    }

    @Test
    void updateBoard_MoveSnakeOneCell_CheckIfPlayerCellGrewOneCell() {
        board.setCellValue(0, 1, 9);
        int expected = 2;

        game.updateBoard(players,board);

        int actual = 0;

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 75; j++) {
                if (board.getCellValue(i, j) == 1){
                    actual++;
                }
            }
        }


        assertEquals(expected,actual);
    }

    @Test
    void generateFruit_GenerateFruitOnBoard_CheckIf3FruitCellsAreGenerated() {
        int expected = 3;

        game.generateFruit(board, 3);

        int actual = 0;

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 75; j++) {
                if (board.getCellValue(i, j) == 9){
                    actual++;
                }
            }
        }

        assertEquals(expected, actual);
    }

    @Test
    void generateFruit_GenerateFruitOnBoard_CheckIf6FruitCellsAreGenerated() {
        int expected = 6;

        game.generateFruit(board, 6);

        int actual = 0;

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 75; j++) {
                if (board.getCellValue(i, j) == 9){
                    actual++;
                }
            }
        }

        assertEquals(expected, actual);
    }

    @Test
    void generateFruit_GenerateFruitOnBoard_CheckIf20FruitCellsAreGenerated() {
        int expected = 20;

        game.generateFruit(board, 20);

        int actual = 0;

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 75; j++) {
                if (board.getCellValue(i, j) == 9){
                    actual++;
                }
            }
        }

        assertEquals(expected, actual);
    }

    @Test
    void generateFruit_GenerateFruitOnBoard_CheckIf500FruitCellsAreGenerated() {
        int expected = 500;

        game.generateFruit(board, 500);

        int actual = 0;

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 75; j++) {
                if (board.getCellValue(i, j) == 9){
                    actual++;
                }
            }
        }

        assertEquals(expected, actual);
    }

    @Test
    void toggleReady_TogglePlayersReadyProperty_SeeIfPlayerIsReadyAfterToggle() {
        boolean expected = true;

        game.toggleReady(players, session);

        boolean actual = player.isReady();
    }

    @Test
    void toggleReady_TogglePlayersReadyProperty_SeeIfPlayerIsUnReadyAfter2Toggles() {
        boolean expected = false;

        game.toggleReady(players, session);
        game.toggleReady(players, session);

        boolean actual = player.isReady();

        assertEquals(expected, actual);
    }

    @Test
    void getTicks() {
        int expected = 250;

        int actual = game.getTicks();

        assertEquals(expected, actual);
    }
}