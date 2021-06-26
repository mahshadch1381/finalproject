package Server;

import sample.Controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

public class repost_server extends Thread {
    ////////////////////////////////////////////////////000000
    public  final int port=217;
    public static AtomicInteger server_time=new AtomicInteger(0);
    public  ServerSocket serverSocket;

    {
        try {
          //  serverSocket.close();
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String address="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\reposts.txt";
    public static String address22="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\countofreposts.txt";
    public static String address3="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\allposts.txt";
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
        new Thread(new Server.repost_server()).start();
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
                                FileWriter fileWriter3=new FileWriter(address3,true);
                                if(input.equals("0")){
                                    fileWriter.close();
                                    fileWriter3.close();
                                    break;
                                }
                                fileWriter.write(input+"\n");
                                fileWriter.flush();
                                fileWriter3.write(input+"\n");
                                fileWriter3.flush();
                                String[] s=input.split("%");
                                String user=s[0];
                                String[] info=s[1].split("#");
                                FileReader fileReader2=new FileReader(address22);
                                Scanner scanner=new Scanner(fileReader2);
                                List<String> posts=new Vector<>();
                                while (scanner.hasNextLine()){
                                    String line=scanner.nextLine();
                                    String[] strings=line.split("#");
                                    if(strings[0].equals(info[0])&&strings[1].equals(info[1])){
                                        int a=parseInt(strings[2]);
                                        int n=a+1;
                                        String c=n+"";
                                        String s2=info[0]+"#"+info[1]+"#"+c;
                                        posts.add(s2);
                                        continue;
                                    }else {
                                        posts.add(line);
                                        continue;
                                    }
                                }
                                FileWriter fileWriter2=new FileWriter(address22);
                                for (String s1:posts){
                                    fileWriter2.write(s1+"\n");
                                    fileWriter2.flush();
                                }
                                System.out.println(Controller.mainUser +" repost ");
                                setDateString(new Date());
                                time_date= Instant.now().toEpochMilli();
                                System.out.println("time:"+date);
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


