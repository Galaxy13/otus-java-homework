package com.galaxy13.CalculatorServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

enum AcceptTypes {
    ADDITION((byte) 1),
    MULTIPLICATION((byte) 2),
    DIVISION((byte) 3),
    SUBSTRACTING((byte) 4);

    AcceptTypes(byte i) {
    }
}

public class CalculatorServer {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static DataInputStream in;
    private static DataOutputStream out;

    public static void serverStart(int socketPort) throws IOException {
        serverSocket = new ServerSocket(socketPort);
        clientSocket = serverSocket.accept();
        in = new DataInputStream(clientSocket.getInputStream());
        out = new DataOutputStream(clientSocket.getOutputStream());

        byte[] buffer = new byte[8];
        while (true) {
            try {
                in.read(buffer, 0, 8);
            } catch (IOException e) {
                System.out.println("Can't read from client stream");
            }

        }
    }

    private int

    public static void main(String[] args) {
        Socke
    }

    public void serverStop() throws IOException {
        serverSocket.close();
        clientSocket.close();
        in.close();
        out.close();
    }
}
