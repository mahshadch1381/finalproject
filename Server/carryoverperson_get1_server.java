package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class carryoverperson_get1_server implements Runnable {
    public int port=132;
    public static AtomicInteger server_time=new AtomicInteger(0);
    public static ServerSocket serverSocket;
    public static Map<String,String> map=new ConcurrentHashMap<>();
    public static String address="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\newfiletocarryoverperson.txt";
    public static String address22="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\person.txt";
    public void start() {
        try {
            if (server_time.get() == 1) {
                serverSocket.close();
                server_time.set(server_time.get()-1);
            }
            serverSocket=new ServerSocket(port);
            server_time.set(server_time.get()+1);

        }catch (IOException e){e.printStackTrace(); }
        new Thread( new Server.carryoverperson_get1_server()).start();
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
                                    //fileWriter.close();
                                    fileReader.close();
                                    break; }
                                String line=scanner.nextLine();
                                FileWriter fileWriter=new FileWriter(address);
                                FileReader fileReader2=new FileReader(address22);
                                Scanner scanner2=new Scanner(fileReader2);
                                String result=null;
                                while (scanner2.hasNextLine()){
                                    String s=scanner2.nextLine();
                                    String[] array=s.split("#");
                                    if(line.equals(array[2])){
                                        result=s;
                                        break;
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

