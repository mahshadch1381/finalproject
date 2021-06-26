package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class deletingacount_client {
    public static int port=209;
    public String username;
    public deletingacount_client(String user){
     username=user;
    }
    public String deleting() throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        String line=username;
        objectOutputStream.writeObject(line);
        objectOutputStream.flush();
        Object answer=objectInputStream.readObject();
        String a="0";
        objectOutputStream.writeObject(a);
        objectOutputStream.flush();
        objectInputStream.close();
        objectOutputStream.close();
        return (String) answer;
    }

}
