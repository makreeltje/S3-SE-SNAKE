package shared.messages;

import lombok.Data;
import shared.BaseMessage;

@Data
public class MessageBoard extends BaseMessage {
    private int rowCount;
    private int columnCount;
}
