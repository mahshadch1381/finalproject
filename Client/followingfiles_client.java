package Client;

import model.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class followingfiles_client {
    public static int port1=117;
    public static int port2=118;
    public String string;
    public Person person;
    public followingfiles_client(){

    }
    public followingfiles_client(Person p){
        person=p;
    }
    public String following_give_info() throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port1);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        String line=person.username+"#"+person.country+"#"+person.profilePath+"#"+person.password;
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
    public Person following_get_info() throws IOException, ClassNotFoundException {
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
        String line=(String) answer;
        String[] info=line.split("#");
        Person p=new Person(info[0],info[3]);
        p.setCountry(info[1]);
        p.setProfilePath(info[2]);
        return p;
    }
}
