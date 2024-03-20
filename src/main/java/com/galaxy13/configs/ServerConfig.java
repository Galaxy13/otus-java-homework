package com.galaxy13.configs;

import java.util.List;

public class ServerConfig {
    private int port;
    private List<String> commands;
    private boolean stoppable;

    public ServerConfig(int port, List<String> commands, boolean stoppable) {
        this.port = port;
        this.stoppable = stoppable;
        this.commands = commands;
    }

    @SuppressWarnings("unused")
    public ServerConfig() {
    }

    public List<String> getCommands() {
        return commands;
    }

    @SuppressWarnings("unused")
    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public int getPort() {
        return port;
    }

    @SuppressWarnings("unused")
    public void setPort(int serverPort) {
        this.port = serverPort;
    }

    public boolean getStoppable() {
        return stoppable;
    }

    @SuppressWarnings("unused")
    public void setStoppable(boolean stoppable) {
        this.stoppable = stoppable;
    }
}
