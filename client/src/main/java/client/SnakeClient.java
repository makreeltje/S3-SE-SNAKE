package client;

import client.models.Cell;
import client.models.CellType;
import communicator.SnakeCommunicator;
import communicator.SnakeCommunicatorClientWebSocket;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import shared.messages.Direction;
import shared.messages.MessageCreator;
import shared.messages.MessageOperation;
import shared.messages.response.ResponseMove;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SnakeClient extends Application implements Observer {

    private static final Logger LOGGER = Logger.getLogger(SnakeClient.class.getName());

    private static final int FRUITS = 3;


    private static final double RECTANGLE_SIZE = 20;
    private static final int NR_SQUARES_HORIZONTAL = 75;
    private static final int NR_SQUARES_VERTICAL = 40;

    private static final int BUTTON_WIDTH = 200;

    private VBox mainMenu = new VBox(20);
    private VBox loginMenu = new VBox(20);
    private Scene scene;
    private StackPane layout = new StackPane();

    private TextField txtUsername = new TextField();
    private PasswordField txtPassword = new PasswordField();

    private Button btnLogin = new Button("Login");
    private Button btnSinglePlayer = new Button("Single Player");
    private Button btnMultiPlayer = new Button("Multi Player");
    private Button btnHistory = new Button("History");
    private Button btnLogout = new Button("Exit");

    private Rectangle[][] playingFieldArea;

    private SnakeCommunicator communicator = null;

    private String username;

    private List<Cell> cellList = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        LOGGER.info("Snake Client started");
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("snake-icon.png")));

        cellList.add(new Cell(0,0, CellType.SNAKE));

        Group root = new Group();
        scene = new Scene(root, NR_SQUARES_HORIZONTAL * RECTANGLE_SIZE,NR_SQUARES_VERTICAL * RECTANGLE_SIZE);
        scene.getStylesheets().add(this.getClass().getClassLoader().getResource("style.css").toExternalForm());

        // Main playing field
        playingFieldArea = new Rectangle[NR_SQUARES_HORIZONTAL][NR_SQUARES_VERTICAL];
        for (int i = 0; i < NR_SQUARES_HORIZONTAL; i++) {
            for (int j = 0; j < NR_SQUARES_VERTICAL; j++) {
                double x = i * (RECTANGLE_SIZE);
                double y = j * (RECTANGLE_SIZE);
                Rectangle rectangle = new Rectangle(x, y, RECTANGLE_SIZE, RECTANGLE_SIZE);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(0.05);
                rectangle.setFill(Color.web("#424242"));
                rectangle.setVisible(true);
                playingFieldArea[i][j] = rectangle;
                root.getChildren().add(rectangle);
            }
        }

        Label label = new Label("Snake XI");
        label.setStyle("-fx-text-fill: #BEBEBE; -fx-font: 5em Consolas; -fx-padding: 0 0 500 0");

        btnLogin.setOnAction(event -> {
            try {
                registerPlayer();
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Register Player error: {}", e.getMessage());
            }
        });

        txtUsername.setPromptText("Username...");
        txtPassword.setPromptText("Password...");

        btnSinglePlayer.setOnAction(event -> {
            try {
                startGame(false);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Start game error: {}", e.getMessage());
            }
        });

        btnSinglePlayer.setOnAction(event -> {
            try {
                startGame(true);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Start game error: {}", e.getMessage());
            }
        });

        btnHistory.setPrefWidth(BUTTON_WIDTH);
        btnLogout.setPrefWidth(BUTTON_WIDTH);

        loginMenu.setAlignment(Pos.CENTER);
        loginMenu.getChildren().addAll(txtUsername, txtPassword, btnLogin);
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.getChildren().addAll(btnSinglePlayer, btnMultiPlayer, btnHistory, btnLogout);
        mainMenu.setVisible(false);

        StackPane glass = new StackPane();
        glass.getChildren().addAll(label, loginMenu, mainMenu);

        glass.setStyle("-fx-background-color: rgba(10, 10, 10, 0.4);");
        glass.setMinWidth(scene.getWidth() - 80);
        glass.setMinHeight(scene.getHeight() - 80);

        layout.getChildren().add(glass);
        layout.setStyle("-fx-padding: 40;");
        root.getChildren().add(layout);

        stage.setTitle("Snake XI");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    private void registerPlayer() {
        loginMenu.setVisible(false);
        mainMenu.setVisible(true);

        //TODO: REST call to register player to database

        username = txtUsername.getText();


    }

    private void startGame(boolean singlePlayer) {
        layout.setVisible(false);

        communicator = SnakeCommunicatorClientWebSocket.getInstance();
        communicator.addObserver(this);
        communicator.start();

        communicator.register(username, singlePlayer);
        drawSnake();
        communicator.generateFruits(FRUITS);
        scene.setOnKeyPressed(SnakeClient.this::keyPressed);

    }

    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case KP_UP:
            case W:
                communicator.move(Direction.UP);
                break;
            case KP_DOWN:
            case S:
                communicator.move(Direction.DOWN);
                break;
            case KP_LEFT:
            case A:
                communicator.move(Direction.LEFT);
                break;
            case KP_RIGHT:
            case D:
                communicator.move(Direction.RIGHT);
                break;
            case SPACE:
                communicator.ready(true);
                break;
            default:
                break;
        }

    }

    private void updatePosition(int row, int column, boolean ateFruit) {
        cellList.add(new client.models.Cell(row, column, CellType.SNAKE));
        playingFieldArea[cellList.get(0).getColumn()][cellList.get(0).getRow()].setFill(Color.web("#424242"));
        if (!ateFruit)
            cellList.remove(0);
        drawSnake();
    }

    private void drawSnake() {
        cellList.forEach(cell -> playingFieldArea[cell.getColumn()][cell.getRow()].setFill(Color.RED));
    }

    public void endGame(){
        scene.setOnKeyPressed(null);
    }

    @Override
    public void update(Observable o, Object arg) {
        MessageOperation message = (MessageOperation) arg;
        MessageCreator messageCreator = new MessageCreator();

        switch (message.getOperation()) {
            case RECEIVE_MOVE:
                ResponseMove messageMove =  (ResponseMove) messageCreator.createResult(message);
                updatePosition(messageMove.getRow(), messageMove.getColumn(), false);
                break;
            case RECEIVE_GROW:

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + message.getOperation());
        }


    }
}
