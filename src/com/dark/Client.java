package com.dark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String host = "localhost";
        int port = 8080;

        try (Socket socket = new Socket(host, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            writer.println("coming");
            System.out.println(reader.readLine());

            while (true) {
                String line = scanner.nextLine();
                writer.println(line);
            }
        } catch (ConnectException ex) {
            System.out.println("Не удалось соединиться с сервером.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}
