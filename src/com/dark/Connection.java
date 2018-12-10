package com.dark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Connection implements Runnable {
    private String line;
    private Socket socket;
    int id;

    Connection(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("ID:" + id + " присоединился");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            writer.println("Thanks for " + reader.readLine());

            while ( (line = reader.readLine() ) != null ) {
                System.out.println("ID:" + id + " сообщение: " + line);
            }
        } catch (SocketException ex ) {
            System.out.println("ID:" + id + " соединение разорвано");
        } catch (IOException ex) {
            ex.getStackTrace();
        } finally {
            System.out.println("ID:" + id + " отключился");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
