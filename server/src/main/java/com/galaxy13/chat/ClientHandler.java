package com.galaxy13.chat;

import com.galaxy13.chat.exceptions.NoSuchUserException;
import com.galaxy13.chat.exceptions.WrongNameCommandException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler {
    final Socket clientSocket;
    private final Server parentServer;
    private final DataOutputStream outputStream;
    private final DataInputStream inputStream;
    private String userName;
    private boolean listenerStop = false;
    private final String timeFormat = "HH:mm:ss";

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
                            parentServer.handleMessage(userName, targetUser, message);
                        } catch (NoSuchUserException e) {
                            sendMessage("Server", "User with name " + userName + " does not exists or offline");
                        }
                    } else {
                        parentServer.handleMessage(userName, msg);
                    }
                }
            } catch (IOException err) {
                System.out.println("Client disconnected.");
            }
        } finally {
            parentServer.handleMessage(parentServer.getServerName(), String.format("User %s disconnected", userName));
            parentServer.disconnect(this);
        }
    }

    public void sendMessage(String sender, String message) {
        String timeStamp = new SimpleDateFormat(timeFormat).format(new Date());
        try {
            outputStream.writeUTF(String.format("[%s]<%s> %s", timeStamp, sender, message));
//            outputStream.flush();
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
            String[] commandSplitArray = userNameCommand.split(" ");
            if (commandSplitArray.length >= 2) {
                this.userName = commandSplitArray[1];
            } else {
                this.userName = createDefaultName();
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
