package communicator.rest;

import shared.rest.Authentication;

public interface ISnakeRest {
    Authentication postSignIn(Authentication signIn);
    Authentication postSignUp(Authentication signUp);
}
