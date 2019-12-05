package ru.myhw.task9;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;

public class RemoteMethodCaller {
    private final String host;
    private final int port;

    public RemoteMethodCaller(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object call(Object arg) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(host, port);
        ObjectOutputStream os = new ObjectOutputStream(Objects.requireNonNull(socket).getOutputStream());
        os.writeObject(arg);
        os.flush();

        ObjectInputStream is = new ObjectInputStream(Objects.requireNonNull(socket).getInputStream());
        return Objects.requireNonNull(is).readObject();
    }
}
