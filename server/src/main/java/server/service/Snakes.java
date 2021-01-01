package server.service;

import server.models.Board;
import server.models.Cell;
import server.models.Player;

public class Snakes {

    public boolean move(Player player, Board board) {
        Cell cell = getNextCell(player);
        player.getSnake().addSnakePart(cell);
        if (board.getCellValue(player.getSnake().getHeadSnake().getRow(), player.getSnake().getHeadSnake().getColumn()) == 0) {
            board.setCellValue(player.getSnake().getTailSnake().getRow(), player.getSnake().getTailSnake().getColumn(), 0);
            player.getSnake().removeTailSnake();
            return false;
        }
        board.setCellValue(player.getSnake().getHeadSnake().getRow(), player.getSnake().getHeadSnake().getColumn(), 0);
        return true;
    }

    private Cell getNextCell(Player player) {

        Cell cell = new Cell(player.getSnake().getHeadSnake().getRow(), player.getSnake().getHeadSnake().getColumn());

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
