package server.models;

import lombok.Data;

import javax.websocket.Session;

@Data
public class Player {
    private Session session;
    private String username;
    private String password;
    private boolean singlePlayer;
    private Snake snake;
    private boolean ready = false;

    public Player(Session session, String username, boolean singlePlayer, Snake snake) {
        this.session = session;
        this.username = username;
        this.singlePlayer = singlePlayer;
        this.snake = snake;
    }
}
