package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class followingserver extends Thread{
    public  int port1=212;
    public static AtomicInteger server_time=new AtomicInteger(0);
    public ServerSocket serverSocket;

    {
        try {
          //  serverSocket.close();
            serverSocket = new ServerSocket(port1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String address1="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\followers.txt";
    public static String address2="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\following.txt";
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
            serverSocket=new ServerSocket(port1);
            server_time.set(server_time.get()+1);

        }catch (IOException e){e.printStackTrace(); }
        new Thread( new Server.followingserver()).start();
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
                                FileReader fileReader2=new FileReader(address2);
                                Scanner scanner2=new Scanner(fileReader2);
                                if(input.equals("0")){
                                    fileReader1.close();
                                    fileReader2.close();
                                    break; }
                                if(input.contains("follow")){
                                    Map<String,Set<String>> map1=new ConcurrentHashMap<>();
                                    String[] people=input.substring(input.indexOf("-")+1).split("#");
                                    while (scanner1.hasNextLine()){
                                        String a=scanner1.nextLine();
                                        String user=a.substring(0,a.indexOf(":"));
                                        a=a.substring(a.indexOf(":")+1);
                                        String[] array=a.split("#");
                                        Set<String> list1=new HashSet<>();
                                        for (String s:array){
                                            list1.add(s);
                                        }
                                        if(user.equals(people[0])){
                                            list1.add(people[1]);
                                        }
                                        map1.put(user,list1);
                                    }
                                    if(map1.size()==0||!map1.containsKey(people[0])){
                                       Set<String> list2=new HashSet<>();
                                        list2.add(people[1]);
                                        map1.put(people[0],list2);
                                    }
                                    FileWriter fileWriter1=new FileWriter(address1);
                                    for (String s:map1.keySet()){
                                        String line=s+":";
                                        for (String s2:map1.get(s)){
                                            line=line+s2+"#";
                                        }
                                        line=line.substring(0,line.length()-1);
                                        fileWriter1.write(line+"\n");
                                        fileWriter1.flush();
                                    }
                                    fileWriter1.close();
                                    Map<String,Set<String>> map2=new ConcurrentHashMap<>();
                                    while (scanner2.hasNextLine()){
                                        String a=scanner2.nextLine();
                                        if(!a.contains(":")){
                                            continue;
                                        }
                                        String user=a.substring(0,a.indexOf(":"));
                                        a=a.substring(a.indexOf(":")+1);
                                        String[] array2=a.split("#");
                                        Set<String> list2=new HashSet<>();
                                        for (String s:array2){
                                            list2.add(s);
                                        }
                                        if(user.equals(people[1])){
                                            list2.add(people[0]);
                                        }
                                        map2.put(user,list2);
                                    }
                                    if(map2.size()==0||!map2.containsKey(people[1])){
                                        Set<String> list3=new HashSet<>();
                                        list3.add(people[0]);
                                        map2.put(people[1],list3);
                                    }
                                    FileWriter fileWriter2=new FileWriter(address2);
                                    for (String s:map2.keySet()){
                                        String line=s+":";
                                        for (String s2:map2.get(s)){
                                            line=line+s2+"#";
                                        }
                                        line=line.substring(0,line.length()-1);
                                        fileWriter2.write(line+"\n");
                                        fileWriter2.flush();
                                    }
                                    System.out.println(people[1]+" follow");
                                    setDateString(new Date());
                                    time_date= Instant.now().toEpochMilli();
                                    System.out.println("time:"+date);
                                    oos.writeObject("ok");
                                    oos.flush();
                                    fileWriter2.close();
                                }

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



