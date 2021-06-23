package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class findmainprofile {
    public static int port=147;
    public String user;
    public findmainprofile(String username) {
        user=username;
    }
    public String findprofile() throws IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1",port);
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        String message = user;
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
        Object answer = objectInputStream.readObject();
        String a = "0";
        objectOutputStream.writeObject(a);
        objectOutputStream.flush();
        objectInputStream.close();
        objectOutputStream.close();
        return (String) answer;
    }
}
