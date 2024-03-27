package com.galaxy13.chat;

import java.io.IOException;

public class ClientApplication {
    public static void main(String[] args) {
        Client client = new Client("localhost", 27105);
        try {
            client.start();
        } catch (IOException e) {
            System.out.println("Client start error");
        }
    }
}
