package com.nhnacademy.minidooray.accountapi.exception;

// 로그인해도 없을 때
public class AccountNotFoundException extends RuntimeException {
    private static final String MESSAGE = "사용자를 찾을 수 없습니다.";

    public AccountNotFoundException(String id) {
        super(MESSAGE + " ID: " + id);
    }
}
