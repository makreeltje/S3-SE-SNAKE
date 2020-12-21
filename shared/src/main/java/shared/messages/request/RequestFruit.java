package shared.messages.request;

import lombok.Data;
import shared.messages.BaseMessage;

public class RequestFruit extends BaseMessage {
    private int fruitCount;

    public int getFruitCount() {
        return fruitCount;
    }

    public void setFruitCount(int fruitCount) {
        this.fruitCount = fruitCount;
    }
}
