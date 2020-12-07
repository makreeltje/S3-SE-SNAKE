/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicator;

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
public interface ISnake {
    
    /**
     * Start the connection.
     */
    public void start();
    
    /**
     * Stop the connection.
     */
    public void stop();
    
    /**
     * Register a property.
     * @param username
     * @param singlePlayer
     */
    public void register(String username, boolean singlePlayer);

    /**
     * Get position
     * @param row
     * @param column
     */
    void position(int row, int column);

    /**
     * Move snake
     * @param direction
     */
    void move(Direction direction);
}
