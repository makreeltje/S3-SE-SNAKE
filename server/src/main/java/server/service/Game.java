package server.service;

import com.google.gson.Gson;
import server.models.Board;
import server.models.Player;
import shared.messages.MessageCreator;
import shared.messages.MessageOperationType;
import shared.messages.response.ResponseGeneratedFruit;
import shared.messages.response.ResponseMove;
import shared.messages.response.ResponseStart;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private static final Random RANDOM = new Random();
    private static final Gson GSON = new Gson();
    private static final MessageCreator MESSAGE_CREATOR = new MessageCreator();

    private Snakes snakes = new Snakes();
    private int ticks = 500;

    public void updateBoard(Players players, Board board) {
        List<ResponseMove> responseMoveList = new ArrayList<>();
        players.getPlayerList().forEach(player -> {
            ResponseMove responseMove = new ResponseMove();
            if (snakes.move(player, board)){
//                responseMove.setAteFruit(true);
                generateFruit(players, board, 1);
            } else {
//                responseMove.setAteFruit(false);
            }

            player.getSnake().getSnakeParts().forEach(c -> board.setCellValue(c.getRow(), c.getColumn(), Integer.parseInt(player.getSession().getId())));

            responseMove.setCells(board.getBoard());

            String string = "";

/*            for (int i = 0; i < 40; i++) {
                for (int j = 0; j < 75; j++) {
                    string += board.getCellValue(i, j) + " ";
                }
                string += "\n";
            }
            System.out.println(string);
            System.out.println();
            System.out.println();*/

            responseMove.setPlayerId(Integer.parseInt(player.getSession().getId()));
            responseMoveList.add(responseMove);
        });

        players.getPlayerList().forEach(player -> {
            for (ResponseMove responseMove: responseMoveList) {
                player.getSession().getAsyncRemote().sendText(GSON.toJson(MESSAGE_CREATOR.createMessage(MessageOperationType.RESPONSE_MOVE, responseMove)));
            }
        });
    }

    public void generateFruit(Players players, Board board, int fruitCount) {

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

        //TODO: something broke, not sure
        ResponseStart responseStart = new ResponseStart();

//        players.getPlayerList().forEach(p -> {
//            responseStart.addId(Integer.parseInt(p.getSession().getId()));
//            responseStart.addName(p.getUsername());
//            responseStart.addReady(p.isReady());
//
//            p.getSession().getAsyncRemote().sendText(GSON.toJson(MESSAGE_CREATOR.createMessage(MessageOperationType.RESPONSE_START, responseStart)));
//        });
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
