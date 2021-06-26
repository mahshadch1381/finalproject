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

public class deletingacount_server extends Thread {
    public  final int port=209;
    public static AtomicInteger server_time=new AtomicInteger(0);
    public  ServerSocket serverSocket;

     {
        try {
        //    serverSocket.close();
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String date;
    public static long time_date;
    public void setDateString(Date d) {
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy/ HH:mm:ss");
        date=formatter.format(d);
    }

    public void deleting_posts(String username) throws IOException {
         String address="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\allposts.txt";
         FileReader fileReader=new FileReader(address);
         Scanner scanner=new Scanner(fileReader);
         List<String> list=new ArrayList<>();
         while (scanner.hasNextLine()){
             String line=scanner.nextLine();
             if (line.length()>0){
                 String[] array=line.split("%");
                 String [] array2=array[1].split("#");
                 if(array[0].equals(username)||array2[0].equals(username)){
                     continue;
                 }
                 list.add(line);
             }
         }
         FileWriter fileWriter=new FileWriter(address);
         for (String s:list){
             fileWriter.write(s+"\n");
             fileWriter.flush();
         }
         fileWriter.close();
         fileReader.close();

        String address2="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\posts.txt";
        FileReader fileReader2=new FileReader(address2);
        Scanner scanner2=new Scanner(fileReader2);
        List<String> list2=new ArrayList<>();
        while (scanner2.hasNextLine()){
            String line=scanner2.nextLine();
            if (line.length()>0){
                String [] array2=line.split("#");
                if(array2[0].equals(username)){
                    continue;
                }
                list2.add(line);
            }
        }
        FileWriter fileWriter2=new FileWriter(address2);
        for (String s:list2){
            fileWriter2.write(s+"\n");
            fileWriter2.flush();
        }
        fileWriter2.close();
        fileReader2.close();

        String address3="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\reposts.txt";
        FileReader fileReader3=new FileReader(address3);
        Scanner scanner3=new Scanner(fileReader3);
        List<String> list3=new ArrayList<>();
        while (scanner3.hasNextLine()){
            String line=scanner3.nextLine();
            if (line.length()>0){
                String[] array3=line.split("%");
                String [] array4=array3[1].split("#");
                if(array3[0].equals(username)||array4[0].equals(username)){
                    continue;
                }
                list3.add(line);
            }
        }
        FileWriter fileWriter3=new FileWriter(address3);
        for (String s:list3){
            fileWriter3.write(s+"\n");
            fileWriter3.flush();
        }
        fileWriter3.close();
        fileReader3.close();

    }
    public void deleting_comments(String username) throws IOException {
        String address2="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\comments.txt";
        FileReader fileReader2=new FileReader(address2);
        Scanner scanner2=new Scanner(fileReader2);
        List<String> list2=new ArrayList<>();
        while (scanner2.hasNextLine()){
            String line=scanner2.nextLine();
            if (line.length()>0){
                String [] array2=line.split("#");
                if(array2[0].equals(username)){
                    continue;
                }
                list2.add(line);
            }
        }
        FileWriter fileWriter2=new FileWriter(address2);
        for (String s:list2){
            fileWriter2.write(s+"\n");
            fileWriter2.flush();
        }
        fileWriter2.close();
        fileReader2.close();

    }
    public void deleting_counters(String username) throws IOException {
        String address2="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\countoflikes.txt";
        FileReader fileReader2=new FileReader(address2);
        Scanner scanner2=new Scanner(fileReader2);
        List<String> list2=new ArrayList<>();
        while (scanner2.hasNextLine()){
            String line=scanner2.nextLine();
            if (line.length()>0){
                String [] array2=line.split("#");
                if(array2[0].equals(username)){
                    continue;
                }
                list2.add(line);
            }
        }
        FileWriter fileWriter2=new FileWriter(address2);
        for (String s:list2){
            fileWriter2.write(s+"\n");
            fileWriter2.flush();
        }
        fileWriter2.close();
        fileReader2.close();
        String address1="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\countofreposts.txt";
        FileReader fileReader1=new FileReader(address1);
        Scanner scanner1=new Scanner(fileReader1);
        List<String> list1=new ArrayList<>();
        while (scanner1.hasNextLine()){
            String line=scanner1.nextLine();
            if (line.length()>0){
                String [] array1=line.split("#");
                if(array1[0].equals(username)){
                    continue;
                }
                list1.add(line);
            }
        }
        FileWriter fileWriter1=new FileWriter(address1);
        for (String s:list1){
            fileWriter1.write(s+"\n");
            fileWriter1.flush();
        }
        fileWriter1.close();
        fileReader1.close();
    }
    public void deleting_of_person(String username) throws IOException {
        String address1="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\person.txt";
        FileReader fileReader1=new FileReader(address1);
        Scanner scanner1=new Scanner(fileReader1);
        List<String> list1=new ArrayList<>();
        while (scanner1.hasNextLine()){
            String line=scanner1.nextLine();
            if (line.length()>0){
                String [] array1=line.split("#");
                if(array1[2].equals(username)){
                    continue;
                }
                list1.add(line);
            }
        }
        FileWriter fileWriter1=new FileWriter(address1);
        for (String s:list1){
            fileWriter1.write(s+"\n");
            fileWriter1.flush();
        }
        fileWriter1.close();
        fileReader1.close();
    }
    public void deleting_users_recovery(String username) throws IOException {
        String address2="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\users.txt";
        FileReader fileReader2=new FileReader(address2);
        Scanner scanner2=new Scanner(fileReader2);
        List<String> list2=new ArrayList<>();
        while (scanner2.hasNextLine()){
            String line=scanner2.nextLine();
            if (line.length()>0){
                String [] array2=line.split("#");
                if(array2[0].equals(username)){
                    continue;
                }
                list2.add(line);
            }
        }
        FileWriter fileWriter2=new FileWriter(address2);
        for (String s:list2){
            fileWriter2.write(s+"\n");
            fileWriter2.flush();
        }
        fileWriter2.close();
        fileReader2.close();
        String address1="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\recoverypassword.txt";
        FileReader fileReader1=new FileReader(address1);
        Scanner scanner1=new Scanner(fileReader1);
        List<String> list1=new ArrayList<>();
        while (scanner1.hasNextLine()){
            String line=scanner1.nextLine();
            if (line.length()>0){
                String [] array1=line.split("#");
                if(array1[0].equals(username)){
                    continue;
                }
                list1.add(line);
            }
        }
        FileWriter fileWriter1=new FileWriter(address1);
        for (String s:list1){
            fileWriter1.write(s+"\n");
            fileWriter1.flush();
        }
        fileWriter1.close();
        fileReader1.close();
    }
    public void deleting_followers(String username) throws IOException {
        String address1="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\followers.txt";
        FileReader fileReader1=new FileReader(address1);
        Scanner scanner1=new Scanner(fileReader1);
       Map<String, Set<String>> map=new HashMap<>();
        while (scanner1.hasNextLine()){
            String line=scanner1.nextLine();
            if (line.length()>0){
                String [] array1=line.split(":");
                if(array1[0].equals(username)){
                    continue;
                }
                Set<String> info=new HashSet<>();
                String[] followers=array1[1].split("#");
                for (String s:followers){
                    if(s.equals(username)){
                        continue;
                    }
                    info.add(s);
                }
                map.put(array1[0],info);
            }
        }
        FileWriter fileWriter1=new FileWriter(address1);
        for (String s:map.keySet()){
            String line=s+":";
            for (String s2:map.get(s)){
                line=line+s2+"#";
            }
            line=line.substring(0,line.length()-1);
            fileWriter1.write(line+"\n");
            fileWriter1.flush();
        }
        fileWriter1.close();
        fileReader1.close();
    }
    public void deleting_followings(String username) throws IOException {
        String address1="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\following.txt";
        FileReader fileReader1=new FileReader(address1);
        Scanner scanner1=new Scanner(fileReader1);
        Map<String, Set<String>> map=new HashMap<>();
        while (scanner1.hasNextLine()){
            String line=scanner1.nextLine();
            if (line.length()>0){
                String [] array1=line.split(":");
                if(array1[0].equals(username)){
                    continue;
                }
                Set<String> info=new HashSet<>();
                String[] followers=array1[1].split("#");
                for (String s:followers){
                    if(s.equals(username)){
                        continue;
                    }
                    info.add(s);
                }
                map.put(array1[0],info);
            }
        }
        FileWriter fileWriter1=new FileWriter(address1);
        for (String s:map.keySet()){
            String line=s+":";
            for (String s2:map.get(s)){
                line=line+s2+"#";
            }
            line=line.substring(0,line.length()-1);
            fileWriter1.write(line+"\n");
            fileWriter1.flush();
        }
        fileWriter1.close();
        fileReader1.close();
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
        new Thread(new Server.deletingacount_server()).start();
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
                                if(input.equals("0")){
                                    break;
                                }
                                deleting_posts(input);
                                deleting_comments(input);
                                deleting_counters(input);
                                deleting_of_person(input);
                                deleting_users_recovery(input);
                                deleting_followers(input);
                                deleting_followings(input);
                                System.out.println(Controller.mainUser +" delete acount");
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
