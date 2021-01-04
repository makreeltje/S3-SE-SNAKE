module communicator {
    requires gson;
    requires shared;
    requires javax.websocket.client.api;
    requires java.sql;
    requires lombok;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;

    exports communicator.websocket;
    exports communicator.rest;
}