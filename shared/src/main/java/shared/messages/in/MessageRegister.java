package shared.messages;

import lombok.Data;
import shared.BaseMessage;

@Data
public class MessageRegister extends BaseMessage {

    private String username;
    private Boolean singlePlayer;
}
