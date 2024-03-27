package com.galaxy13.chat;

import com.galaxy13.chat.exceptions.NoSuchUserException;
import com.galaxy13.chat.exceptions.WrongNameCommandException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private final int port;
    private final Map<String, ClientHandler> clientHandlerMap = new ConcurrentHashMap<>();
    private final Object syncObject = new Object();
    private final List<ClientHandler> testList = new ArrayList<>();
    private int userCounter;
    public Server(int port) {
        this.port = port;
    }

    private void clientHandleStart(Socket clientSocket) {
        try {
            clientSocket.setSoTimeout(180_000);
            ClientHandler handler = new ClientHandler(clientSocket, this);
            userCounter++;
            String name = handler.getName();
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

    public void disconnect(ClientHandler handler) {
        testList.remove(handler);
        System.out.println("Handler removed. Current size: " + testList.size());
    }

    public void sendMessage(String receivingUser, String message) throws NoSuchUserException {
        synchronized (syncObject) {
            if (clientHandlerMap.containsKey(receivingUser)) {
                clientHandlerMap.get(receivingUser).sendMessage(message);
            } else {
                throw new NoSuchUserException(receivingUser + "not exists or offline");
            }
        }
    }

    public void sendMessage(String message) {
        clientHandlerMap.values().forEach(handler -> handler.sendMessage(message));
    }

    public int getUserCounter() {
        return userCounter;
    }
}