package Client;

import model.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class countoffollowers_client {
    public static int port1=123;
    public static int port2=124;
    public String username;
    public countoffollowers_client(String user){
        username=user;
    }
    public String count_of_followers() throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port1);
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
    public String count_of_following() throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port2);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(username);
        objectOutputStream.flush();
        Object answer=objectInputStream.readObject();
        String a="0";
        objectOutputStream.writeObject(a);
        objectOutputStream.flush();
        objectInputStream.close();
        objectOutputStream.close();
       return  (String) answer;

    }
}


