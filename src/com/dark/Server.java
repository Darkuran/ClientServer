package com.dark;

import java.io.*;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        int id = 0;
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server has been started ... ");

            while (true) {
                exec.execute(new Connection(serverSocket.accept(), ++id));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
