package shared.messages.request;

import shared.messages.BaseMessage;

public class RequestRegister implements BaseMessage {

    private String username;
    private String email;
    private String password;
    private Boolean singlePlayer;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSinglePlayer() {
        return singlePlayer;
    }

    public void setSinglePlayer(Boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }
}
