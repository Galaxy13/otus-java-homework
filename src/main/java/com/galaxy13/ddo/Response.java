package com.galaxy13.ddo;

import java.io.Serializable;

public class Response implements Serializable {
    private final String responseCode;
    private final String body;

    public Response(String responseCode, String body) {
        this.responseCode = responseCode;
        this.body = body;
    }

    public Response(String responseCode) {
        this.responseCode = responseCode;
        body = "";
    }

    public String getBody() {
        return body;
    }

    public String getResponseCode() {
        return responseCode;
    }
}