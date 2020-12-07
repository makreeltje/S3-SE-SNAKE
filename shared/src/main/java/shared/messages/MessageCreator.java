package shared.messages;

import com.google.gson.Gson;
import shared.messages.in.MessageMove;
import shared.messages.in.MessageRegister;
import shared.messages.out.MessageMoveOut;

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
            case SEND_MOVE:
                try {
                    return gson.fromJson(message.getProperty(), MessageMove.class);
                } catch (Exception e) {
                    e.getMessage();
                }
                break;
            case RECIEVE_MOVE:
                try {
                    return gson.fromJson(message.getProperty(), MessageMoveOut.class);
                } catch (Exception e) {
                    e.getMessage();
                }
            default:
                throw new IllegalStateException("Unexpected value: " + message);
        }
        return null;
    }

    public MessageOperation createMessage(MessageOperationType operation, BaseMessage property) {

        MessageOperation messageOperation = new MessageOperation();

        messageOperation.setProperty(gson.toJson(property));

        messageOperation.setOperation(operation);

        //String message = gson.toJson(messageOperation);

        return messageOperation;
    }

}
