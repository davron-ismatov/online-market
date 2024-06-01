package com.example.auth.exceptions;

public class AttachmentNotFoundException extends RuntimeException{
    public AttachmentNotFoundException(String message) {
        super(message);
    }
}
