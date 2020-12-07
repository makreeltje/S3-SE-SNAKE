module shared {
    requires gson;
    requires lombok;

    exports shared.messages;
    exports shared.messages.in;
    exports shared.messages.out;
}