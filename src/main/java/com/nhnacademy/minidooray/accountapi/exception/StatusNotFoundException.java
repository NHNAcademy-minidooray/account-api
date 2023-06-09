package com.nhnacademy.minidooray.accountapi.exception;

public class StatusNotFoundException extends RuntimeException {
    private static final String MESSAGE = "해당 상태코드를 찾을 수 없습니다.";

    public StatusNotFoundException(String code) {
        super(MESSAGE + "Code: " + code);
    }
}
