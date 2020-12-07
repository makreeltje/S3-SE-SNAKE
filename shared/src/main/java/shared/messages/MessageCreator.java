package shared;

import com.google.gson.Gson;
import shared.messages.MessageBoard;
import shared.messages.MessageRegister;
import shared.messages.MessageOperation;
import shared.messages.MessageOperationType;

public class MessageCreator {

    private Gson gson = new Gson();

    public BaseMessage createResult(MessageOperation message) {

        switch (message.getOperation()) {
            case REGISTER_PROPERTY:
                try {
                    return gson.fromJson(message.getProperty(), MessageRegister.class);
                } catch (Exception e) {
                    e.getMessage();
                }
                break;
            case UPDATE_BOARD:
                try {
                    return gson.fromJson(message.getProperty(), MessageBoard.class);
                } catch (Exception e) {
                    e.getMessage();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + message);
        }
        return null;
    }

    public MessageOperation createMessage(MessageOperationType operation, BaseMessage property) {

        MessageOperation messageOperation = new MessageOperation();

        messageOperation.setProperty(gson.toJson(property));

        messageOperation.setOperation(operation);
        return messageOperation;

    }

}
