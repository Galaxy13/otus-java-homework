package com.galaxy13.chat;

public class ServerApplication {
    public static void main(String[] args) {
        Server server = new Server(27106);
        server.start();
    }
}
