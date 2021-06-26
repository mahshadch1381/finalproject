package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class recoverypass_server extends Thread{
    public  int port=226;
    public static AtomicInteger server_time=new AtomicInteger(0);
    public  ServerSocket serverSocket;

    {
        try {
            //serverSocket.close();
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String address="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\recoverypassword.txt";
  /*  public void start() {
        try {
            if (server_time.get() == 1) {
                serverSocket.close();
                server_time.set(server_time.get()-1);
            }
            serverSocket=new ServerSocket(port);
            server_time.set(server_time.get()+1);

        }catch (IOException e){e.printStackTrace(); }
        new Thread( new Server.recoverypass_server()).start();
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
                                FileReader fileReader=new FileReader(address);
                                Scanner scanner=new Scanner(fileReader);
                                if(input.equals("0")){
                                    //fileWriter.close();
                                    fileReader.close();
                                    break; }
                                String[]check=input.split("#");
                                String password="";
                               while (scanner.hasNextLine()){
                                   String line=scanner.nextLine();
                                   if(line.length()>0){
                                       String[] array=line.split("#");
                                       if(array[0].equals(check[0])&&array[2].equals(check[1])){
                                           password=array[1];
                                           break;
                                       }
                                   }
                               }
                               oos.writeObject(password);
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
