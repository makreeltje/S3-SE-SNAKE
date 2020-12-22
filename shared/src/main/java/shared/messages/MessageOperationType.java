/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.messages;

/**
 * Indicate type of operation to be performed.
 * @author Nico Kuijpers
 */
public enum MessageOperationType {
    SEND_REGISTER,         // Register a property (client only)
    SEND_MOVE,
    SEND_READY,
    SEND_GENERATE_FRUIT,
    RECEIVE_MOVE,
    RECEIVE_GROW,
    RECEIVE_GENERATE_FRUIT,
}
