package shared.messages;

import lombok.Data;
import shared.BaseMessage;

@Data
public class MessageMove extends BaseMessage {

    private Direction direction;
}
