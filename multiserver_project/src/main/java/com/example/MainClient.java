package com.example;

/**
 * Hello world!
 *
 */
public class MainClient
{
    public static void main( String[] args )
    {
        MultiClient cliente = new MultiClient();
        cliente.connetti();
        cliente.comunica();
    }
}
