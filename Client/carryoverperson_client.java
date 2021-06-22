package Client;

import model.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class carryoverperson_client {
    public int port1=131;
    public static int port2=132;
    public String string;
   // public Person person;
    public carryoverperson_client(){
    }
    public carryoverperson_client(String p){
        string=p;
    }
    public String person_give_info() throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port1);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        String line=string;
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
    public String person_get_info() throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port2);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject("find");
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
