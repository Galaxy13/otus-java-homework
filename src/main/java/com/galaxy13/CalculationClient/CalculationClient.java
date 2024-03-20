package com.galaxy13.CalculationClient;

import com.galaxy13.configs.ClientConfig;
import com.galaxy13.ddo.Request;
import com.galaxy13.ddo.Response;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CalculationClient {

    private final ClientConfig config;
    private List<String> commands;

    public CalculationClient(ClientConfig config) {
        this.config = config;
        boolean success = false;
        while (!success) {
            try {
                commands = requestCommands();
                out(commands.toString());
                success = true;
            } catch (IOException e) {
                out("Connection error. Start server before attempting to connect");
                out(reconnectSleep());
            } catch (ClassNotFoundException e) {
                out("Unable to get available commands");
                return;
            }
        }
    }

    private static ClientConfig createClientConfig() throws IOException {
        Yaml yaml = new Yaml(new Constructor(ClientConfig.class, new LoaderOptions()));
        try (FileInputStream inputStream = new FileInputStream("src/main/resources")) {
            return yaml.load(inputStream);
        }
    }

    public static void main(String[] args) {
        ClientConfig config;
        try {
            config = createClientConfig();
        } catch (IOException e) {
            config = new ClientConfig("localhost", 27105, 5000);
        }
        CalculationClient client = new CalculationClient(config);
        client.clientStart();
    }

    private static void out(String msg) {
        System.out.println(msg);
    }

    private Response sendRequest(Request request) throws IOException, ClassNotFoundException {
        try (Socket clientSocket = new Socket(config.getHost(), config.getPort());
             ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream())) {
            out("Connection established");
            outputStream.writeObject(request);
            outputStream.flush();
            return (Response) inputStream.readObject();
        }
    }

    private String reconnectSleep() {
        int millis = config.getTimeout();
        try {
            Thread.sleep(millis);
            return String.format("Client sleep for %d seconds", millis / 1000);
        } catch (InterruptedException err) {
            return "Interruption error";
        }
    }

    private List<String> requestCommands() throws IOException, ClassNotFoundException {
        Request commandRequest = new Request("commands");
        Response response = sendRequest(commandRequest);
        if (response.getResponseCode().equals("100")) {
            return Arrays.asList(response.getBody().replaceAll("[\\[\\] \"]", "").split("\\s*,\\s*"));
        } else throw new IOException("Receiving commands error");
    }

    private String[] readLines(Scanner scanner) throws InputMismatchException {
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

    private String responseErrorOut(String responseCode, String msg) {
        return String.format("Error %s: %s", responseCode, msg);
    }

    private String handleResponse(Response response) {
        if (response.getResponseCode().equals("200")) {
            return ("Server calculation response: " + response.getBody());
        } else {
            String responseCode = response.getResponseCode();
            return switch (responseCode) {
                case "300" -> responseErrorOut(responseCode, "Sum integer overflow");
                case "301" -> responseErrorOut(responseCode, "Subtract integer underflow");
                case "302" -> responseErrorOut(responseCode, "Multiplication integer overflow/underflow");
                case "303" -> responseErrorOut(responseCode, "Division by zero or integer overflow/underflow");
                case "400" -> responseErrorOut(responseCode, "Unsupported operation error");
                case "401" -> responseErrorOut(responseCode, "Unsupported type of request");
                default -> "";
            };
        }
    }

    private void handleCalculation(Scanner scanner) {
        try {
            String[] calculationBody = readLines(scanner);
            Request calculationRequest = new Request("calculation", calculationBody[0], calculationBody[1], calculationBody[2]);
            Response response = sendRequest(calculationRequest);
            out(handleResponse(response));
        } catch (IOException e) {
            out("Connection error. " + e.getMessage());
        } catch (ClassNotFoundException e) {
            out("Parse response error");
        } catch (ClassCastException err) {
            out(err.getMessage());
        } catch (InputMismatchException e) {
            out("Wrong operation, not integer input or integer overflow");
        }
    }

    private boolean continueCheck(Scanner scanner) {
        boolean correctInput = false;
        boolean continueResult = false;
        out("Continue?: Y/N ");
        while (!correctInput) {
            String continueInput = scanner.nextLine();
            if (continueInput.equalsIgnoreCase("yes") || continueInput.equalsIgnoreCase("y")) {
                continueResult = true;
                correctInput = true;
            } else if (continueInput.equalsIgnoreCase("no") || continueInput.equalsIgnoreCase("n")) {
                correctInput = true;
            } else {
                out("Wrong input. Try again");
            }
        }
        return continueResult;
    }

    public void clientStart() {
        Scanner scanner = new Scanner(System.in);
        boolean continueFlag = true;
        while (continueFlag) {
            handleCalculation(scanner);
            continueFlag = continueCheck(scanner);
        }
    }
}
