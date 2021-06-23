package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class postingServer implements Runnable{
    public static int port =113;
    public static AtomicInteger server_time=new AtomicInteger(0);
    public static ServerSocket serverSocket;
    public static Map<String,String> map=new ConcurrentHashMap<>();
    public static String address="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\posts.txt";
    public static String address2="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\countoflikes.txt";
    public static String address3="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\countofreposts.txt";
    public static String address4="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\allposts.txt";
    public void start() {
        try {
            if (server_time.get() == 1) {
                serverSocket.close();
                server_time.set(server_time.get()-1);
            }
            serverSocket=new ServerSocket(port);
            server_time.set(server_time.get()+1);

        }catch (IOException e){e.printStackTrace(); }
        new Thread( new postingServer()).start();
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
                                FileWriter fileWriter=new FileWriter(address,true);
                                FileReader fileReader=new FileReader(address);
                                Scanner scanner=new Scanner(fileReader);
                                FileWriter fileWriter2=new FileWriter(address2,true);
                                FileWriter fileWriter3=new FileWriter(address3,true);
                                FileWriter fileWriter4=new FileWriter(address4,true);
                                if(input.equals("0")){
                                    fileWriter4.close();
                                    fileWriter.close();
                                    fileWriter3.close();
                                    fileWriter2.close();
                                    fileReader.close();
                                    break; }
                                String a=input;
                                String[] array1=input.split("%");
                                input=array1[1];
                                fileWriter.write(input + "\n");
                                fileWriter.flush();
                                fileWriter4.write(a + "\n");
                                fileWriter4.flush();
                                String[] array=input.split("#");
                                String result=array[0]+"#"+array[1]+"#"+"0";
                                fileWriter2.write(result+"\n");
                                fileWriter2.flush();
                                fileWriter3.write(result+"\n");
                                fileWriter3.flush();
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



