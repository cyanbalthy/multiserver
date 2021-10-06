package com.example;
import java.net.*;
import java.io.*;

public class MultiServer {
    public void start(){
        try{
            ServerSocket serverSocket = new ServerSocket(6789);
            for(;;){
                System.out.println("1 server in attesa ...");
                Socket socket = serverSocket.accept();
                System.out.println("3 server socket " + socket);
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server !");
            System.exit(1);
        }
    }

}
