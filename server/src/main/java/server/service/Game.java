package server.service;

import com.google.gson.Gson;
import lombok.Data;
import server.models.Board;
import server.models.Player;
import shared.messages.MessageCreator;
import shared.messages.MessageOperationType;
import shared.messages.response.ResponseMove;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@Data
public class Game {
    private int ticks = 100;

    private Board board;
    private Players players;
    private Snakes snakes = new Snakes();
    private Timer timer = new Timer();

    public void updateBoard(List<Player> players) {
        MessageCreator messageCreator = new MessageCreator();
        ResponseMove responseMove = new ResponseMove();
        Gson gson = new Gson();

        players.forEach(player -> {
            snakes.move(player);
            responseMove.setRow(player.getSnake().getHeadSnake().getRow());
            responseMove.setColumn(player.getSnake().getHeadSnake().getColumn());
            responseMove.setIndexCellType(player.getSnake().getHeadSnake().getCellType().ordinal());
            player.getSession().getAsyncRemote().sendText(gson.toJson(messageCreator.createMessage(MessageOperationType.RECEIVE_MOVE, responseMove)));
        });
    }

    public void generateFruit(int fruitCount) {
        Random rand = new Random();

        for (int i = 0; i < fruitCount; i++) {
            int col = rand.nextInt(76);
            int row = rand.nextInt(41);


        }
    }
}
