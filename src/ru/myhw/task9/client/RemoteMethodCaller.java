package ru.myhw.task9.client;

import java.io.IOException;
import java.net.Socket;

import static ru.myhw.task9.socketutils.Utils.readFromSocket;
import static ru.myhw.task9.socketutils.Utils.writeToSocket;

public class RemoteMethodCaller {
    private final String host;
    private final int port;

    public RemoteMethodCaller(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object call(Object arg) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(host, port);
        writeToSocket(socket, arg);
        return readFromSocket(socket);
    }
}
