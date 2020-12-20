package shared.messages.request;

import lombok.Data;
import shared.messages.BaseMessage;
import shared.messages.Direction;

@Data
public class RequestMove extends BaseMessage {

    private Direction direction;
}
