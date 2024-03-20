package com.galaxy13.CalculatorServer;

import com.galaxy13.configs.ServerConfig;
import com.galaxy13.ddo.Request;
import com.galaxy13.ddo.Response;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.function.BinaryOperator;


class ClientHandler implements AutoCloseable {
    Socket clientSocket;
    ObjectInputStream inStream;
    ObjectOutputStream outStream;

    public ClientHandler(Socket socket) throws IOException {
        clientSocket = socket;
        outStream = new ObjectOutputStream(clientSocket.getOutputStream());
        inStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    public String handleClient(List<String> commands) throws IOException {
        try {
            Request accepted = (Request) inStream.readObject();
            Response response;
            if (accepted.getRequestType().equals("commands")) {
                response = new Response("100", commands.toString());
            } else if (accepted.getRequestType().equals("calculation")) {
                response = calculateRequest(accepted);
            } else {
                response = new Response("401");
            }
            outStream.writeObject(response);
        } catch (ClassNotFoundException e) {
            return "Can't read from client stream";
        } catch (ClassCastException err) {
            return "Cast error";
        }
        return String.format("Client %s:%s request handling successful", clientSocket.getLocalAddress().getHostAddress(), clientSocket.getLocalPort());
    }

    private Response calculateRequest(Request request) {
        int firstNumber, secondNumber;
        try {
            firstNumber = Integer.parseInt(request.getFirstNumber());
            secondNumber = Integer.parseInt(request.getSecondNumber());
        } catch (NumberFormatException e) {
            return new Response("401"); // not a number error
        }
        return switch (request.getOperation()) {
            case "+" -> mathOperation(Math::addExact, "300", firstNumber, secondNumber);
            case "-" -> mathOperation(Math::subtractExact, "301", firstNumber, secondNumber);
            case "*" -> mathOperation(Math::multiplyExact, "302", firstNumber, secondNumber);
            case "/" -> mathOperation(Math::divideExact, "303", firstNumber, secondNumber);
            default -> new Response("400"); // wrong operation error
        };
    }

    private Response mathOperation(BinaryOperator<Integer> operation, String errorCode, int firstNumber, int secondNumber) {
        try {
            return new Response("200", operation.apply(firstNumber, secondNumber).toString());
        } catch (ArithmeticException e) {
            return new Response(errorCode);
        }
    }

    @Override
    public void close() throws IOException {
        outStream.flush();
        outStream.close();
        inStream.close();
        clientSocket.close();
    }

}

public class Server {
    private final ServerSocket serverSocket;
    private final ServerConfig config;
    private boolean stopCondition = false;

    public Server(ServerConfig config) throws IOException {
        this.config = config;
        serverSocket = new ServerSocket(this.config.getPort());
        out("Server started");
        if (config.getStoppable()) {
            out("Type 'stop' to stop server");
        }
    }

    public Server() throws IOException {
        this.config = new ServerConfig(27105, List.of("+", "-", "/", "*"), true);
        serverSocket = new ServerSocket(config.getPort());
        out("Server started with default config: port 27105");
        out("Type 'stop' to stop server");
        out("Waiting for client");
    }

    private static void out(Object object) {
        System.out.println(object);
    }

    private static ServerConfig getConfig() throws IOException {
        Yaml yaml = new Yaml(new Constructor(ServerConfig.class, new LoaderOptions()));
        try (InputStream inputStream = new FileInputStream("src/main/resources/serverConfig.yml")) {
            return yaml.load(inputStream);
        }
    }

    public static void main(String[] args) {
        ServerConfig config;
        Server server;
        try {
            config = getConfig();
            server = new Server(config);
        } catch (IOException e) {
            out("Config parse error.");
            try {
                server = new Server();
            } catch (IOException err) {
                out("Server start error");
                return;
            }
        }
        server.serverHandler();
    }

    private Thread createScannerThread() {
        return new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (!this.stopCondition) {
                String input = scanner.nextLine();
                if ("stop".equals(input)) {
                    this.stopCondition = true;
                    try {
                        serverSocket.close();
                        out("Server stopped");
                        scanner.close();
                        return;
                    } catch (IOException e) {
                        out("Error closing server socket: " + e.getMessage());
                    }
                } else {
                    out("Unknown command");
                }
            }
            scanner.close();
        });
    }

    public void serverHandler() {
        if (config.getStoppable()) {
            Thread scannerThread = createScannerThread();
            scannerThread.start();
        }

        while (!stopCondition) {
            out("Waiting for connection");
            try (Socket clientSocket = serverSocket.accept();
                 ClientHandler clientHandler = new ClientHandler(clientSocket)) {
                out("Connection established!");
                out(clientHandler.handleClient(config.getCommands()));
            } catch (IOException e) {
                out(e.getMessage());
            }
        }
    }
}
