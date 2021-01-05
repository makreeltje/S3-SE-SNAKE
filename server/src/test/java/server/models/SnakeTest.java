package server.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.service.Players;
import server.service.Snakes;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {

    private Session session;
    private Player player;
    private Players players;
    private Snake snake;
    private Cell cell;
    private List<Cell> cellList;

    @BeforeEach
    void setUp() {
        this.players = new Players();
        this.cell = new Cell(0, 1);
        this.cellList = new ArrayList<>();

        this.player = new Player(this.session, "Test user", true, new Snake());
        this.players.addPlayer(player);
        this.snake = player.getSnake();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSnakeParts() {
        int expected = 1;
        int actual = snake.getSnakeParts().size();

        assertEquals(expected, actual);
    }

    @Test
    void addSnakePart() {
        int expected = 2;

        player.getSnake().addSnakePart(this.cell);

        int actual = snake.getSnakeParts().size();

        assertEquals(expected, actual);
    }

    @Test
    void removeTailSnake() {
        int expected = 2;

        snake.addSnakePart(this.cell);

        int actual = snake.getSnakeParts().size();

        assertEquals(expected, actual);

        snake.removeTailSnake();

        expected = 1;

        actual = snake.getSnakeParts().size();

        assertEquals(expected, actual);
    }

    @Test
    void getTailSnake() {
        Cell expectedCell = new Cell(0, 0);
        Cell cell = new Cell(0, 1);

        snake.addSnakePart(cell);

        Cell actualCell = snake.getTailSnake();

        assertEquals(expectedCell.getColumn(), actualCell.getColumn());
        assertEquals(expectedCell.getRow(), actualCell.getRow());
    }

    @Test
    void getHeadSnake() {
        Cell expectedCell = new Cell(0, 1);
        Cell cell = new Cell(0, 1);

        snake.addSnakePart(cell);

        Cell actualCell = snake.getHeadSnake();

        assertEquals(expectedCell.getColumn(), actualCell.getColumn());
        assertEquals(expectedCell.getRow(), actualCell.getRow());
    }

    @Test
    void setSnakeParts() {
        cellList.add(cell);

        snake.setSnakeParts(cellList);

        assertEquals(this.cellList, snake.getSnakeParts());
    }
}