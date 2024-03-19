package com.galaxy13.ddo;

import java.io.Serializable;

public class Request implements Serializable {
    private final String requestType;
    private String operation = "";
    private String firstNumber = "";
    private String secondNumber = "";

    public Request(String requestType) {
        this.requestType = requestType;
    }

    public Request(String requestType, String operation, String firstNumber, String secondNumber) {
        this.requestType = requestType;
        this.operation = operation;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getOperation() {
        return operation;
    }

    public String getFirstNumber() {
        return firstNumber;
    }

    public String getSecondNumber() {
        return secondNumber;
    }
}