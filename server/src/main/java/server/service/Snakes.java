package server.service;

import server.models.Cell;
import server.models.CellType;
import server.models.Player;

public class Snakes {

    public void grow(Player player, int row, int column) {

        Cell cell = new Cell(row, column, CellType.SNAKE);

        player.getSnake().addSnakePart(cell);

    }
}
