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

public class postdetail_server extends Thread{
    public  int port=223;
    public static AtomicInteger server_time=new AtomicInteger(0);
    public ServerSocket serverSocket;

     {
        try {
          //  serverSocket.close();
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String,String> map=new ConcurrentHashMap<>();
    public static String address="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\postdetails.txt";
  /*  public void start() {
        try {
            if (server_time.get() == 1) {
                serverSocket.close();
                server_time.set(server_time.get()-1);
            }
            serverSocket=new ServerSocket(port);
            server_time.set(server_time.get()+1);

        }catch (IOException e){e.printStackTrace(); }
        new Thread( new Server.postdetail_server()).start();
    }*/
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
                                FileWriter fileWriter=new FileWriter(address,true);
                                FileReader fileReader=new FileReader(address);
                                Scanner scanner=new Scanner(fileReader);
                                if(input.equals("0")){
                                    fileWriter.close();
                                    fileReader.close();
                                    break; }
                                if(scanner.hasNextLine()){
                                    fileWriter=new FileWriter(address);
                                    fileWriter.write(input+"\n");
                                    fileWriter.flush();
                                }
                                else {
                                fileWriter.write(input+"\n");
                                fileWriter.flush();}
                                oos.writeObject("ok");
                                oos.flush();
                            } catch (ClassNotFoundException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } new My_thread().start();

            }
        }catch (IOException e){e.printStackTrace(); }
    }

}

