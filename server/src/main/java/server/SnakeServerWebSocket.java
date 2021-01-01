package server;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import server.models.Board;
import server.models.Player;
import server.models.Snake;
import server.service.Game;
import server.service.Players;
import shared.messages.BaseMessage;
import shared.messages.MessageCreator;
import shared.messages.MessageOperation;
import shared.messages.MessageOperationType;
import shared.messages.request.RequestFruit;
import shared.messages.request.RequestMove;
import shared.messages.request.RequestRegister;
import shared.messages.response.ResponseRegister;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

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
    private static final int FRUIT = 3;
    private static final List<Session> sessions = new ArrayList<>();
    private static final Players players = new Players();
    private static final Timer timer = new Timer();
    private static final Game game = new Game();
    private static final Board board = new Board(40, 75);
    private MessageCreator messageCreator = new MessageCreator();

    @OnOpen
    public void onConnect(Session session) {
        LOGGER.log(Level.INFO, "[WebSocket Connected] SessionID: {0}", session.getId());
        sessions.add(session);
        LOGGER.log(Level.INFO, "[#sessions]: {0}", players.getPlayerList().size());
    }

    @OnMessage
    public void onText(String message, Session session) {
        LOGGER.log(Level.INFO, "[WebSocket Session ID] : {0} [Received] : {1}", new Object[]{session.getId(), message});
        handleMessageFromClient(message, session);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        LOGGER.log(Level.INFO, "[WebSocket Session ID] : {0} [Socket Closed]: {1}", new Object[]{session.getId(), reason});
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        LOGGER.log(Level.SEVERE, "[WebSocket Session ID] : {0} [ERROR]: ", session.getId());
        LOGGER.log(Level.SEVERE, cause.getMessage());
    }

    // Handle incoming message from client
    private void handleMessageFromClient(String jsonMessage, Session session) {
        Gson gson = new Gson();
        MessageOperation wbMessage = null;
        try {
            wbMessage = gson.fromJson(jsonMessage, MessageOperation.class);
        } catch (JsonSyntaxException ex) {
            LOGGER.log(Level.INFO, "[WebSocket ERROR: cannot parse Json message {0}", jsonMessage);
            return;
        }

        BaseMessage message;
        message = messageCreator.createResult(wbMessage);

        // Operation defined in message
        if (null != wbMessage.getOperation()) {
            switch (wbMessage.getOperation()) {
                case REQUEST_REGISTER:
                    RequestRegister registerMessage = (RequestRegister) message;
                    // Register property if not registered yet
                    players.addPlayer(new Player(session, registerMessage.getUsername(), registerMessage.getSinglePlayer(), new Snake()));

                    ResponseRegister responseRegister = new ResponseRegister();
                    responseRegister.setPlayerId(Integer.parseInt(players.getPlayerBySession(session).getSession().getId()));

                    session.getAsyncRemote().sendText(gson.toJson(messageCreator.createMessage(MessageOperationType.RESPONSE_REGISTER, responseRegister)));

                    // Send the message to all clients that are subscribed to this property
                    LOGGER.log(Level.INFO, "[WebSocket send ] {0} to:", jsonMessage);
                    break;
                case REQUEST_MOVE:
                    RequestMove requestMove = (RequestMove) message;
                    Player player = players.getPlayerBySession(session);
                    player.setDirection(requestMove.getDirection());
                    break;
                case REQUEST_READY:
                    game.toggleReady(players, session);

                    if (players.getPlayerList().stream().allMatch(Player::isReady)) {
                        game.generateFruit(players, board, FRUIT);
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                game.updateBoard(players, board);
                            }
                        }, game.getTicks(), game.getTicks());
                    }
                    break;
                default:
                    LOGGER.log(Level.SEVERE, "[WebSocket ERROR: cannot process Json message {0}", jsonMessage);
                    break;
            }
        }
    }
}