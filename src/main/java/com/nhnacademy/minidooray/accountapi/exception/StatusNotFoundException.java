package com.nhnacademy.minidooray.accountapi.exception;

public class StatusNotFoundException extends RuntimeException {
    public StatusNotFoundException(Integer seq) {
        super("Status Sequence is Not Found: " + seq);
    }
}
