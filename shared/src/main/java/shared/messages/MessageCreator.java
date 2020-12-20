package shared.messages;

import com.google.gson.Gson;
import shared.messages.request.RequestFruit;
import shared.messages.request.RequestMove;
import shared.messages.request.RequestRegister;
import shared.messages.request.RequestStart;
import shared.messages.response.ResponseMove;

public class MessageCreator {

    private Gson gson = new Gson();

    public BaseMessage createResult(MessageOperation message) {
        try {
            switch (message.getOperation()) {
                case REGISTER_PROPERTY:
                    return gson.fromJson(message.getProperty(), RequestRegister.class);
                case SEND_MOVE:
                    return gson.fromJson(message.getProperty(), RequestMove.class);
                case SEND_READY:
                    return gson.fromJson(message.getProperty(), RequestStart.class);
                case RECEIVE_MOVE:
                    return gson.fromJson(message.getProperty(), ResponseMove.class);
                case SEND_GENERATE_FRUIT:
                    return gson.fromJson(message.getProperty(), RequestFruit.class);
                default:
                    throw new IllegalStateException("Unexpected value: " + message);
            }
        } catch (Exception e) {
            e.getMessage();
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
