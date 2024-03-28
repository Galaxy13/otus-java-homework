package com.galaxy13.chat.components;

import com.galaxy13.chat.enums.SendContext;
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

    private final Commands serverCommands;

    private final Server parentServer;

    private final DataOutputStream outputStream;

    private final DataInputStream inputStream;

    private String userName;

    private boolean listenerStop = false;

    @SuppressWarnings("FieldCanBeLocal")
    private final String messageTimeFormat = "HH:mm:ss";

    private final Logger logger;

    public ClientHandler(Socket clientSocket, Commands serverCommands, Server parentServer) throws IOException, WrongNameCommandException {
        this.clientSocket = clientSocket;
        this.serverCommands = serverCommands;
        this.parentServer = parentServer;
        this.logger = parentServer.getLogger();
        outputStream = new DataOutputStream(clientSocket.getOutputStream());
        inputStream = new DataInputStream(clientSocket.getInputStream());
        this.nameInit();
        Thread.startVirtualThread(() -> {
            try {
                this.listenerStart();
            } catch (IOException e) {
                logger.exceptionLog("Client start error");
            }
        });
    }

    private void listenerStart() throws IOException {
        try (clientSocket; outputStream; inputStream) {
            try {
                while (!listenerStop) {
                    String msg = inputStream.readUTF();
                    messageWorker(msg);
                }
            } catch (IOException err) {
                logger.exceptionLog(String.format("User %s disconnected", userName));
            }
        } finally {
            serverCommands.broadcast(this, String.format("User %s left server", userName));
            stopHandlerListener();
            parentServer.disconnect(this);
        }
    }

    private void messageWorker(String msg) {
        if (msg.startsWith("/w")) {
            String[] context = msg.split(" ", 3);
            String targetUser = context[1];
            String message = context[2];
            try {
                serverCommands.privateMessage(this, targetUser, message, SendContext.PERSONAL);
            } catch (NoSuchUserException e) {
                sendMessage(parentServer.getServerName(), "User with name " + targetUser + " does not exists or offline", SendContext.INFO);
            }
        } else if (msg.trim().startsWith("/o")) {
            serverCommands.onlineUsers(this);
        } else if (msg.trim().startsWith("/h")) {
            serverCommands.help(this);
        } else {
            serverCommands.broadcast(this, msg);
        }
    }

    public void sendMessage(String sender, String message, SendContext sendContext) {
        String timeStamp = new SimpleDateFormat(messageTimeFormat).format(new Date());
        try {
            outputStream.writeUTF(String.format("{%s}[%s]<%s> %s", sendContext.getValue(), timeStamp, sender, message));
            outputStream.flush();
        } catch (IOException e) {
            logger.exceptionLog("Output stream closed");
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
