package com.galaxy13.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        try (Socket socket = new Socket(host, port)) {
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String msg = scanner.nextLine();
                outputStream.writeUTF(msg);
                outputStream.flush();
            }
        } catch (IOException e) {
            System.out.println("Socket bind error");
        }
    }
}