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

public class mute_server extends Thread{
    public  int port=219;
    public static AtomicInteger server_time=new AtomicInteger(0);
    public ServerSocket serverSocket;

     {
        try {
          //  if (serverSocket!=null){
            //    serverSocket.close();
            //}
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
        new Thread(new Server.mute_server()).start();
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
                                        a = a.substring(a.indexOf(":") + 1);
                                        String[] array = a.split("#");
                                        Set<String> list1 = new HashSet<>();
                                        for (String s : array) {
                                            list1.add(s);
                                        }
                                        if (user.equals(people[0])) {
                                            list1.add(people[1]);
                                        }
                                        map1.put(user, list1);
                                    }
                                }
                                if(map1.size()==0||!map1.containsKey(people[0])){
                                    Set<String> list2=new HashSet<>();
                                    list2.add(people[1]);
                                    map1.put(people[0],list2);
                                }
                                FileWriter fileWriter1=new FileWriter(address);
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
                                System.out.println(Controller.mainUser +" mute "+people[1]);
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
















