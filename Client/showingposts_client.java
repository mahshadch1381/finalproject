package Client;

import model.Person;
import model.Post;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class showingposts_client {
    public String username;
    public final int port=227;
    public String a= "1";
    public showingposts_client(String user){
        username=user;
    }

    public List<Post> posts() throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        String message=username+"#";
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
        Object answer=objectInputStream.readObject();
        a="0";
        objectOutputStream.writeObject(a);
        objectOutputStream.flush();
        objectInputStream.close();
        objectOutputStream.close();
        List<Post> posts=new Vector<>();
      for(String s:(List<String>)answer){
          String[] array=s.split("#");
          Post post=new Post();
          post.publisher=array[0];
          post.title=array[1];
          post.description=array[2];
          post.date=array[3];
          post.postPicture=array[4];
          post.profile=array[5];
          posts.add(post);
      }
      socket.close();
      return posts;
    }

    public String getUsername() {
        return username;
    }

}


