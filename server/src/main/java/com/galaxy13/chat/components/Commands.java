package com.galaxy13.chat.components;

import com.galaxy13.chat.enums.SendContext;
import com.galaxy13.chat.exceptions.NoSuchUserException;

import java.util.Set;
import java.util.StringJoiner;

public class Commands {
    private final Server server;

    public Commands(Server server) {
        this.server = server;
    }

    public void privateMessage(ClientHandler handler, String receivingUser, String message, SendContext sendContext) throws NoSuchUserException {
        ClientHandler receivingHandler = server.getHandler(receivingUser);
        if (receivingHandler != null) {
            if (sendContext.equals(SendContext.PERSONAL) && !receivingUser.equals(handler.getName())) {
                receivingHandler.sendMessage(handler.getName(), message, sendContext);
                handler.sendMessage(handler.getName(), message, sendContext);
            } else {
                receivingHandler.sendMessage(handler.getName(), message, sendContext);
            }
        } else {
            throw new NoSuchUserException("User " + receivingUser + " not exists or offline");
        }
    }

    public void broadcast(ClientHandler handler, String message) {
        server.getClientHandlerMap().values().forEach(x -> x.sendMessage(handler.getName(), message, SendContext.ALL));
    }

    public void onlineUsers(ClientHandler handler) {
        Set<String> userSet = server.getOnlineUsers();
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add(String.format("Users online (%d):", userSet.size()));
        for (String username : userSet) {
            stringJoiner.add(username);
        }
        handler.sendMessage(server.getServerName(), stringJoiner.toString(), SendContext.INFO);
    }

    public void help(ClientHandler handler) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("Server commands:");
        stringJoiner.add("/h - print list of available commands");
        stringJoiner.add("/o - users online");
        stringJoiner.add("/w <username> msg - write personal message to online user. Example: /w retroider Hello there!");
        stringJoiner.add("If you want to write message into common channel, just typo message and press Enter");
        handler.sendMessage("Server", stringJoiner.toString(), SendContext.INFO);
    }
}
