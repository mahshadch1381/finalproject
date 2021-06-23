package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class counts_of_reposts implements Runnable{
    public static int port1=145;
    public static AtomicInteger server_time=new AtomicInteger(0);
    public static ServerSocket serverSocket;
    public static String address1="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\countofreposts.txt";
    public void start() {
        try {
            if (server_time.get() == 1) {
                serverSocket.close();
                server_time.set(server_time.get()-1);
            }
            serverSocket=new ServerSocket(port1);
            server_time.set(server_time.get()+1);

        }catch (IOException e){e.printStackTrace(); }
        new Thread( new Server.counts_of_reposts ()).start();
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
                                FileReader fileReader1=new FileReader(address1);
                                Scanner scanner1=new Scanner(fileReader1);
                                if(input.equals("0")){
                                    fileReader1.close();
                                    break; }
                                String result="";
                                while (scanner1.hasNextLine()) {
                                    String line = scanner1.nextLine();
                                    if(line.contains(input)){
                                        String[] array=line.split("#");
                                        result = array[2];
                                    }
                                }
                                oos.writeObject(result);
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




