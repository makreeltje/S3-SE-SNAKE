/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

/**
 * Message to be sent from client to server and vice versa  using WebSockets.
 * @author Nico Kuijpers
 */
public class SnakeWebSocketMessage extends SnakeWebSocketMessageRegister {
    
    // Operation that is requested at client side
    private SnakeWebSocketMessageOperation operation;
    
    // Property
    private String property;
    
    // Content
    private String content;

    public SnakeWebSocketMessageOperation getOperation() {
        return operation;
    }

    public void setOperation(SnakeWebSocketMessageOperation operation) {
        this.operation = operation;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
