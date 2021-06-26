package Client;

import model.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class edit_client {
    public int port=210;
    public String line;
    public edit_client(String line){
        this.line=line;
    }
    public String editing() throws IOException, ClassNotFoundException {
        Socket socket=new Socket("127.0.0.1",port);
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        String [] seprate=line.split("%");
        String user=seprate[0];
        line=seprate[1];
        String[] info=line.split("#");
        if(info.length==0){
            return "no";
        }
        String a="-";
        if(line.contains("name")){
            String[] a1=info[0].split(":");
            a=a1[1];
        }
        String b="-";
        if (line.contains("country")){
            String[] a1=info[1].split(":");
            b=a1[1];
        }
        String c="-";
        if (line.contains("picture")){
            String[] a1=info[2].split(":");
            c=a1[1];
        }
        List<String> list=new Vector<>();
        list.add(user);
        list.add(a);
        list.add(b);
        list.add(c);
        String x=user+"#"+a+"#"+b+"#"+c;
        objectOutputStream.writeObject(x);
        objectOutputStream.flush();
        Object answer=objectInputStream.readObject();
        String a5="0";
        objectOutputStream.writeObject(a5);
        objectOutputStream.flush();
        objectInputStream.close();
        objectOutputStream.close();
        return (String) answer;
    }
}
