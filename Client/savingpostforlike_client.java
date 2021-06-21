package Client;

import model.Post;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class savingpostforlike_client {
   /* public static int port=128;
    public String username;
    public Post post;
    public savingpostforlike_client(Post post){
        this.post=post;
    }
    public String liking() throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        String line=post.publisher+"#"+post.title+"#"+post.likes+"#"+post.reposts;
        objectOutputStream.writeObject(line);
        objectOutputStream.flush();
        Object answer=objectInputStream.readObject();
        String a="0";
        objectOutputStream.writeObject(a);
        objectOutputStream.flush();
        objectInputStream.close();
        objectOutputStream.close();
        return (String) answer;
    }*/
}
