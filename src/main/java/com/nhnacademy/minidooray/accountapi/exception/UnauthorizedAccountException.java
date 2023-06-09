package com.nhnacademy.minidooray.accountapi.exception;

// 로그인되지 않음
public class UnauthorizedAccountException extends RuntimeException {
    private static final String MESSAGE = "로그인되지 않았습니다.";
    public UnauthorizedAccountException(){
        super(MESSAGE);
    }
}
