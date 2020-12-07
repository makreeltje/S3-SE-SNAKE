package server;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import server.models.Board;
import server.models.Cell;
import server.models.Player;
import server.models.Snake;
import server.service.Players;
import server.service.Snakes;
import shared.messages.*;
import shared.messages.in.MessageMove;
import shared.messages.in.MessageRegister;
import shared.messages.out.MessageMoveOut;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;
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
    private static final Board board = new Board(40, 75);
    private static final Snakes snakes = new Snakes();

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
        MessageOperation wbMessage = null;
        try {
            wbMessage = gson.fromJson(jsonMessage, MessageOperation.class);
        } catch (JsonSyntaxException ex) {
            LOGGER.log(Level.INFO, format("[WebSocket ERROR: cannot parse Json message %s", jsonMessage));
            return;
        }

        BaseMessage message;
        MessageCreator messageCreator = new MessageCreator();
        message = messageCreator.createResult(wbMessage);

        // Operation defined in message
        if (null != wbMessage.getOperation()) {
            switch (wbMessage.getOperation()) {
                case REGISTER_PROPERTY:
                    MessageRegister registerMessage = (MessageRegister) message;
                    // Register property if not registered yet
                    players.addPlayer(new Player(session, registerMessage.getUsername(), registerMessage.getSinglePlayer(), new Snake()));

                    // Send the message to all clients that are subscribed to this property
                    LOGGER.info("[WebSocket send ] " + jsonMessage + " to:");

                    break;
                case SEND_MOVE:
                    MessageMove messageMove = (MessageMove) message;
                    Player player = players.getPlayerBySession(session);
                    Cell cell = player.getSnake().getHeadSnake();
                    snakes.move(player, messageMove.getDirection());

                    MessageMoveOut messageMoveOut = new MessageMoveOut();

                    messageMoveOut.setRow(cell.getRow());
                    messageMoveOut.setColumn(cell.getColumn());
                    messageMoveOut.setIndexCellType(CellType.valueOf(cell.getCellType().toString()).ordinal());

                    session.getAsyncRemote().sendText(gson.toJson(messageCreator.createMessage(MessageOperationType.RECIEVE_MOVE, messageMoveOut)));
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
}