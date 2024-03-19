package com.galaxy13.CalculationClient;

import com.galaxy13.ddo.Request;
import com.galaxy13.ddo.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CalculationClient {
    private Socket clientSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private List<String> commands;

    public CalculationClient(String ip, int port) {
        boolean success = false;
        while (!success) {
            try {
                clientSocket = new Socket(ip, port);
                out("Connection established");
                outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                inputStream = new ObjectInputStream(clientSocket.getInputStream());
                commands = requestCommands();
                success = true;
            } catch (IOException e) {
                out("Connection error. Start server before attempting to connect");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException err) {
                    return;
                }
            } catch (ClassNotFoundException e) {
                out("Unable to get available commands");
                return;
            }
        }
    }

    private static void out(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        CalculationClient client = new CalculationClient("localhost", 27105);
        client.clientStart();
    }

    private List<String> requestCommands() throws IOException, ClassNotFoundException {
        outputStream.writeObject(new Request("commands"));
        Response response = (Response) inputStream.readObject();
        return Arrays.asList(response.getBody().replaceAll("[\\[\\] \" ]", "").split("\\s*,\\s*"));
    }

    private String[] readLine(Scanner scanner) throws InputMismatchException {
        out("Enter the operation:");
        String operation = scanner.nextLine();
        if (!commands.contains(operation)) {
            throw new InputMismatchException("No such operation exists. Enter operation from available:");
        }
        out("Enter the first number:");
        int firstNumber = scanner.nextInt();
        out("Enter the second number:");
        int secondNumber = scanner.nextInt();
        scanner.nextLine();
        return new String[]{operation, String.valueOf(firstNumber), String.valueOf(secondNumber)};
    }

    private void handleCalculation(Scanner scanner) {
        try {
            String[] calculationBody = readLine(scanner);
            Request request = new Request("calculation", calculationBody[0], calculationBody[1], calculationBody[2]);
            outputStream.writeObject(request);
            Response response = (Response) inputStream.readObject();
            if (response.getResponseCode().equals("200")) {
                out("Server calculation response: " + response.getBody());
            } else {
                String responseCode = response.getResponseCode();
                switch (responseCode) {
                    case "300":
                        out("Error " + responseCode + " :Sum integer overflow");
                    case "301":
                        out("Error " + responseCode + " :Subtract integer underflow");
                    case "302":
                        out("Error " + responseCode + " :Multiplication integer overflow/underflow");
                    case "303":
                        out("Error " + responseCode + " :Division by zero or integer overflow/underflow");
                    default:
                }
            }
        } catch (IOException e) {
            out("Connection error. " + e.getMessage());
        } catch (ClassNotFoundException e) {
            out("Parse response error");
        } catch (ClassCastException err) {
            out(err.getMessage());
        } catch (InputMismatchException e) {
            out("Not a number or number overflow");
        }
    }

    public void clientStart() {
        Scanner scanner = new Scanner(System.in);
        handleCalculation(scanner);
    }

    public boolean clientStop() {
        try {
            clientSocket.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
