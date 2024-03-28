package com.galaxy13.chat.services;

import com.galaxy13.chat.components.Client;

import java.io.IOException;

public class ClientApplication {
    public static void main(String[] args) {
        Client client = new Client("localhost", 27108);
        try {
            client.start();
        } catch (IOException e) {
            System.out.println("Client start error");
        }
    }
}
