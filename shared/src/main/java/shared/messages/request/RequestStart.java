package shared.messages.request;

import shared.messages.BaseMessage;

public class RequestStart implements BaseMessage {
    private boolean start;

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
}
