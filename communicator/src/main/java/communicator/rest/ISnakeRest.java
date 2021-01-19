package communicator.rest;

import shared.messages.request.RequestRegister;

public interface ISnakeRest {

    /**
     * Posts an Http request to the rest server to login a defined user
     * @param signIn player model based on input
     * @return complete user model received from server
     */
    RequestRegister postSignIn(RequestRegister signIn);

    /**
     * Post an Http request to the rest server to register a defined user
     * @param signUp player model based on input
     * @return complete user model received from server
     */
    RequestRegister postSignUp(RequestRegister signUp);
}
