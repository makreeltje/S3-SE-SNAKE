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
    REQUEST_REGISTER,         // Register a property (client only)
    REQUEST_MOVE,
    REQUEST_READY,
    REQUEST_GENERATE_FRUIT,
    RESPONSE_REGISTER,
    RESPONSE_MOVE,
    RESPONSE_GROW,
    RESPONSE_GENERATE_FRUIT,
    RESPONSE_START,
}
