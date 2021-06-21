package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

public class like_server implements Runnable{
    ////////////////////////////////////////////////////000000
    public static final int port=126;
    public static AtomicInteger server_time=new AtomicInteger(0);
    public static ServerSocket serverSocket;
    public static Map<String,String> map=new ConcurrentHashMap<>();
    public static String address="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\like_repost.txt";
    public void start() {
        try {
            if (server_time.get() == 1) {
                serverSocket.close();
                server_time.set(server_time.get()-1);
            }
            serverSocket=new ServerSocket(port);
            server_time.set(server_time.get()+1);

        }catch (IOException e){e.printStackTrace(); }
        new Thread(new Server.like_server()).start();
    }
    @Override
    public void run() {
        try {
            while (true) {
                Socket client = serverSocket.accept();
                OutputStream a = client.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(a);
                InputStream b = client.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(b);
                class My_thread extends Thread implements Runnable {
                    public void run() {
                        while (true) {
                            try {
                                Object object = ois.readObject();
                                String input = (String) object;
                                String[] array=input.split("#");
                               FileReader fileReader=new FileReader(address);
                               Scanner scanner=new Scanner(fileReader);
                                if(input.equals("0")){
                                    fileReader.close();
                                    break; }
                                List<String> posts=new ArrayList<>();
                                while (scanner.hasNextLine()){
                                    String line=scanner.nextLine();
                                    if(line.contains(array[0])&&line.contains(array[1])){
                                        String[] strings=line.split("#");
                                        int a=parseInt(strings[2]);
                                        int n=a+1;
                                        array[2]=n+"";
                                       String s=array[0]+"#"+array[1]+"#"+array[2]+"#"+array[3];
                                       posts.add(s);
                                       continue;
                                    }else {
                                        posts.add(line);
                                        continue;
                                    }
                                }
                                FileWriter fileWriter=new FileWriter(address);
                                for (String s:posts){
                                    fileWriter.write(s+"\n");
                                    fileWriter.flush();
                                }
                                fileWriter.close();
                                    oos.writeObject("ok");
                                    oos.flush();
                            } catch (ClassNotFoundException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } new My_thread().start();
                break;
            }serverSocket.close();
        }catch (IOException e){e.printStackTrace(); }
    }
}

