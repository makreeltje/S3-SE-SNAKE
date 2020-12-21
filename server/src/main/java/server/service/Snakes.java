package server.service;

import server.models.Cell;
import server.models.Player;

public class Snakes {

    public void grow(Player player) {

        Cell cell = getNextCell(player);

        player.getSnake().addSnakePart(cell);

    }

    public void move(Player player) {
        Cell cell = getNextCell(player);
        player.getSnake().addSnakePart(cell);
        player.getSnake().removeTailSnake();
    }

    private Cell getNextCell(Player player) {

        Cell cell = player.getSnake().getHeadSnake();

        // TODO: Check if cell is getting out of bounds

        switch (player.getDirection()) {
            case UP:
                cell.setRow(cell.getRow() - 1);
                break;
            case DOWN:
                cell.setRow(cell.getRow() + 1);
                break;
            case LEFT:
                cell.setColumn(cell.getColumn() - 1);
                break;
            case RIGHT:
                cell.setColumn(cell.getColumn() + 1);
                break;
        }

        return cell;
    }
}
