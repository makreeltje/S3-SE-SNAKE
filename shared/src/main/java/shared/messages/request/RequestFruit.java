package shared.messages.request;

import shared.messages.BaseMessage;

public class RequestFruit implements BaseMessage {
    private int fruitCount;

    public int getFruitCount() {
        return fruitCount;
    }

    public void setFruitCount(int fruitCount) {
        this.fruitCount = fruitCount;
    }
}
