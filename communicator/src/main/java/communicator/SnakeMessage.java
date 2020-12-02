/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communicator;

/**
 * Message to be sent from client to client through a Communicator.
 * All clients that are subscribed to the property of a message
 * will receive a copy of this message.
 * @author Nico Kuijpers
 */
public class SnakeMessage {
    
    // Property to which receiving clients should be subscribed
    private String property;
    
    // Content of the message
    private String content;

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
