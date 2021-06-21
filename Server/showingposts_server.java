package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class showingposts_server implements Runnable{
        public static int port =125;
        public static AtomicInteger server_time=new AtomicInteger(0);
        public static ServerSocket serverSocket;
        public static String address="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\posts.txt";
       public static String address2="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\following.txt";
       public static String address3="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\reposts.txt";
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
                                    FileReader fileReader3=new FileReader(address3);
                                    Scanner scanner3=new Scanner(fileReader3);
                                    if(user.equals("0")){
                                        fileReader2.close();
                                        fileReader.close();
                                        fileReader3.close();
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
                                        String post=line;
                                        String[] array=line.split("#");
                                        if(array[0].equals(user)){
                                            posts.add(post);
                                            continue;
                                        }
                                        for (String s:list){
                                            if(s.equals(array[0])){
                                                posts.add(post);
                                                break;
                                            }
                                        }

                                    }
                                    while (scanner3.hasNextLine()){
                                        String line=scanner3.nextLine();
                                        String[] info=line.split("%");
                                        if(user.equals(info[0])){
                                            posts.add(info[1]);
                                            continue;
                                        }else {
                                            for (String s:list){
                                                if(s.equals(info[0])){
                                                    posts.add(info[1]);
                                                }
                                            }
                                        }
                                    }
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



