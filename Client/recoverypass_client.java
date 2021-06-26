package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class recoverypass_client {
    public static int port=226;
    public String username;
    public String color;
    public String a= "1";
    public recoverypass_client(String user,String color){
        username=user;
        this.color=color;
    }
    public String recovering() throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        String message=username+"#"+color;
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
        Object answer=objectInputStream.readObject();
        a="0";
        objectOutputStream.writeObject(a);
        objectOutputStream.flush();
        objectInputStream.close();
        objectOutputStream.close();
        return (String) answer;
    }

    public String getUsername() {
        return username;
    }


}
