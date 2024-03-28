package com.galaxy13.chat.components;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private final String timeFormat;

    public Logger(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    private void out(String msg, String time, String reason) {
        System.out.printf("Log <%s>[%s]: %s%n", reason, time, msg);
    }

    private String getTimeStamp() {
        return new SimpleDateFormat(timeFormat).format(new Date());
    }

    public void exceptionLog(String msg) {
        String timeStamp = getTimeStamp();
        out(msg, timeStamp, "Exception");
    }

    public void infoLog(String msg) {
        String timeStamp = getTimeStamp();
        out(msg, timeStamp, "Info");
    }
}
