package communicator.rest;

import shared.rest.Authentication;

public interface ISnakeRest {

    /**
     * Posts an Http request to the rest server to login a defined user
     * @param signIn player model based on input
     * @return complete user model received from server
     */
    Authentication postSignIn(Authentication signIn);

    /**
     * Post an Http request to the rest server to register a defined user
     * @param signUp player model based on input
     * @return complete user model received from server
     */
    Authentication postSignUp(Authentication signUp);
}
