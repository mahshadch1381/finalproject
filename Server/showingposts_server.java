package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class showingposts_server implements Runnable{
        public static int port =125;
        public static AtomicInteger server_time=new AtomicInteger(0);
        public static ServerSocket serverSocket;
        public static String address="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\allposts.txt";
       public static String address2="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\following.txt";
    public static String date;
    public static long time_date;
    public void setDateString(Date d) {
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy/ HH:mm:ss");
        date=formatter.format(d);
    }
        public void start() {
            try {
                if (server_time.get() == 1) {
                    serverSocket.close();
                    server_time.set(server_time.get()-1);
                }
                serverSocket=new ServerSocket(port);
                server_time.set(server_time.get()+1);

            }catch (IOException e){e.printStackTrace(); }
            new Thread( new Server.showingposts_server()).start();
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
                                    String user = (String) object;
                                    FileReader fileReader=new FileReader(address);
                                    Scanner scanner=new Scanner(fileReader);
                                    FileReader fileReader2=new FileReader(address2);
                                    Scanner scanner2=new Scanner(fileReader2);
                                    if(user.equals("0")){
                                        fileReader2.close();
                                        fileReader.close();
                                        break; }
                                    user=user.substring(0,user.length()-1);
                                    Set<String> list=new HashSet<>();
                                    while (scanner2.hasNextLine()){
                                        String line=scanner2.nextLine();
                                        String client=line.substring(0,line.indexOf(":"));
                                        if(client.equals(user)){
                                            line=line.substring(line.indexOf(":")+1);
                                            String [] strings=line.split("#");
                                            for (String s:strings){
                                                list.add(s);
                                            }
                                            break;}
                                        else {
                                            continue; }
                                    }
                                    List<String> posts=new ArrayList<>();
                                    while (scanner.hasNextLine()){
                                        String line=scanner.nextLine();
                                        String[ ]parts=line.split("%");
                                       if(list.contains(parts[0])){
                                           posts.add(parts[1]);
                                           continue;
                                       }
                                       if(parts[0].equals(user)){
                                           posts.add(parts[1]);
                                           continue;
                                       }
                                    }
                                    System.out.println(user+" get posts list");
                                    setDateString(new Date());
                                    time_date= Instant.now().toEpochMilli();
                                    System.out.println("time:"+date);
                                    oos.writeObject(posts);
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



