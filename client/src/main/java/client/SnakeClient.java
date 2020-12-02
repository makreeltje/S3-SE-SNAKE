package client;

import client.services.Board;
import javafx.application.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

public class SnakeClient extends Application implements Observer {

    public ClientHandler handler;
    private JPanel contentPane;
    public Board board;

    public static void main(String[] args) {
        SnakeClient frame = new SnakeClient();
        frame.setVisible(true);
    }

    public SnakeClient() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handler.stop();
            }
        });
        setTitle("Snake Multiplayer (2020 Rick Meels)");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,450,300);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0,0));
        setContentPane(contentPane);

        board = new Board();
        contentPane.add(board, BorderLayout.CENTER);
        pack();

        handler = new ClientHandler(this);
        handler.start();


    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
