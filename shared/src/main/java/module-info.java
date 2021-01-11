module shared {
    requires com.google.gson;
    exports shared.messages;
    exports shared.messages.request;
    exports shared.messages.response;
    exports shared.rest;
}