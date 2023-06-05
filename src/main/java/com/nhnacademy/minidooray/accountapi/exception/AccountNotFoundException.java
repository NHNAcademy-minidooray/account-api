package com.nhnacademy.minidooray.accountapi.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String id) {
        super("Account ID Not Found: " + id);
    }
}
