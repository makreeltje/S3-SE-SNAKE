package shared.messages.request;

import lombok.Data;
import shared.messages.BaseMessage;

public class RequestRegister extends BaseMessage {

    private String username;
    private Boolean singlePlayer;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getSinglePlayer() {
        return singlePlayer;
    }

    public void setSinglePlayer(Boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }
}
