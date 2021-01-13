package shared.messages;

import com.google.gson.Gson;
import shared.messages.request.RequestFruit;
import shared.messages.request.RequestMove;
import shared.messages.request.RequestRegister;
import shared.messages.request.RequestStart;
import shared.messages.response.ResponseGeneratedFruit;
import shared.messages.response.ResponseMove;
import shared.messages.response.ResponseRegister;
import shared.messages.response.ResponseStart;

public class MessageCreator {

    private final Gson gson = new Gson();

    public BaseMessage createResult(MessageOperation message) {
        BaseMessage result;
        switch (message.getOperation()) {
            case REQUEST_LOGIN:
            case REQUEST_REGISTER:
                result = gson.fromJson(message.getProperty(), RequestRegister.class);
                break;
            case REQUEST_MOVE:
                result = gson.fromJson(message.getProperty(), RequestMove.class);
                break;
            case REQUEST_READY:
                result = gson.fromJson(message.getProperty(), RequestStart.class);
                break;
            case REQUEST_GENERATE_FRUIT:
                result = gson.fromJson(message.getProperty(), RequestFruit.class);
                break;
            case RESPONSE_REGISTER:
                result = gson.fromJson(message.getProperty(), ResponseRegister.class);
                break;
            case RESPONSE_MOVE:
                result = gson.fromJson(message.getProperty(), ResponseMove.class);
                break;
            case RESPONSE_GENERATE_FRUIT:
                result = gson.fromJson(message.getProperty(), ResponseGeneratedFruit.class);
                break;
            case RESPONSE_START:
                result = gson.fromJson(message.getProperty(), ResponseStart.class);
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
