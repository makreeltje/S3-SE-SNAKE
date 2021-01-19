package client;

import communicator.websocket.SnakeCommunicatorWebSocket;
import communicator.websocket.SnakeCommunicatorClientWebSocket;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import shared.models.Direction;
import shared.messages.MessageCreator;
import shared.messages.MessageOperation;
import shared.messages.response.ResponseGeneratedFruit;
import shared.messages.response.ResponseMove;
import shared.messages.response.ResponseRegister;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SnakeClient extends Application implements Observer, SnakeGUI {

    private static final Logger LOGGER = Logger.getLogger(SnakeClient.class.getName());

    private static final int FRUITS = 20;

    private static final double RECTANGLE_SIZE = 20;
    private static final int NR_SQUARES_HORIZONTAL = 75;
    private static final int NR_SQUARES_VERTICAL = 40;

    private final VBox mainMenu = new VBox(20);
    private final VBox loginMenu = new VBox(20);
    private final VBox registerMenu = new VBox(20);

    private final TableColumn<PlayerView, String> column1 = new TableColumn<>("Username");
    private final TableColumn<PlayerView, String> column2 = new TableColumn<>("State");
    private final TableView<PlayerView> tableView = new TableView<>();
    private final ObservableList<PlayerView> playerViews = FXCollections.observableArrayList();

    private Scene scene;
    private final StackPane layout = new StackPane();

    private final TextField txtUsernameLogin = new TextField();
    private final TextField txtUsernameRegister = new TextField();
    private final PasswordField txtPasswordLogin = new PasswordField();
    private final PasswordField txtPasswordRegister = new PasswordField();
    private final TextField txtEmail = new TextField();

    private final Button btnLogin = new Button("Login");
    private final Button btnRegister = new Button("Register");
    private final Button btnSignIn = new Button("Sign In");
    private final Button btnSignUp = new Button("Sign Up");
    private final Button btnSinglePlayer = new Button("Play");
    private final Button btnMultiPlayer = new Button("Multi Player");
    private final Button btnHistory = new Button("History");
    private final Button btnLogout = new Button("Exit");

    private Rectangle[][] playingFieldArea;
    private SnakeCommunicatorWebSocket communicator = null;

    private String username;
    private String password = txtPasswordLogin.getText();
    private String email;
    private int playerId;

    @Override
    public void start(Stage stage) throws Exception {
        LOGGER.info("Snake Client started");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("snake-icon.png"))));

        Group root = new Group();
        scene = new Scene(root, NR_SQUARES_HORIZONTAL * RECTANGLE_SIZE, NR_SQUARES_VERTICAL * RECTANGLE_SIZE);
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
        label.setStyle("-fx-text-fill: #bebebe; -fx-font: 5em Consolas; -fx-padding: 0 0 500 0");

        btnLogin.setOnAction(event -> {
            try {
                username = txtUsernameLogin.getText();
                password = txtPasswordLogin.getText();
                loginPlayer(username, password);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Register Player error: {}", e.getMessage());
            }
        });

        btnRegister.setOnAction(event -> {
            try {
                username = txtUsernameRegister.getText();
                email = txtEmail.getText();
                password = txtPasswordRegister.getText();
                registerPlayer(username, email, password);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Register Player error: {}", e.getMessage());
            }
        });

        btnSignIn.setOnAction(event -> {
            registerMenu.setVisible(false);
            loginMenu.setVisible(true);
            }
        );

        btnSignUp.setOnAction(event -> {
            loginMenu.setVisible(false);
            registerMenu.setVisible(true);
        });

        txtUsernameLogin.setPromptText("Username...");
        txtUsernameRegister.setPromptText("Username...");
        txtEmail.setPromptText("Email...");
        txtPasswordLogin.setPromptText("Password...");
        txtPasswordRegister.setPromptText("Password...");

        btnSinglePlayer.setOnAction(event -> {
            try {
                startGame();
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Start game error: {}", e.getMessage());
            }
        });

        btnSinglePlayer.setOnAction(event -> {
            try {
                startGame();
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Start game error: {}", e.getMessage());
            }
        });

        registerMenu.setAlignment(Pos.CENTER);
        registerMenu.getChildren().addAll(txtUsernameRegister, txtEmail, txtPasswordRegister, btnRegister, btnSignIn);
        registerMenu.setVisible(false);
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.getChildren().addAll(btnSinglePlayer, btnHistory, btnLogout);
        mainMenu.setVisible(false);
        column1.setCellValueFactory(new PropertyValueFactory<>("username"));
        column2.setCellValueFactory(new PropertyValueFactory<>("state"));
        tableView.getColumns().addAll(column1, column2);
        tableView.setItems(playerViews);
        tableView.setVisible(false);
        loginMenu.setAlignment(Pos.CENTER);
        loginMenu.getChildren().addAll(txtUsernameLogin, txtPasswordLogin, btnLogin, btnSignUp);
        loginMenu.setVisible(true);


        StackPane glass = new StackPane();
        glass.getChildren().addAll(label, loginMenu, mainMenu, registerMenu, tableView);

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

    @Override
    public void loginPlayer(String username, String password) {
        loginMenu.setVisible(false);
        mainMenu.setVisible(true);

        communicator = SnakeCommunicatorClientWebSocket.getInstance();
        communicator.addObserver(this);
        communicator.start();
        communicator.login(username, password, true);

    }

    @Override
    public void registerPlayer(String username, String email, String password) {
        registerMenu.setVisible(false);
        mainMenu.setVisible(true);

        communicator = SnakeCommunicatorClientWebSocket.getInstance();
        communicator.addObserver(this);
        communicator.start();
        communicator.register(username, email, password, true);
    }

    @Override
    public void startGame() {
        mainMenu.setVisible(false);
        layout.setVisible(false);

        communicator.generateFruits(FRUITS);
        scene.setOnKeyPressed(SnakeClient.this::keyPressed);

    }

    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case KP_UP:
            case UP:
            case W:
                communicator.move(Direction.UP);
                break;
            case KP_DOWN:
            case DOWN:
            case S:
                communicator.move(Direction.DOWN);
                break;
            case KP_LEFT:
            case LEFT:
            case A:
                communicator.move(Direction.LEFT);
                break;
            case KP_RIGHT:
            case RIGHT:
            case D:
                communicator.move(Direction.RIGHT);
                break;
            case SPACE:
                communicator.toggleReady();
                break;
            default:
                break;
        }

    }

    @Override
    public synchronized void updatePosition(int[][] cells) {



        for (int column = 0; column < NR_SQUARES_HORIZONTAL; column++) {
            for (int row = 0; row < NR_SQUARES_VERTICAL; row++) {
                if (cells[row][column] == 0)
                    playingFieldArea[column][row].setFill(Color.web("#424242"));
                else if (cells[row][column] == 1)
                    playingFieldArea[column][row].setFill(Color.RED);
                else if (cells[row][column] == 2)
                    playingFieldArea[column][row].setFill(Color.BLUE);
                else if (isBetween(cells[row][column], 3, 8))
                    playingFieldArea[column][row].setFill(Color.GREEN);
                else if (cells[row][column] == 9)
                    playingFieldArea[column][row].setFill(Color.YELLOW);
            }
        }
    }

    private static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    @Override
    public void placeFruit(int row, int column) {
        playingFieldArea[row][column].setFill(Color.YELLOW);
    }

    @Override
    public void endGame() {
        scene.setOnKeyPressed(null);
    }

    @Override
    public void update(Observable o, Object arg) {
        MessageOperation message = (MessageOperation) arg;
        MessageCreator messageCreator = new MessageCreator();

        switch (message.getOperation()) {
            case RESPONSE_REGISTER:
                ResponseRegister responseRegister = (ResponseRegister) messageCreator.createResult(message);

                Platform.runLater(() -> playerViews.add(new PlayerView(responseRegister.getPlayerId(), responseRegister.getPlayerName(), "not ready")));
                break;
            case RESPONSE_MOVE:
                ResponseMove messageMove = (ResponseMove) messageCreator.createResult(message);
                Platform.runLater(() -> updatePosition(messageMove.getCells()));
                break;
            case RESPONSE_GENERATE_FRUIT:
                ResponseGeneratedFruit responseGeneratedFruit = (ResponseGeneratedFruit) messageCreator.createResult(message);
                Platform.runLater(() -> {
                    for (int i = 0; i < responseGeneratedFruit.getColumn().size(); i++) {
                        placeFruit(responseGeneratedFruit.getRow().get(i), responseGeneratedFruit.getColumn().get(i));
                    }
                });
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + message.getOperation());
        }
    }
}
