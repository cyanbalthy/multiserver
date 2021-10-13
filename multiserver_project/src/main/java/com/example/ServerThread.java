package com.example;
import java.net.*;
import java.io.*;

public class ServerThread extends Thread{
    MultiServer multiServer = null;
    Socket client = null;
    String stringRicevuta = null;
    String stringModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public ServerThread(Socket socket, MultiServer multiServer){
        this.client = socket;
        this.multiServer = multiServer;
    }
    
    public void run(){
        try{
            comunica();
        }catch(Exception e){
            System.out.println("il server è stato chiuso da un thread");
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
            }else if(stringRicevuta.equals("STOP")){
                outVersoClient.writeBytes(stringRicevuta+" {=>server sta per fermarsi...}"+ '\n');
                System.out.println("Echo sul server in pausa:" + stringRicevuta);
                multiServer.stop();
                break;
            }else{
                stringModificata = stringRicevuta.toUpperCase();
                outVersoClient.writeBytes(stringModificata+" (ricevuta e ritrasmessa...)" + '\n');
                System.out.println("6 Echo sul server :" + stringRicevuta);
            }
        }
        if(stringRicevuta.equals("STOP")){
            close();
        }
    }


    public void close(){
        try {
            outVersoClient.close();
            inDalClient.close();
            System.out.println("9 chiusura socket" + client);
            client.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}