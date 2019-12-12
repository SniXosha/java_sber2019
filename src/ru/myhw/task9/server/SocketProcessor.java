package ru.myhw.task9.server;

import java.net.Socket;

public interface SocketProcessor {
    void process(Socket socket);
}
