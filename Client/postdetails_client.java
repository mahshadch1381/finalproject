package Client;

import model.Person;
import model.Post;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static java.lang.Integer.parseInt;

public class postdetails_client {
    public static int port1=121;
    public static int port2=122;
    public String a="1";
    public Post post;
    public String username;
   public postdetails_client(){}
    public postdetails_client(Post p) {
        post=p;
    }
        public String detail_give_info() throws IOException, ClassNotFoundException {
            Socket socket=new Socket("127.0.0.1",port1);
            ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
            String message=post.publisher+"#"+post.title+"#"+post.description+"#"+post.date+"#"+post.picture;
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            Object answer=objectInputStream.readObject();
            String a="0";
            objectOutputStream.writeObject(a);
            objectOutputStream.flush();
            objectInputStream.close();
            objectOutputStream.close();
            return (String) answer;
        }
        public Post detail_get_info() throws IOException, ClassNotFoundException {
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
            Post p=new Post();
            p.setPublisher(info[0]);
            p.title=info[1];
            p.setDescription(info[2]);
            p.date=info[3];
            p.picture=info[4];
            return p;
        }
    }

