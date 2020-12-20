package shared.messages.request;

import lombok.Data;
import shared.messages.BaseMessage;

@Data
public class RequestStart extends BaseMessage {
    private boolean start;
}
