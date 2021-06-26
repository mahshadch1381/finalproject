package Client;

import sample.Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class comments_client {
    public String username;
    public String comment;
    public final int port1=203;
    public final int port2=204;
    public String a= "1";
    public String postTitle;
    public comments_client(String user,String comment,String title){
        username=user;
        this.comment=comment;
        postTitle=title;
    }
    public comments_client(String title) {
    postTitle=title;
    }
    public String Saving_comments() throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port1);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        String message=username+"#"+postTitle+"#"+comment;
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
  public List<String> Reading_comments() throws IOException, ClassNotFoundException {
    Socket socket=new Socket("127.0.0.1",port2);
    ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
    ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
    String message=postTitle;
    objectOutputStream.writeObject(message);
    objectOutputStream.flush();
    Object answer=objectInputStream.readObject();
    a="0";
    objectOutputStream.writeObject(a);
    objectOutputStream.flush();
    objectInputStream.close();
    objectOutputStream.close();
    List<String> list=new Vector<>();
    for (String s:(List<String>) answer){
        String[] array=s.split("#");
        String co="username: "+ array[0]+"  //   comment: "+array[2];
        list.add(co);
    }
    return list;
}

    public String getUsername() {
        return username;
    }
}


