package com.galaxy13.chat;

import com.galaxy13.chat.exceptions.NoSuchUserException;
import com.galaxy13.chat.exceptions.WrongNameCommandException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.regex.Pattern;

public class ClientHandler {
    final Socket clientSocket;
    private final Server parentServer;
    private final DataOutputStream outputStream;
    private final DataInputStream inputStream;
    private String userName;

    private boolean listenerStop = false;

    public ClientHandler(Socket clientSocket, Server parentServer) throws IOException, WrongNameCommandException {
        this.clientSocket = clientSocket;
        this.parentServer = parentServer;
        outputStream = new DataOutputStream(clientSocket.getOutputStream());
        inputStream = new DataInputStream(clientSocket.getInputStream());
        this.nameInit();
        Thread.startVirtualThread(() -> {
            try {
                this.listenerStart();
            } catch (IOException e) {
                System.out.println("Client start error");
            }
        });
    }

    private void listenerStart() throws IOException {
        try (clientSocket; outputStream; inputStream) {
            try {
                while (!listenerStop) {
                    String msg = inputStream.readUTF();
                    if (msg.startsWith("/w")) {
                        String[] context = msg.split(" ", 3);
                        String targetUser = context[1];
                        String message = context[2];
                        try {
                            parentServer.sendMessage(targetUser, message);
                        } catch (NoSuchUserException e) {
                            sendMessage("User with name " + userName + " does not exists or offline");
                        }
                    } else {
                        parentServer.sendMessage(msg);
                    }
                }
            } catch (IOException err) {
                System.out.println("Client disconnected.");
            }
        } finally {
            parentServer.disconnect(this);
        }
    }

    public void sendMessage(String message) {
        try {
            outputStream.writeUTF(message);
            outputStream.flush();
        } catch (IOException e) {
            System.out.println("Message send error");
        }
    }

    private String createDefaultName() {
        return "user:" + parentServer.getUserCounter();
    }

    private void nameInit() throws IOException, WrongNameCommandException {
        String userNameCommand = inputStream.readUTF();
        if (userNameCommand.startsWith("/set_username")) {
            String userName = Pattern.compile("(?<=/set_username )[a-zA-z0-9]*").matcher(userNameCommand).group();
            if (userName.isEmpty()) {
                this.userName = createDefaultName();
            } else {
                this.userName = userName;
            }
        } else {
            throw new WrongNameCommandException("Wrong command parsed");
        }
    }

    public String getName() {
        return userName;
    }

    public void stopHandlerListener() {
        listenerStop = true;
    }
}
