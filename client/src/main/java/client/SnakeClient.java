package client;

import communicator.Snake;
import communicator.SnakeClientWebSocket;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Observable;
import java.util.Observer;

public class SnakeClient extends Application implements Observer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SnakeClient.class);

    private static final int FRUITS = 3;
    private static final int TICKS = 100;

    private static final int RECTANGLE_SIZE = 20;
    private static final int NR_SQUARES_HORIZONTAL = 75;
    private static final int NR_SQUARES_VERTICAL = 40;

    private static final int TEXT_INPUT_WIDTH = 400;
    private static final int BUTTON_WIDTH = 200;
    private static final String BUTTON_LAYOUT = "-fx-border-color: #ffffff; -fx-border-width: 3px; -fx-background-color: transparent; -fx-text-fill: #BEBEBE; -fx-font: 2em Consolas;";

    private VBox mainMenu = new VBox(20);
    private VBox loginMenu = new VBox(20);
    private TextField txtUsername = new TextField();
    private PasswordField txtPassword = new PasswordField();
    private Button btnLogin = new Button("Login");

    private Button btnSinglePlayer = new Button("Single Player");
    private Button btnMultiPlayer = new Button("Multi Player");
    private Button btnHistory = new Button("History");
    private Button btnLogout = new Button("Exit");


    private Rectangle[][] playingFieldArea;

    private Snake communicator = null;

    @Override
    public void start(Stage stage) throws Exception {
        LOGGER.info("Snake Client started");
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("snake-icon.png")));

        Group root = new Group();
        Scene scene = new Scene(root, NR_SQUARES_HORIZONTAL * RECTANGLE_SIZE,NR_SQUARES_VERTICAL * RECTANGLE_SIZE);
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
                LOGGER.error("Register Player error: {}", e.getMessage());
            }
        });

        txtUsername.setPromptText("Username...");
        txtPassword.setPromptText("Password...");
        btnSinglePlayer.setPrefWidth(BUTTON_WIDTH);
        btnMultiPlayer.setPrefWidth(BUTTON_WIDTH);
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

        StackPane layout = new StackPane();
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

        /*communicator = SnakeClientWebSocket.getInstance();
        communicator.addObserver(this);
        communicator.start();*/
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
