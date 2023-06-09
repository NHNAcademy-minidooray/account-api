package com.nhnacademy.minidooray.accountapi.exception;

public class AccountIdExistsException extends RuntimeException {
    private static final String MESSAGE = "사용중인 ID입니다.";

    public AccountIdExistsException(String id) {
        super(MESSAGE + "ID: " + id);
    }
}
