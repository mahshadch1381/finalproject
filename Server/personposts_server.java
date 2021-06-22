package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class personposts_server implements Runnable{
    public static int port =134;
    public static AtomicInteger server_time=new AtomicInteger(0);
    public static ServerSocket serverSocket;
    public static String address="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\posts.txt";
    public void start() {
        try {
            if (server_time.get() == 1) {
                serverSocket.close();
                server_time.set(server_time.get()-1);
            }
            serverSocket=new ServerSocket(port);
            server_time.set(server_time.get()+1);

        }catch (IOException e){e.printStackTrace(); }
        new Thread( new personposts_server()).start();
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
                                FileReader fileReader=new FileReader(address);
                                Scanner scanner=new Scanner(fileReader);
                                if(input.equals("0")){
                                    fileReader.close();
                                    break; }
                                List<String> list =new ArrayList<>();
                               while (scanner.hasNextLine()){
                                   String line=scanner.nextLine();
                                   if(line.length()>0){
                                       String[] array=line.split("#");
                                       if(input.equals(array[0])){
                                           list.add(line);
                                       }
                                   }
                               }
                                oos.writeObject(list);
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



