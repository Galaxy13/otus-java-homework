package com.galaxy13.configs;

public class ClientConfig {
    private String host;
    private int port;
    private int timeout;

    public ClientConfig(String host, int port, int timeout) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    @SuppressWarnings("unused")
    public ClientConfig() {
    }

    public int getPort() {
        return port;
    }

    @SuppressWarnings("unused")
    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    @SuppressWarnings("unused")
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getHost() {
        return host;
    }

    @SuppressWarnings("unused")
    public void setHost(String host) {
        this.host = host;
    }
}
