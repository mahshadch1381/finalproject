package Server;

import sample.Controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class loginServer extends Thread{
        public  final int port=218;
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

    public static Map<String,String> map=new ConcurrentHashMap<>();
        public static String address="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\users.txt";
        public static String date;
        public static long time_date;
       public void setDateString(Date d) {
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy/ HH:mm:ss");
        date=formatter.format(d);
    }
       /* public void start() {
           try {
                if (server_time.get() == 1) {
                    serverSocket.close();
                    server_time.set(server_time.get()-1);
                }
                serverSocket=new ServerSocket(port);
                server_time.set(server_time.get()+1);

            }catch (IOException e){e.printStackTrace(); }
            new Thread(new Server.loginServer()).start();
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
                                    String username="";
                                    if(input.equals("0")){
                                       // System.out.println(Controller.mainUser +" login");
                                        break; }
                                    Scanner scanner=new Scanner(fileReader);
                                    AtomicInteger i=new AtomicInteger(0);
                                    AtomicInteger j=new AtomicInteger(0);
                                    while (scanner.hasNextLine()){
                                        i.set(i.get()+1);
                                        String line=scanner.nextLine();
                                        if(line.contains(input)){
                                            String[] array=line.split("#");
                                            username=array[0];
                                            System.out.println(username +" login");
                                            setDateString(new Date());
                                            time_date= Instant.now().toEpochMilli();
                                            System.out.println("time:"+date);
                                            oos.writeObject("true");
                                            oos.flush();
                                            break;
                                        }else {
                                            j.set(j.get()+1);
                                        }
                                    }if(i.get()==j.get()){
                                        oos.writeObject("false");
                                        oos.flush();}
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

