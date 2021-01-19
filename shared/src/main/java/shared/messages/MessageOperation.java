/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.messages;

/**
 * Message to be sent from client to server and vice versa  using WebSockets.
 * @author Nico Kuijpers
 */
public class MessageOperation {
    
    // Operation that is requested at client side
    private MessageOperationType operation;
    
    // Property
    private String property;

    public MessageOperationType getOperation() {
        return operation;
    }

    public void setOperation(MessageOperationType operation) {
        this.operation = operation;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
