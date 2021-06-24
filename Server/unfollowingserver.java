package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class unfollowingserver implements Runnable{
    public static int port1=120;
    public static AtomicInteger server_time=new AtomicInteger(0);
    public static ServerSocket serverSocket;
    public static String address1="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\followers.txt";
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
            serverSocket=new ServerSocket(port1);
            server_time.set(server_time.get()+1);

        }catch (IOException e){e.printStackTrace(); }
        new Thread( new Server.unfollowingserver()).start();
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
                                FileReader fileReader3=new FileReader(address1);
                                Scanner scanner3=new Scanner(fileReader3);
                                FileReader fileReader4=new FileReader(address2);
                                Scanner scanner4=new Scanner(fileReader4);
                                if(input.equals("0")){
                                    fileReader3.close();
                                    fileReader4.close();
                                    break; }
                                if (input.contains("unfollow")){
                                    Map<String, Set<String>> map3=new HashMap<>();
                                    String[] people=input.substring(input.indexOf("-")+1).split("#");
                                    while (scanner3.hasNextLine()){
                                        String a=scanner3.nextLine();
                                        String user=a.substring(0,a.indexOf(":"));
                                        a=a.substring(a.indexOf(":")+1);
                                        String[] array=a.split("#");
                                        Set<String> list1=new HashSet<>();
                                        if(user.equals(people[0])) {
                                            for (String s : array) {
                                                if(!s.equals(people[1])){
                                                    list1.add(s);}
                                            }
                                        }else {
                                            for (String s : array) {
                                                list1.add(s);}
                                        }
                                        map3.put(user,list1);
                                    }
                                    FileWriter fileWriter3=new FileWriter(address1);
                                    for (String s:map3.keySet()){
                                        if(map3.get(s).size()==0){
                                            continue;
                                        }
                                        String line=s+":";
                                        for (String s2:map3.get(s)){
                                            line=line+s2+"#";
                                        }
                                        line=line.substring(0,line.length()-1);
                                        fileWriter3.write(line+"\n");
                                        fileWriter3.flush();
                                    }
                                    fileWriter3.close();

                                    Map<String,List<String>> map4=new HashMap<>();
                                    while (scanner4.hasNextLine()){
                                        String a=scanner4.nextLine();
                                        String user=a.substring(0,a.indexOf(":"));
                                        a=a.substring(a.indexOf(":")+1);
                                        String[] array2=a.split("#");
                                        List<String> list2=new ArrayList<>();
                                        if(user.equals(people[1])) {
                                            for (String s : array2) {
                                                if(!s.equals(people[0])){
                                                    list2.add(s);}
                                            }
                                        }else {
                                            for (String s : array2) {
                                                list2.add(s);}
                                        }
                                        map4.put(user,list2);
                                    }
                                    FileWriter fileWriter4=new FileWriter(address2);
                                    for (String s:map4.keySet()){
                                        if(map4.get(s).size()==0){
                                            continue;
                                        }
                                        String line=s+":";
                                        for (String s2:map4.get(s)){
                                            line=line+s2+"#";
                                        }
                                        line=line.substring(0,line.length()-1);
                                        fileWriter4.write(line+"\n");
                                        fileWriter4.flush();
                                    }
                                    System.out.println(people[1]+" unfollow");
                                    setDateString(new Date());
                                    time_date= Instant.now().toEpochMilli();
                                    System.out.println("time:"+date);
                                    oos.writeObject("ok");
                                    oos.flush();
                                    fileWriter4.close();

                                }

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



