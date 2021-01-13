package client;

/**
 * Interface provided by the graphical user interface of the snake game
 * @author Rick Meels
 */
public interface SnakeGUI {

    /**
     * Communicate the result of a login request by the player.
     * @param username players username
     * @param password players password
     */
    void loginPlayer(String username, String password);

    /**
     * Communicate the result of a register request by the player.
     * @param username players username
     * @param email players email
     * @param password players password
     */
    void registerPlayer(String username, String email, String password);

    /**
     * Communicate the result of a login request by the player.
     * @param singlePlayer the decision of players play style
     */
    void startGame(boolean singlePlayer);

    /**
     * Sets graphical board based on communicated board from server-side
     * @param playerId current players id to know what cells are your's
     * @param cells complete board with snake locations
     */
    void updatePosition(int playerId, int[][] cells);

    /**
     * Places fruit pieces on board based on what is generated in the server
     * @param row which row the fruit is positioned
     * @param column which column the fruit is positioned
     */
    void placeFruit(int row, int column);

    /**
     * Makes sure that player input is not being registered anymore
     */
    void endGame();
}
