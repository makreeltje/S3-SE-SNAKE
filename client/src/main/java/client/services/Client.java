package client.services;

public class Client {

    private int directionU;
    private int directionD;
    private int directionL;
    private int directionR;
    private int directionN;
    private Snake snake;
    private Board board;
    private int direction;
    private boolean gameOver;

    public Client(Snake snake, Board board) {
        this.snake = snake;
        this.board = board;
    }


}
