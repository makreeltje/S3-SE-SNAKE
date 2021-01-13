package server.service;

import com.google.gson.Gson;
import server.models.Board;
import server.models.Player;
import shared.messages.MessageCreator;
import shared.messages.MessageOperationType;
import shared.messages.response.ResponseMove;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private static final Random RANDOM = new Random();
    private static final Gson GSON = new Gson();
    private static final MessageCreator MESSAGE_CREATOR = new MessageCreator();

    private final Snakes snakes = new Snakes();
    private static final int TICKS = 500;

    public void updateBoard(Players players, Board board) {
        List<ResponseMove> responseMoveList = new ArrayList<>();
        players.getPlayerList().forEach(player -> {
            ResponseMove responseMove = new ResponseMove();
            if (snakes.move(player, board)){
                generateFruit(board, 1);
            }

            player.getSnake().getSnakeParts().forEach(c -> board.setCellValue(c.getRow(), c.getColumn(), Integer.parseInt(player.getSession().getId())));

            responseMove.setCells(board.getGrid());

            responseMove.setPlayerId(Integer.parseInt(player.getSession().getId()));
            responseMoveList.add(responseMove);
        });

        players.getPlayerList().forEach(player -> {
            for (ResponseMove responseMove: responseMoveList) {
                try {
                    player.getSession().getAsyncRemote().sendText(GSON.toJson(MESSAGE_CREATOR.createMessage(MessageOperationType.RESPONSE_MOVE, responseMove)));
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        });
    }

    public void generateFruit(Board board, int fruitCount) {

        for (int i = 0; i < fruitCount; i++) {
            boolean fruitPlaced = false;
            while (!fruitPlaced) {
                int row = RANDOM.nextInt(40);
                int col = RANDOM.nextInt(75);

                if (board.getCellValue(row, col) != 9) {
                    board.setCellValue(row, col, 9);
                    fruitPlaced = true;
                }
            }
        }
    }

    public void toggleReady(Players players, Session session) {
        Player player = players.getPlayerBySession(session);

        player.setReady(!player.isReady());
    }

    public int getTicks() {
        return TICKS;
    }
}
