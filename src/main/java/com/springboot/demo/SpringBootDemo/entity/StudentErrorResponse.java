package com.springboot.demo.SpringBootDemo.entity;

public class StudentErrorResponse {

    private int status;
    private String message;
    private long timestamp;

    public StudentErrorResponse() {

    }

    public StudentErrorResponse(String message, long timestamp, int status) {
        this.message = message;
        this.timestamp = timestamp;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
