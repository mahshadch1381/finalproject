package Client;
import Server.signupServer;
import model.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class signup_client {
    public String username;
    public String password;
    public final int port=112;
    public String a= "1";
    public signup_client(String user,String pass){
        username=user;
        password=pass;
    }
    public String signup_connection(Person person) throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1" ,port);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        String message=username+"#"+password;
         objectOutputStream.writeObject(message);
         objectOutputStream.flush();
         Object answer=objectInputStream.readObject();
         if (((String) answer).equals("false")){
          a="0";
           objectOutputStream.writeObject(a);
           objectOutputStream.flush();
           objectInputStream.close();
           objectOutputStream.close();
           socket.close();
             return (String) answer;
         }else {
             String information="add"+"#"+person.name+"#"+person.country+"#"+person.username+"#"+person.password+"#"+person.profilePath+"%"+person.accidentalQuestion;
             objectOutputStream.writeObject(information);
             objectOutputStream.flush();
                 a="0";
                 objectOutputStream.writeObject(a);
                 objectOutputStream.flush();
                 objectInputStream.close();
                 objectOutputStream.close();
                 socket.close();
                 return (String) answer;
         }

    }
}
