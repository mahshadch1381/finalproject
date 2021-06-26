package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class counts_of_reposts_client{
    public static int port=208;
    public String writer;
    public String title;
    public counts_of_reposts_client(String writer,String title){
        this.title=title;
        this.writer=writer;
    }
    public String numofreposts() throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        String line=writer+"#"+title;
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
}


