package server.service;

import server.models.Board;
import server.models.Cell;
import server.models.CellType;
import server.models.Player;

import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private static final int TICKS = 100;

    private Board board;
    private Players players;
    private Timer timer = new Timer();

    public Game(int rowCount, int columnCount) {
        board = new Board(rowCount, columnCount);
    }

    public void startGame() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // updateBoard();
            }
        }, 0, TICKS);
    }

    public void stopGame() {
        timer.cancel();
    }

    public void updateBoard(Player player, int row, int column) {
        player.getSnake().addSnakePart(new Cell(row, column, CellType.SNAKE));
        player.getSnake().removeTailSnake();
    }




}
