package com.galaxy13.chat.components;

import com.galaxy13.chat.enums.SendContext;
import com.galaxy13.chat.exceptions.WrongNameCommandException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("InfiniteLoopStatement")
public class Server {
    private final int port;
    private final Map<String, ClientHandler> clientHandlerMap = new ConcurrentHashMap<>();
    private final Commands serverCommands = new Commands(this);
    private int userCounter;
    private final String serverName = "Server";
    public Server(int port) {
        this.port = port;
    }

    private final Logger logger = new Logger("HH:mm:ss");

    private void clientHandleStart(Socket clientSocket) {
        try {
            clientSocket.setSoTimeout(180_000);
            ClientHandler handler = new ClientHandler(clientSocket, serverCommands, this);
            userCounter++;
            String name = handler.getName();
            handler.sendMessage(serverName, String.format("Welcome to our server, %s!", name), SendContext.INFO);
            logger.infoLog(String.format("User '%s' connected to server", name));
            clientHandlerMap.put(name, handler);
            serverCommands.help(handler);
            serverCommands.onlineUsers(handler);
        } catch (IOException e) {
            logger.exceptionLog("Handler creation aborted");
        } catch (WrongNameCommandException e) {
            logger.exceptionLog("Suspicious connection. Client handler initialize aborted");
        }
    }

    private void createInputThread(ServerSocket serverSocket) {
        logger.infoLog("To stop server type: /exit");
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String inputValue = scanner.nextLine();
                if (inputValue.equals("/exit")) {
                    try {
                        serverSocket.close();
                        break;
                    } catch (IOException e) {
                        logger.exceptionLog("Server already stopped");
                    }
                }
            }
        }).start();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            createInputThread(serverSocket);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread.startVirtualThread(() -> clientHandleStart(clientSocket));
            }
        } catch (IOException e) {
            logger.exceptionLog("Server stopped");
        }
    }

    public void disconnect(ClientHandler handler) {
        clientHandlerMap.remove(handler.getName());
    }

    public int getUserCounter() {
        return userCounter;
    }

    public String getServerName() {
        return serverName;
    }

    public ClientHandler getHandler(String user) {
        return clientHandlerMap.get(user);
    }

    public Map<String, ClientHandler> getClientHandlerMap() {
        return clientHandlerMap;
    }

    public Set<String> getOnlineUsers() {
        return clientHandlerMap.keySet();
    }

    public Logger getLogger() {
        return logger;
    }
}