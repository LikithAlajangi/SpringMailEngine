package com.likithalajangi.springBootSES.model;

public class notificationRequest {
    private String email;
    private String message;

    public String getSubject() {
        return subject;
    }

    private String subject;

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }
}
