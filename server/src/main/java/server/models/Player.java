package server.models;

import lombok.Data;
import shared.messages.Direction;

import javax.websocket.Session;

@Data
public class Player {
    private Session session;
    private String username;
    private String password;
    private boolean singlePlayer;
    private Snake snake;
    private boolean ready = false;
    private Direction direction;

    public Player(Session session, String username, boolean singlePlayer, Snake snake) {
        this.session = session;
        this.username = username;
        this.singlePlayer = singlePlayer;
        this.snake = snake;
    }

    public void setDirection(Direction direction) {

        if (direction == Direction.UP && this.direction == Direction.DOWN ||
                direction == Direction.DOWN && this.direction == Direction.UP ||
                direction == Direction.LEFT && this.direction == Direction.RIGHT ||
                direction == Direction.RIGHT && this.direction == Direction.LEFT)
            return;
        this.direction = direction;
    }
}
