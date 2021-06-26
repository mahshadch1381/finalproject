package Client;

import model.Post;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class postingClient {
    public static int port=225;
    public Post p;
    public String a= "1";
    public String mainuser;
    public postingClient(Post post,String user){
        p=post;
        mainuser=user;
    }
    public String posting_connection() throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        String message=mainuser+"%"+p.publisher+"#"+p.title+"#"+p.description+"#"+p.date+"#"+p.postPicture+"#"+p.profile;
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
