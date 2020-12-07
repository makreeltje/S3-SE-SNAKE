package shared.messages.in;

import lombok.Data;
import shared.messages.BaseMessage;

@Data
public class MessageBoard extends BaseMessage {
    private int rowCount;
    private int columnCount;
}
