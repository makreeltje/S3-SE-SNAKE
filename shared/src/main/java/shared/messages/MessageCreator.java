package shared.messages;

import com.google.gson.Gson;
import shared.messages.request.RequestFruit;
import shared.messages.request.RequestMove;
import shared.messages.request.RequestRegister;
import shared.messages.request.RequestStart;
import shared.messages.response.ResponseGeneratedFruit;
import shared.messages.response.ResponseMove;

public class MessageCreator {

    private final Gson gson = new Gson();

    public BaseMessage createResult(MessageOperation message) {
        BaseMessage result;
        switch (message.getOperation()) {
            case SEND_REGISTER:
                result = gson.fromJson(message.getProperty(), RequestRegister.class);
                break;
            case SEND_MOVE:
                result = gson.fromJson(message.getProperty(), RequestMove.class);
                break;
            case SEND_READY:
                result = gson.fromJson(message.getProperty(), RequestStart.class);
                break;
            case SEND_GENERATE_FRUIT:
                result = gson.fromJson(message.getProperty(), RequestFruit.class);
                break;
            case RECEIVE_MOVE:
                result = gson.fromJson(message.getProperty(), ResponseMove.class);
                break;
            case RECEIVE_GENERATE_FRUIT:
                result = gson.fromJson(message.getProperty(), ResponseGeneratedFruit.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + message);
        }
        return result;
    }

    public MessageOperation createMessage(MessageOperationType operation, BaseMessage property) {
        MessageOperation messageOperation = new MessageOperation();
        messageOperation.setProperty(gson.toJson(property));
        messageOperation.setOperation(operation);
        return messageOperation;
    }

}
