package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class followingserver implements Runnable{
    public static int port1=119;
    public static AtomicInteger server_time=new AtomicInteger(0);
    public static ServerSocket serverSocket;
    public static String address1="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\followers.txt";
    public static String address2="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\following.txt";
    public void start() {
        try {
            if (server_time.get() == 1) {
                serverSocket.close();
                server_time.set(server_time.get()-1);
            }
            serverSocket=new ServerSocket(port1);
            server_time.set(server_time.get()+1);

        }catch (IOException e){e.printStackTrace(); }
        new Thread( new Server.followingserver()).start();
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
                                FileReader fileReader1=new FileReader(address1);
                                Scanner scanner1=new Scanner(fileReader1);
                                FileReader fileReader2=new FileReader(address2);
                                Scanner scanner2=new Scanner(fileReader2);
                                if(input.equals("0")){
                                    fileReader1.close();
                                    fileReader2.close();
                                    break; }
                                if(input.contains("follow")){
                                    Map<String,List<String>> map1=new HashMap<>();
                                    String[] people=input.substring(input.indexOf("-")+1).split("#");
                                    while (scanner1.hasNextLine()){
                                        String a=scanner1.nextLine();
                                        String user=a.substring(0,a.indexOf(":"));
                                        a=a.substring(a.indexOf(":")+1);
                                        String[] array=a.split("#");
                                        List<String> list1=new ArrayList<>();
                                        for (String s:array){
                                            list1.add(s);
                                        }
                                        if(user.equals(people[0])){
                                            list1.add(people[1]);
                                        }
                                        map1.put(user,list1);
                                    }
                                    if(map1.size()==0||!map1.containsKey(people[0])){
                                        List<String> list2=new ArrayList<>();
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
                                    Map<String,List<String>> map2=new HashMap<>();
                                    while (scanner2.hasNextLine()){
                                        String a=scanner2.nextLine();
                                        String user=a.substring(0,a.indexOf(":"));
                                        a=a.substring(a.indexOf(":")+1);
                                        String[] array2=a.split("#");
                                        List<String> list2=new ArrayList<>();
                                        for (String s:array2){
                                            list2.add(s);
                                        }
                                        if(user.equals(people[1])){
                                            list2.add(people[0]);
                                        }
                                        map2.put(user,list2);
                                    }
                                    if(map2.size()==0||!map2.containsKey(people[1])){
                                        List<String> list3=new ArrayList<>();
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
                                    oos.writeObject("ok");
                                    oos.flush();
                                    fileWriter2.close();
                                }
                               /* if (input.contains("unfollow")){
                                    FileReader fileReader3=new FileReader(address1);
                                    Scanner scanner3=new Scanner(fileReader3);
                                    Map<String,List<String>> map3=new HashMap<>();
                                    String[] people=input.substring(input.indexOf("-")+1).split("#");
                                    while (scanner3.hasNextLine()){
                                        String a=scanner3.nextLine();
                                        String user=a.substring(0,a.indexOf(":"));
                                        a=a.substring(a.indexOf(":")+1);
                                        String[] array=a.split("#");
                                        List<String> list1=new ArrayList<>();
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
                                        String line=s+":";
                                        for (String s2:map3.get(s)){
                                            line=line+s2+"#";
                                        }
                                        line=line.substring(0,line.length()-1);
                                        fileWriter3.write(line+"\n");
                                        fileWriter3.flush();
                                    }
                                    fileReader3.close();
                                    fileWriter3.close();

                                        FileReader fileReader4=new FileReader(address2);
                                        Scanner scanner4=new Scanner(fileReader4);
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
                                            String line=s+":";
                                            for (String s2:map4.get(s)){
                                                line=line+s2+"#";
                                            }
                                            line=line.substring(0,line.length()-1);
                                            fileWriter4.write(line+"\n");
                                            fileWriter4.flush();
                                        }
                                    oos.writeObject("ok");
                                    oos.flush();
                                    fileWriter4.close();
                                    fileWriter4.close();
                                    }*/
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



