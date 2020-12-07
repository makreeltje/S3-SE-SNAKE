package shared.messages.in;

import lombok.Data;
import shared.messages.BaseMessage;
import shared.messages.Direction;

@Data
public class MessageMove extends BaseMessage {

    private Direction direction;
}
