package org.fuji;

import org.fuji.clients.Client;
import org.fuji.server.FileServer;

public class Main {
    public static void main(String[] args) {
        FileServer fileServer= new FileServer();

        Client client1= new Client(fileServer, "write", "operation.txt", "Hello world".getBytes());
        Client client2= new Client(fileServer, "write", "operation2.txt", "Welcome on board".getBytes());

        Client client3= new Client(fileServer, "read", "operation.txt", null);
        Client client4= new Client(fileServer, "read", "operation2.txt",null);

        Thread t1= new Thread(client1);
        Thread t2= new Thread(client2);
        Thread t3= new Thread(client3);
        Thread t4= new Thread(client4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}