package shared.messages.response;

import lombok.Data;
import shared.messages.BaseMessage;
import shared.messages.CellType;

@Data
public class ResponseGrow extends BaseMessage {
    private int row;
    private int column;
    private CellType cellType;

    public CellType setIndexCellType(int index) {
        return cellType.values()[index];
    }
}
