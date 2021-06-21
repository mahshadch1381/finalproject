package Client;

import model.Post;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class likerepost_client {
        public static int port1=126;
        public static int port2=127;
        public String username;
        public Post post;
        public likerepost_client(Post post){
            this.post=post;
        }
        public likerepost_client(Post post,String user){
            this.post=post;
            username=user;
        }
        public String liking() throws IOException, ClassNotFoundException {
            Socket socket=new Socket("127.0.0.1",port1);
            ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
            String line=post.publisher+"#"+post.title;
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
        public String reposting() throws IOException, ClassNotFoundException {
            Socket socket=new Socket("127.0.0.1",port2);
            ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
            String message=username+"%"+post.publisher+"#"+post.title+"#"+post.description+"#"+post.date+"#"+post.picture;
            objectOutputStream.writeObject(message);
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


