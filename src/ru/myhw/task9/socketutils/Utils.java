package ru.myhw.task9.socketutils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;

public class Utils {
    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static Object readFromSocket(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(Objects.requireNonNull(socket).getInputStream());
        return Objects.requireNonNull(is).readObject();
    }

    public static void writeToSocket(Socket socket, Object object) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(Objects.requireNonNull(socket).getOutputStream());
        os.writeObject(object);
        os.flush();
    }
}
