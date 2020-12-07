/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicator;

import lombok.Data;
import shared.messages.MessageOperationType;

/**
 * Message to be sent from client to client through a Communicator.
 * All clients that are subscribed to the property of a message
 * will receive a copy of this message.
 * @author Nico Kuijpers
 */
@Data
public class SnakeMessage {

    private MessageOperationType operation;

    private String property;
}
