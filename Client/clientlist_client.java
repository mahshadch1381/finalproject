package Client;

import model.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class clientlist_client {
    public static int port=202;
    public static String a="1";
    List<Person> list=new Vector<>();
    public  static List<Person> findingClientList() throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        String message="find the list";
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
        Object answer=objectInputStream.readObject();
        a="0";
        objectOutputStream.writeObject(a);
        objectOutputStream.flush();
        objectInputStream.close();
        objectOutputStream.close();
        List<Person> people=new Vector<>();
       for (String s:(List<String>)answer){
           String[] info=s.split("#");
          String name=info[0];
          String country=info[1];
          String username=info[2];
          String pass=info[3];
          String picture=info[4];
          Person p=new Person(username,pass);
          p.setName(name);
          p.setCountry(country);
          p.setProfilePath(picture);
          people.add(p);
       }
       return people;
    }

}
