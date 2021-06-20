package Client;

import model.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class getpersoninfo_client {
    public static int port=115;
    String a="1";
    public Person findingClientsinformation(String username) throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        String message="find the information";
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
        Object answer=objectInputStream.readObject();
        a="0";
        objectOutputStream.writeObject(a);
        objectOutputStream.flush();
        objectInputStream.close();
        objectOutputStream.close();
       // Person p = null;
        for (String s:(List<String>)answer){
            String[] info=s.split("#");
            String name=info[0];
            String country=info[1];
            String user=info[2];
            String pass=info[3];
            String picture=info[4];
            if(user.equals(username)){
            Person p=new Person(user,pass);
            p.setName(name);
            p.setCountry(country);
            p.setProfilePath(picture);
            return p;}
        }
        return null;
}}
