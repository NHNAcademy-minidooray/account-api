package com.nhnacademy.minidooray.accountapi.exception;

public class AccountExistsException extends RuntimeException {
    private static final String MESSAGE = "이미 존재하는 회원입니다.";

    public AccountExistsException() {
        super(MESSAGE);
    }
}
