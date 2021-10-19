package com.example;
import java.net.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class MultiServer {

    List<ServerThread> threadList = new ArrayList<>();
    ServerSocket serverSocket=null;

    public void start(){
        try{
            serverSocket = new ServerSocket(6789);
            for(;;){
                System.out.println("1 server in attesa ...");
                Socket socket = serverSocket.accept();
                System.out.println("3 server socket " + socket);
                ServerThread serverThread = new ServerThread(socket, this);
                serverThread.start();
                threadList.add(serverThread);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server !");
            System.exit(1);
        }
    }

    public void stop(){
        for(int i=0;i<threadList.size();i++){
            threadList.get(i).close();
        }

        try {
            serverSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}