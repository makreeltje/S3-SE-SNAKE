package shared.messages.request;

import lombok.Data;
import shared.messages.BaseMessage;

@Data
public class RequestFruit extends BaseMessage {
    private int fruitCount;
}
