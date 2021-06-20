package Client;

import model.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class followingclient {
    public static int port1=115;
    public String a="1";
   public Person p;
    public String username;
    public followingclient(Person person,String client_username) {
        p=person;
        username=client_username;
    }
    public String follow()throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port1);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        String message="follow"+"-"+p.username+"#"+username;
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
    public String unfollow()throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port1);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        String message="unfollow"+"-"+p.username+"#"+username;
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
}
