package shared.messages.request;

import shared.messages.BaseMessage;
import shared.messages.Direction;

public class RequestMove implements BaseMessage {

    private Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
