/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicator.websocket;

import shared.messages.Direction;

/**
 * Interface of Communicator in order to 
 * 1) start and stop connection;
 * 2) register and unregister properties;
 * 3) subscribe to and unsubscribe from properties;
 * 4) update properties by sending a message all clients that are
 *    subscribed to the property of the message.
 * 
 * @author Nico Kuijpers
 */
public interface ISnakeWebsocket {
    
    /**
     * Start the connection.
     */
    void start();
    
    /**
     * Stop the connection.
     */
    void stop();
    
    /**
     * Register a property.
     * @param username
     * @param singlePlayer
     */
    void register(String username, boolean singlePlayer);

    /**
     * Move snake
     * @param direction
     */
    void move(Direction direction);

    /**
     * Sets player ready
     */
    void toggleReady();

    /**
     * Generate fruit pieces
     * @param fruitCount
     */
    void generateFruits(int fruitCount);
}
