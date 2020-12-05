package server;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import server.models.Message;
import server.models.Player;
import server.models.Snake;
import server.service.Players;
import shared.SnakeWebSocketMessage;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.*;

// https://github.com/jetty-project/embedded-jetty-websocket-examples/tree/master/javax.websocket-example/src/main/java/org/eclipse/jetty/demo

/**
 * Server-side implementation of Communicator using WebSockets for communication.
 * <p>
 * This code is based on example code from:
 * https://github.com/jetty-project/embedded-jetty-websocket-examples/blob/
 * master/javax.websocket-example/src/main/java/org/eclipse/jetty/
 * demo/EventServerSocket.java
 *
 * @author Nico Kuijpers, based on example project
 */

@ServerEndpoint(value = "/communicator/")
public class SnakeServerWebSocket {

    // Logger
    private static final Logger LOGGER = Logger.getLogger(SnakeServerWebSocket.class.getName());

    // All sessions
    private static final List<Session> sessions = new ArrayList<>();
    private static final Players players = new Players();

    // Map each property to list of sessions that are subscribed to that property
    private static final Map<String, List<Session>> propertySessions = new HashMap<>();

    @OnOpen
    public void onConnect(Session session) {
        LOGGER.info("[WebSocket Connected] SessionID: " + session.getId());
        String message = format("[New client with client side session ID]: %s", session.getId());
        sessions.add(session);
        LOGGER.info(format("[#sessions]: %d", sessions.size()));
    }

    @OnMessage
    public void onText(String message, Session session) {
        LOGGER.info(format("[WebSocket Session ID] : %s [Received] : %s", session.getId(), message));
        handleMessageFromClient(message, session);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        LOGGER.info(format("[WebSocket Session ID] : %s [Socket Closed]: %s", session.getId(), reason));
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        LOGGER.log(Level.SEVERE, format("[WebSocket Session ID] : %s[ERROR]: ", session.getId()));
        LOGGER.log(Level.SEVERE, cause.getMessage());
    }

    // Handle incoming message from client
    private void handleMessageFromClient(String jsonMessage, Session session) {
        Gson gson = new Gson();
        SnakeWebSocketMessage wbMessage = null;
        try {
            wbMessage = gson.fromJson(jsonMessage, SnakeWebSocketMessage.class);
        } catch (JsonSyntaxException ex) {
            LOGGER.log(Level.INFO, format("[WebSocket ERROR: cannot parse Json message %s", jsonMessage));
            return;
        }

        // Operation defined in message
        Message message = defineMessage(wbMessage);

        if (null != message.getOperation()) {
            switch (message.getOperation()) {
                case REGISTERPROPERTY:
                    // Register property if not registered yet
                    players.addPlayer(new Player(session, message.getUsername(), message.isSinglePlayer(), new Snake()));
                    break;
                /*case UPDATEPROPERTY:
                    // Send the message to all clients that are subscribed to this property
                    if (propertySessions.get(message.getProperty()) != null) {
                        LOGGER.info("[WebSocket send ] " + jsonMessage + " to:");
                        for (Session sess : propertySessions.get(message.getProperty())) {
                            // Use asynchronous communication
                            LOGGER.info("\t\t >> Client associated with server side session ID: " + sess.getId());
                            sess.getAsyncRemote().sendText(jsonMessage);
                        }
                        LOGGER.info("[WebSocket end sending message to subscribers]");
                    }
                    break;*/
                default:
                    LOGGER.log(Level.SEVERE, "[WebSocket ERROR: cannot process Json message " + jsonMessage);
                    break;
            }
        }
    }

    private Message defineMessage(SnakeWebSocketMessage wbMessage) {
        Message message = new Message();

        message.setOperation(wbMessage.getOperation());
        message.setProperty(wbMessage.getProperty());
        message.setUsername(wbMessage.getUsername());
        //message.setPassword(wbMessage.getPassword());
        message.setSinglePlayer(wbMessage.getSinglePlayer());

        return message;
    }
}