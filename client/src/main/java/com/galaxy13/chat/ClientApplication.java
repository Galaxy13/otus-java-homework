package com.galaxy13.chat;

public class ClientApplication {
    public static void main(String[] args) {
        Client client = new Client("localhost", 27105);
        client.start();
    }
}
