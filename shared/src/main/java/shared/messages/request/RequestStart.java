package shared.messages.request;

import lombok.Data;
import shared.messages.BaseMessage;

public class RequestStart extends BaseMessage {
    private boolean start;

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
}
