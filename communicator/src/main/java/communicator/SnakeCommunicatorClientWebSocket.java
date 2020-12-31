package communicator;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import shared.messages.MessageCreator;
import shared.messages.*;
import shared.messages.request.RequestFruit;
import shared.messages.request.RequestMove;
import shared.messages.request.RequestRegister;
import shared.messages.request.RequestStart;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

// https://github.com/jetty-project/embedded-jetty-websocket-examples/tree/master/javax.websocket-example/src/main/java/org/eclipse/jetty/demo

/**
 * Client-side implementation of abstract class Communicator using 
 * WebSockets for communication.
 * 
 * This code is based on the example code from:
 * https://github.com/jetty-project/embedded-jetty-websocket-examples/blob/
 * master/javax.websocket-example/src/main/java/org/eclipse/jetty/
 * demo/EventServerSocket.java
 *
 * @author Nico Kuijpers
 */
@ClientEndpoint
public class SnakeCommunicatorClientWebSocket extends SnakeCommunicator {
    
    // Singleton
    private static SnakeCommunicatorClientWebSocket instance = null;

    /**
     * The local websocket uri to connect to.
     */
    private static final String URI = "ws://localhost:8095/communicator/";
    private static final Logger LOGGER = Logger.getLogger(SnakeCommunicatorClientWebSocket.class.getName());

    private Session session;

    private String message;
    
    private Gson gson = null;

    private MessageCreator messageCreator = new MessageCreator();
    
    // Status of the webSocket client
    boolean isRunning = false;
    
    // Private constructor (singleton pattern)
    private SnakeCommunicatorClientWebSocket() {
        gson = new Gson();
    }
    
    /**
     * Get singleton instance of this class.
     * Ensure that only one instance of this class is created.
     * @return instance of client web socket
     */
    public static SnakeCommunicatorClientWebSocket getInstance() {
        if (instance == null) {
            LOGGER.info("[WebSocket Client create singleton instance]");
            instance = new SnakeCommunicatorClientWebSocket();
        }
        return instance;
    }

    /**
     *  Start the connection.
     */
    @Override
    public void start() {
        LOGGER.info("[WebSocket Client start connection]");
        if (!isRunning) {
            startClient();
            isRunning = true;
        }
    }

    @Override
    public void stop() {
        LOGGER.info("[WebSocket Client stop]");
        if (isRunning) {
            stopClient();
            isRunning = false;
        }
    }

    @OnOpen
    public void onWebSocketConnect(Session session){
        LOGGER.log(Level.INFO, "[WebSocket Client open session] {0}", session.getRequestURI());
        this.session = session;
    }

    @OnMessage
    public void onWebSocketText(String message, Session session){
        this.message = message;
        LOGGER.log(Level.INFO, "[WebSocket Client message received] {0}", message);
        processMessage(message);
    }

    @OnError
    public void onWebSocketError(Session session, Throwable cause) {
        LOGGER.log(Level.SEVERE, "[WebSocket Client connection error] {0}", cause.toString());
    }
    
    @OnClose
    public void onWebSocketClose(CloseReason reason){
        LOGGER.log(Level.INFO, "[WebSocket Client close session] {0} for reason {1}", new Object[]{session.getRequestURI(), reason});
        session = null;
    }

    @Override
    public void register(String username, boolean singlePlayer) {
        RequestRegister requestRegister = new RequestRegister();

        requestRegister.setUsername(username);
        requestRegister.setSinglePlayer(singlePlayer);

        sendMessageToServer(messageCreator.createMessage(MessageOperationType.REQUEST_REGISTER, requestRegister));

    }

    @Override
    public void move(Direction direction) {
        RequestMove requestMove = new RequestMove();

        requestMove.setDirection(direction);

        sendMessageToServer(messageCreator.createMessage(MessageOperationType.REQUEST_MOVE, requestMove));
    }

    @Override
    public void toggleReady() {
        RequestStart requestStart = new RequestStart();
        sendMessageToServer(messageCreator.createMessage(MessageOperationType.REQUEST_READY, requestStart));
    }

    @Override
    public void generateFruits(int fruitCount) {
        RequestFruit requestFruit = new RequestFruit();
        requestFruit.setFruitCount(fruitCount);
        sendMessageToServer(messageCreator.createMessage(MessageOperationType.REQUEST_GENERATE_FRUIT, requestFruit));
    }


    private void sendMessageToServer(MessageOperation message) {
        String jsonMessage = gson.toJson(message);
        // Use asynchronous communication
        session.getAsyncRemote().sendText(jsonMessage);
    }
    
    /**
     * Get the latest message received from the websocket communication.
     * @return The message from the websocket communication
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message, but no action is taken when the message is changed.
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Start a WebSocket client.
     */
    private void startClient() {
        LOGGER.info("[WebSocket Client start]");
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(URI));
            
        } catch (IOException | URISyntaxException | DeploymentException ex) {
            // do something useful eventually
            ex.getMessage();
        }
    }

    /**
     * Stop the client when it is running.
     */
    private void stopClient(){
        LOGGER.info("[WebSocket Client stop]");
        try {
            session.close();

        } catch (IOException ex){
            // do something useful eventually
            ex.getMessage();
        }
    }
    
    // Process incoming json message
    private void processMessage(String jsonMessage) {
        
        // Parse incoming message
        MessageOperation wsMessage;
        try {
            wsMessage = gson.fromJson(jsonMessage, MessageOperation.class);
        }
        catch (JsonSyntaxException ex) {
            LOGGER.log(Level.SEVERE, "[WebSocket Client ERROR: cannot parse Json message {0}", jsonMessage);
            return;
        }
        
        // Only operation update property will be further processed
        MessageOperationType operation;
        operation = wsMessage.getOperation();
        if (operation == null) {
            LOGGER.log(Level.SEVERE, "[WebSocket Client ERROR: update property operation expected]");
            return;
        }
        
        // Obtain property from message
        String property = wsMessage.getProperty();
        if (property == null || "".equals(property)) {
            LOGGER.log(Level.SEVERE, "[WebSocket Client ERROR: property not defined]");
            return;
        }
        
        // Create instance of CommunicaterMessage for observers
        MessageOperation commMessage = new MessageOperation();
        commMessage.setOperation(operation);
        commMessage.setProperty(property);
        
        // Notify observers
        this.setChanged();
        this.notifyObservers(commMessage);
    }
}
