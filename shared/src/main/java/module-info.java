module shared {
    requires gson;
    requires lombok;

    exports shared.messages;
    exports shared.messages.request;
    exports shared.messages.response;
    exports shared.rest;
}