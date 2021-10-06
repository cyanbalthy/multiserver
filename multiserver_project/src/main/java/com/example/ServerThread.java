package com.example;
import java.net.*;
import java.io.*;

public class ServerThread {
    ServerSocket server = null;
    Socket client = null;
    String stringRicevuta = null;
    String stringmodificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public ServerThread(Socket socket){
        this.client = socket;
    }
    
    public void run(){
        try{
            comunica();
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
    }

    public void comunica() throws Exception{
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());
        for(;;){
            stringRicevuta = inDalClient.readLine();
            if(stringRicevuta)
        }
    }
}
