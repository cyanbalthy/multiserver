package com.example;
import java.io.*;
import java.net.*;

public class Client{
    String nomeServer = "localhost";    //indirizzo del server
    int portaServer = 6789;              //porta x servizio
    Socket mioSocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    BufferedReader inDalServer;                  //stream di input
    DataOutputStream outVersoServer;                //stream di output

    public Socket connetti (){
        try{
            //input da tastiera
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            //creo un socket
            mioSocket = new Socket (nomeServer, portaServer);
            outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
        }catch(UnknownHostException e){
            System.err.println("host non riconosciuto"); //messaggio errore
        }catch(Exception e){
            System.out.println(e.getMessage()); //messaggio errore
            System.out.println("errore durante la connessione");
            System.exit(1);
        }
        
        return mioSocket;
    }

    public void comunica(){
        for(;;){
            try{
                System.out.println("4 ... inserisci la stringa da trasmettere al server");
                stringaUtente = tastiera.readLine();  
                //la spedisco al server
                System.out.println("5 ... invio la stringa al server e attendo");
                outVersoServer.writeBytes(stringaUtente + '\n');  
                //leggo la risposta dal server
                stringaRicevutaDalServer=inDalServer.readLine();
                System.out.println("8 ... risposta dal server"+'\n'+stringaRicevutaDalServer);
                if(stringaUtente.equals("FINE")){
                    System.out.println("8 CLIENT: termina elaborazione e chiude connesione");
                    mioSocket.close();
                    break;
                }
            }catch(Exception e){
                System.out.println(e.getMessage()); //messaggio errore
                System.out.println(" errore durante la comunicazione col server");
                System.exit(1);
            }
    }
}   

}