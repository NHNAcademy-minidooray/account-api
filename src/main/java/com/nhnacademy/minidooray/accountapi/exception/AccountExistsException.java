package com.nhnacademy.minidooray.accountapi.exception;

public class AccountExistsException extends RuntimeException {
    private static final String MESSAGE = "이미 존재하는 ID입니다.";

    public AccountExistsException(String id) {
        super(MESSAGE + "ID: " + id);
    }
}
