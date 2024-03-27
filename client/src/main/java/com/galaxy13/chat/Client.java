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

    private String setUserName(Scanner scanner) {
        System.out.println("Enter username: ");
        return scanner.nextLine();
    }

    private void threadInputStart(DataInputStream inputStream) {
        new Thread(() -> {
            try {
                while (true) {
                    String msg = inputStream.readUTF();
                    System.out.println(msg);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private void handleOutput(DataOutputStream outputStream, Scanner scanner) throws IOException {
        while (true) {
            String msg = scanner.nextLine();
            if (msg.equals("/exit")) {
                break;
            }
            outputStream.writeUTF(msg);
            outputStream.flush();
        }
    }

    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String userName = setUserName(scanner);
        try (Socket socket = new Socket(host, port);
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream inputStream = new DataInputStream(socket.getInputStream())) {
            outputStream.writeUTF("/set_username " + userName);
            threadInputStart(inputStream);
            handleOutput(outputStream, scanner);
        } catch (IOException e) {
            System.out.println("Socket bind error");
        }
    }
}