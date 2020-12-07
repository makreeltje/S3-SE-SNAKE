package shared.messages.in;

import lombok.Data;
import shared.messages.BaseMessage;

@Data
public class MessageRegister extends BaseMessage {

    private String username;
    private Boolean singlePlayer;
}
