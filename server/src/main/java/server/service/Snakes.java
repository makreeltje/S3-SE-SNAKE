package server.service;

import server.models.Board;
import server.models.Cell;
import server.models.CellType;
import server.models.Player;

public class Snakes {

    public boolean move(Player player, Board board) {
        Cell cell = getNextCell(player);
        player.getSnake().addSnakePart(cell);
        if (board.getCellType(player.getSnake().getHeadSnake().getColumn(), player.getSnake().getHeadSnake().getRow()) != CellType.FRUIT) {
            player.getSnake().removeTailSnake();
            return false;
        }
        board.setCellType(player.getSnake().getHeadSnake().getColumn(), player.getSnake().getHeadSnake().getRow(), CellType.EMPTY);
        return true;
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
