module communicator {
    requires gson;
    requires shared;
    requires javax.websocket.client.api;
    requires java.sql;
    requires lombok;

    exports communicator;
}