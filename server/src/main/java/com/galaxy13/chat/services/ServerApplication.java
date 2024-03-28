package com.galaxy13.chat.services;

import com.galaxy13.chat.components.Server;

public class ServerApplication {
    public static void main(String[] args) {
        Server server = new Server(27108);
        server.start();
    }
}
