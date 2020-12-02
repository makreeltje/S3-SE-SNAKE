/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

/**
 * Indicate type of operation to be performed.
 * @author Nico Kuijpers
 */
public enum SnakeWebSocketMessageOperation {
    REGISTERPROPERTY,         // Register a property (client only)
    UNREGISTERPROPERTY,       // Unregister a registered property (client only
    SUBSCRIBETOPROPERTY,      // Subscribe to a property (client only)
    UNSUBSCRIBEFROMPROPERTY,  // Unsubscribe from a property (client only)
    UPDATEPROPERTY,           // Update property (client and server)
}
