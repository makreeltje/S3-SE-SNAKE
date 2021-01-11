package client;

public class PlayerView {
    private int id;
    private String username;
    private String state;

    public PlayerView(int id, String name, String ready) {
        this.id = id;
        this.username = name;
        this.state = ready;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
