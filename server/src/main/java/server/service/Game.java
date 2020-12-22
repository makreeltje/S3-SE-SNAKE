package server.service;

import com.google.gson.Gson;
import server.models.Board;
import server.models.CellType;
import shared.messages.MessageCreator;
import shared.messages.MessageOperationType;
import shared.messages.response.ResponseGeneratedFruit;
import shared.messages.response.ResponseMove;

import java.util.Random;

public class Game {
    private static final Random RANDOM = new Random();
    private static final Gson GSON = new Gson();
    private static final MessageCreator MESSAGE_CREATOR = new MessageCreator();

    private Snakes snakes = new Snakes();
    private int ticks = 100;

    public void updateBoard(Players players, Board board) {
        ResponseMove responseMove = new ResponseMove();

        players.getPlayerList().forEach(player -> {
            if (snakes.move(player, board)){
                responseMove.setAteFruit(true);
                generateFruit(players, board, 1);
            } else {
                responseMove.setAteFruit(false);
            }
            responseMove.setRow(player.getSnake().getHeadSnake().getRow());
            responseMove.setColumn(player.getSnake().getHeadSnake().getColumn());
            responseMove.setIndexCellType(player.getSnake().getHeadSnake().getCellType().ordinal());
            player.getSession().getAsyncRemote().sendText(GSON.toJson(MESSAGE_CREATOR.createMessage(MessageOperationType.RECEIVE_MOVE, responseMove)));
        });
    }

    public void generateFruit(Players players, Board board, int fruitCount) {
        ResponseGeneratedFruit responseGeneratedFruit = new ResponseGeneratedFruit();

        for (int i = 0; i < fruitCount; i++) {
            boolean fruitPlaced = false;
            while (!fruitPlaced) {
                int col = RANDOM.nextInt(40);
                int row = RANDOM.nextInt(75);

                if (board.getCellType(row, col) != CellType.FRUIT) {
                    board.setCellType(row, col, CellType.FRUIT);
                    fruitPlaced = true;
                    responseGeneratedFruit.addColumn(col);
                    responseGeneratedFruit.addRow(row);
                }
            }
        }
        players.getPlayerList().forEach(s -> s.getSession().getAsyncRemote().sendText(GSON.toJson(MESSAGE_CREATOR.createMessage(MessageOperationType.RECEIVE_GENERATE_FRUIT, responseGeneratedFruit))));
    }

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public Snakes getSnakes() {
        return snakes;
    }

    public void setSnakes(Snakes snakes) {
        this.snakes = snakes;
    }
}
