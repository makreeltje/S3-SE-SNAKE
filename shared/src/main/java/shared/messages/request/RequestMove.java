package shared.messages.request;

import lombok.Data;
import shared.messages.BaseMessage;
import shared.messages.Direction;

public class RequestMove extends BaseMessage {

    private Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
