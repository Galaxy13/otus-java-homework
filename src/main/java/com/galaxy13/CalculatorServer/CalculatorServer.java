package com.galaxy13.CalculatorServer;

import com.galaxy13.ddo.Request;
import com.galaxy13.ddo.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.function.BinaryOperator;


public class CalculatorServer {
    private final ServerSocket serverSocket;
    private final List<String> commands = List.of("+", "-", "*", "/");
    private Socket clientSocket;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private boolean stopCondiiton = false;

    public CalculatorServer(int socketPort) throws IOException {
        serverSocket = new ServerSocket(socketPort);
        out("Server started");
        out("Waiting for client");
    }

    private static void out(Object object) {
        System.out.println(object);
    }

    public static void main(String[] args) {
        try {
            CalculatorServer server = new CalculatorServer(27105);
            server.serverHandler();
        } catch (IOException e) {
            out("Server start error");
        }
    }

    public void serverHandler() {
        while (!stopCondiiton) {
            try {
                clientSocket = serverSocket.accept();
                out("Connection established!");
                outStream = new ObjectOutputStream(clientSocket.getOutputStream());
                inStream = new ObjectInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
                out("Connection to client is not established. " + e.getMessage());
            }
            try {
                out(handleClient());
            } catch (IOException e) {
                out("Client handling error." + e.getMessage());
            }
        }
    }

    private Response mathOperation(BinaryOperator<Integer> operation, String errorCode, int firstNumber, int secondNumber) {
        try {
            return new Response("200", operation.apply(firstNumber, secondNumber).toString());
        } catch (ArithmeticException e) {
            return new Response(errorCode);
        }
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

    private String handleClient() throws IOException {
        while (clientSocket.isConnected()) {
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
        }
        return "Client handling stopped";
    }
}
