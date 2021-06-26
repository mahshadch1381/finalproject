package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class count_of_followers_server extends Thread {
    public  int port1=206;
    public static AtomicInteger server_time=new AtomicInteger(0);
    public  ServerSocket serverSocket;

     {
        try {
          //  serverSocket.close();
            serverSocket = new ServerSocket(port1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String address1="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\followers.txt";
  /*  public void start() {
        try {
            if (server_time.get() == 1) {
                serverSocket.close();
                server_time.set(server_time.get()-1);
            }
            serverSocket=new ServerSocket(port1);
            server_time.set(server_time.get()+1);

        }catch (IOException e){e.printStackTrace(); }
        new Thread( new Server.count_of_followers_server()).start();
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
                                FileReader fileReader1=new FileReader(address1);
                                Scanner scanner1=new Scanner(fileReader1);
                                if(input.equals("0")){
                                    fileReader1.close();
                                    break; }
                                AtomicInteger n=new AtomicInteger(0);
                                while (scanner1.hasNextLine()) {
                                    String line = scanner1.nextLine();
                                    String user = line.substring(0, line.indexOf(":"));
                                    if (input.equals(user)) {
                                      line=line.substring(line.indexOf(":")+1);
                                      String[] followers=line.split("#");
                                     n.set(followers.length);
                                      break;
                                    } else {
                                        continue;
                                    }
                                }
                                String result=n.get()+"";
                                oos.writeObject(result);
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



