package ru.myhw.task9.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final SocketProcessor processor;
    private final int port;

    private Server(int port, SocketProcessor processor) {
        this.processor = processor;
        this.port = port;
    }

    public static Server of(int port, SocketProcessor processor) {
        return new Server(port, processor);
    }

    public void run() {
        new Thread(this::runServerOnSocket).start();
    }

    private void runServerOnSocket() {
        ServerSocket serverSocket = getServerSocket();
        if (serverSocket == null) return;
        System.out.println("Server started.");
        while (true) {
            Socket clientSocket = getClientSocket(serverSocket);
            if (clientSocket == null) continue;
            System.out.println("New connection.");
            new Thread(() -> processor.process(clientSocket)).start();
        }
    }

    private Socket getClientSocket(ServerSocket serverSocket) {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ServerSocket getServerSocket() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return serverSocket;
    }
}
