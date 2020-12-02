/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicator;

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
     * @param property
     */
    public void register(String property);
    
    /**
     * Unregister a property.
     * @param property
     */
    public void unregister(String property);

    /**
     * Subscribe to a property.
     * @param property
     */
    public void subscribe(String property);

    /**
     * Unsubscribe from a property.
     * @param property
     */
    public void unsubscribe(String property);

    /**
     * Update a property by sending a message to all clients
     * that are subscribed to the property of the message.
     * @param message the message to be sent
     */
    public void update(SnakeMessage message);
}
