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

public class edit_server extends Thread{
    public  int port=210;
    public static AtomicInteger server_time=new AtomicInteger(0);
    public  ServerSocket serverSocket;

     {
        try {
       //     serverSocket.close();
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String,String> map=new ConcurrentHashMap<>();
    public static String address="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\person.txt";
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
        new Thread( new Server.edit_server()).start();
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
                                //FileWriter fileWriter=new FileWriter(address,true);
                                FileReader fileReader=new FileReader(address);
                                Scanner scanner=new Scanner(fileReader);
                                if(input.equals("0")){
                                    //fileWriter.close();
                                    fileReader.close();
                                    break; }
                                String profile=null;
                                String[] r=input.split("#");
                                List<String> list=new ArrayList<>();
                                while (scanner.hasNextLine()){
                                    String a=scanner.nextLine();
                                    if(a.length()>0){
                                    String[] info=a.split("#");
                                    if(r[0].equals(info[2])){
                                        if(!r[1].equals("-")){
                                            info[0]=r[1];
                                        }if(!r[2].equals("-")){
                                            info[1]=r[2];
                                        }if(!r[3].equals("-")){
                                            info[4]=r[3];
                                        }
                                        profile=info[4];
                                        String st=info[0]+"#"+info[1]+"#"+info[2]+"#"+info[3]+"#"+info[4];
                                        list.add(st);
                                        continue;
                                    }else {
                                    list.add(a);
                                    continue;}
                                    }
                                    continue;
                                }
                                FileWriter fileWriter=new FileWriter(address);
                                for (String s:list){
                                    fileWriter.write(s+"\n");
                                    fileWriter.flush();
                                }
                                  fileWriter.close();
                                System.out.println(Controller.mainUser +" update info");
                                System.out.println("message: new profile:" +profile);
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



