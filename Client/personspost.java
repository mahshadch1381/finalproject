package Client;

import model.Post;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class personspost {
    public int port=134;
    public String username;
    public String a= "1";
    public personspost(String user){
        username=user;

    }
    public List<Post> posts() throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        String message=username;
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
        Object answer=objectInputStream.readObject();
        a="0";
        objectOutputStream.writeObject(a);
        objectOutputStream.flush();
        objectInputStream.close();
        objectOutputStream.close();
        List<Post> posts=new ArrayList<>();
        for (String s:(List<String> )answer){
            String[] array=s.split("#");
            Post p=new Post();
            p.publisher=array[0];
            p.title=array[1];
            p.description=array[2];
            p.date=array[3];
            p.picture=array[4];
            posts.add(p);
        }
        return posts;
    }

    public String getUsername() {
        return username;
    }

}


