package com.example;
import java.net.*;
import java.io.*;

public class ServerThread extends Thread{
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
            if(stringRicevuta == null){
                outVersoClient.writeBytes(stringRicevuta+" {=>server in chiusura...}"+ '\n');
                System.out.println("Echo sul server in chiusura :" + stringRicevuta);
                break;
            }else{
                outVersoClient.writeBytes(stringRicevuta+" (ricevuta e ritrasmessa...)" + '\n');
                System.out.println("6 Echo sul server :" + stringRicevuta);
            }
        }
        outVersoClient.close();
        inDalClient.close();
        System.out.println("9 chiusura socket" + client);
    }
}