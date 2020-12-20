package shared.messages.request;

import lombok.Data;
import shared.messages.BaseMessage;

@Data
public class RequestRegister extends BaseMessage {

    private String username;
    private Boolean singlePlayer;
}
