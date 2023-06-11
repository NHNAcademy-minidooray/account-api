package com.nhnacademy.minidooray.accountapi.exception;

public class UnauthorizedAdminException extends RuntimeException {
    public UnauthorizedAdminException(String id){
        super("관리자가 아닙니다. ID: " + id);
    }

    public UnauthorizedAdminException(Integer statusCode){
        super("권한코드 오류. Status Code: " + statusCode);
    }
}
