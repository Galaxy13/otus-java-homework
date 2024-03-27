package com.galaxy13.chat;

import com.galaxy13.chat.exceptions.NoSuchUserException;
import com.galaxy13.chat.exceptions.WrongNameCommandException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private final int port;
    private final Map<String, ClientHandler> clientHandlerMap = new ConcurrentHashMap<>();
    private final Object syncObject = new Object();
    private final List<ClientHandler> testList = new ArrayList<>();
    private int userCounter;
    private final String serverName = "Server";
    public Server(int port) {
        this.port = port;
    }

    private void clientHandleStart(Socket clientSocket) {
        try {
            clientSocket.setSoTimeout(180_000);
            ClientHandler handler = new ClientHandler(clientSocket, this);
            userCounter++;
            String name = handler.getName();
            handler.sendMessage(serverName, String.format("Welcome to our server, %s!", name));
            usersOnline(handler);
            clientHandlerMap.put(name, handler);
            System.out.println("Size of testMap: " + clientHandlerMap.size());
        } catch (IOException e) {
            System.out.println("Handler creation aborted");
        } catch (WrongNameCommandException e) {
            System.out.println("Suspicious connection. Client handler initialize aborted");
        }
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread.startVirtualThread(() -> clientHandleStart(clientSocket));
            }
        } catch (IOException e) {
            System.out.println("Port bind error");
        }
    }

    private void usersOnline(ClientHandler handler) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("Users online:");
        for (String username : clientHandlerMap.keySet()) {
            stringJoiner.add(username);
        }
        handler.sendMessage("Server", stringJoiner.toString());
    }

    public void disconnect(ClientHandler handler) {
        testList.remove(handler);
        System.out.println("Handler removed. Current size: " + testList.size());
    }

    public void handleMessage(String sender, String receivingUser, String message) throws NoSuchUserException {
        synchronized (syncObject) {
            if (clientHandlerMap.containsKey(receivingUser)) {
                clientHandlerMap.get(receivingUser).sendMessage(sender, message);
            } else {
                throw new NoSuchUserException(receivingUser + "not exists or offline");
            }
        }
    }

    public void handleMessage(String sender, String message) {
        clientHandlerMap.values().forEach(handler -> handler.sendMessage(sender, message));
    }

    public int getUserCounter() {
        return userCounter;
    }

    public String getServerName() {
        return serverName;
    }
}