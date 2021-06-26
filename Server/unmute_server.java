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

public class unmute_server extends Thread{
    public int port=230;
    public static AtomicInteger server_time=new AtomicInteger(0);
    public  ServerSocket serverSocket;

     {
        try {
         //   serverSocket.close();
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String address="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\mute.txt";
    public static String date;
    public static long time_date;
    public void setDateString(Date d) {
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy/ HH:mm:ss");
        date=formatter.format(d);
    }
    /*public void start() {
        try {
            if (server_time.get() == 1) {
                serverSocket.close();
                server_time.set(server_time.get()-1);
            }
            serverSocket=new ServerSocket(port);
            server_time.set(server_time.get()+1);

        }catch (IOException e){e.printStackTrace(); }
        new Thread(new Server.unmute_server()).start();
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
                                String[] people=input.split("#");
                                FileReader fileReader=new FileReader(address);
                                Scanner scanner=new Scanner(fileReader);
                                if(input.equals("0")){
                                    fileReader.close();
                                    break; }
                                Map<String, Set<String>> map1=new ConcurrentHashMap<>();
                                while (scanner.hasNextLine()) {
                                    String a = scanner.nextLine();
                                    if (a.length() > 0 && a.contains(":")) {
                                        String user = a.substring(0, a.indexOf(":"));
                                        if (user.equals(people[0])){
                                            a = a.substring(a.indexOf(":") + 1);
                                            String[] array = a.split("#");
                                            Set<String> list1 = new HashSet<>();
                                            for (String s : array) {
                                                if(!s.equals(people[1])){
                                                list1.add(s);}
                                            }
                                            map1.put(user, list1);
                                        }
                                        else {
                                        a = a.substring(a.indexOf(":") + 1);
                                        String[] array = a.split("#");
                                        Set<String> list1 = new HashSet<>();
                                        for (String s : array) {
                                            list1.add(s);
                                        }
                                        map1.put(user, list1);}
                                    }
                                }
                                if(map1.size()==0||!map1.containsKey(people[0])){
                                    oos.writeObject("no");
                                    oos.flush();
                                }
                                FileWriter fileWriter1=new FileWriter(address);
                                for (String s:map1.keySet()){
                                    String line=s+":";
                                    if(map1.get(s).size()==0){
                                        continue;
                                    }
                                    for (String s2:map1.get(s)){
                                        line=line+s2+"#";
                                    }
                                    line=line.substring(0,line.length()-1);
                                    fileWriter1.write(line+"\n");
                                    fileWriter1.flush();
                                }
                                fileWriter1.close();
                                System.out.println(Controller.mainUser +" unmute "+people[1]);
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




