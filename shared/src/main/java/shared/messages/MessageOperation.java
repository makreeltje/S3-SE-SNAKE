/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.messages;

import lombok.Data;

/**
 * Message to be sent from client to server and vice versa  using WebSockets.
 * @author Nico Kuijpers
 */
@Data
public class MessageOperation {
    
    // Operation that is requested at client side
    private MessageOperationType operation;
    
    // Property
    private String property;
}
