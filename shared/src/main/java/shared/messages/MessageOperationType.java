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
    REGISTER_PROPERTY,         // Register a property (client only)
    UPDATE_PROPERTY,           // Update property (client and server),
    SEND_MOVE,
    RECIEVE_MOVE,
}
