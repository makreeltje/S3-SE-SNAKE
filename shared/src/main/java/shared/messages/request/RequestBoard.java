package shared.messages.request;

import lombok.Data;
import shared.messages.BaseMessage;

@Data
public class RequestBoard extends BaseMessage {
    private int rowCount;
    private int columnCount;
}
