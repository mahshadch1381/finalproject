package Server;
import Client.signup_client;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class signupServer implements Runnable {
    public static final int port=112;
    public static AtomicInteger server_time=new AtomicInteger(0);
    public static ServerSocket serverSocket;
    public static String address="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\users.txt";
    public static String person_address="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\person.txt";
    public static String recovery_address="C:\\Users\\98912\\IdeaProjects\\HelloFX\\src\\files\\recoverypassword.txt";
    public void start() {
        try {
            if (server_time.get() == 1) {
                serverSocket.close();
                server_time.set(server_time.get()-1);
            }
            serverSocket=new ServerSocket(port);
            server_time.set(server_time.get()+1);

        }catch (IOException e){e.printStackTrace(); }
        new Thread(new signupServer()).start();
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
                            FileWriter fileWriter=new FileWriter(address,true);
                            FileReader fileReader=new FileReader(address);
                            String username="";
                            String pass="";
                            Scanner scanner=new Scanner(fileReader);
                              if(input.equals("0")){
                                  fileWriter.close();
                                  fileReader.close();
                                break; }
                              if(!input.contains("add")) {
                                  String[] check = input.split("#");
                                  int i = 0, j = 0;
                                  while (scanner.hasNextLine()) {
                                      i++;
                                      String line = scanner.nextLine();
                                      String[] user = line.split("#");
                                      if (user[0].equals(check[0])) {
                                          oos.writeObject("false");
                                          oos.flush();
                                          break;
                                      } else {
                                          j++;
                                      }
                                  }
                                  if (i == j) {
                                      fileWriter.write(input + "\n");
                                      fileWriter.flush();
                                      oos.writeObject("true");
                                      oos.flush();
                                      continue;
                                  }
                              }else {
                                  String[]array=input.split("%");
                                  input=array[0];
                                  String color=array[1];
                                  input=input.substring(4);
                                  FileWriter fileWriter2=new FileWriter(person_address,true);
                                  fileWriter2.write(input+"\n");
                                  fileWriter2.flush();
                                  String[] sr=input.split("#");
                                  username=sr[2];
                                  pass=sr[3];
                                  FileWriter fileWriter3=new FileWriter(recovery_address,true);
                                  String recovery=username+"#"+pass+"#"+color;
                                  fileWriter3.write(recovery+"\n");
                                  fileWriter3.flush();
                                  continue;
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


