package Client;

import model.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class unfollowing_client {
    public static int port2=120;
    public String a="1";
    public Person p;
    public String username;
    public unfollowing_client(Person person,String client_username){
        p=person;
        username=client_username;
    }
    public String unfollow()throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port2);
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
