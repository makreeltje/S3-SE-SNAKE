package server.models;

import shared.messages.Direction;

import javax.websocket.Session;

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
        this.direction = Direction.RIGHT;
    }

    public void setDirection(Direction direction) {

        if (direction == Direction.UP && this.direction == Direction.DOWN ||
                direction == Direction.DOWN && this.direction == Direction.UP ||
                direction == Direction.LEFT && this.direction == Direction.RIGHT ||
                direction == Direction.RIGHT && this.direction == Direction.LEFT)
            return;
        this.direction = direction;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSinglePlayer() {
        return singlePlayer;
    }

    public void setSinglePlayer(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public Direction getDirection() {
        return direction;
    }
}
