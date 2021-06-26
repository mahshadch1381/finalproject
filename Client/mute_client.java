package Client;

import model.Post;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class mute_client {
    public int port=219;
    public String username;
    public String client;
    public String a= "1";
    public mute_client(String user,String client){
        username=user;
         this.client=client;
    }
    public String muting() throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        String message=username+"#"+client;
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
