package com.nhnacademy.minidooray.accountapi.exception;

// 관리자가 아님
public class UnauthorizedAdminException extends RuntimeException {
    private static final String MESSAGE = "관리자가 아닙니다.";
    public UnauthorizedAdminException(){
        super(MESSAGE);
    }
}
