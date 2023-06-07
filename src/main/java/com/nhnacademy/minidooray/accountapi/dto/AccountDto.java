package com.nhnacademy.minidooray.accountapi.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AccountDto {

    String accountId;

    String password;

    String email;

    String name;

    LocalDate createdAt;

    Integer statusCode;

    Integer authorityCode;

    public AccountDto(String accountId, String password, String email, String name,
                      LocalDate createdAt, Integer statusCode, Integer authorityCode) {
        this.accountId = accountId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
        this.statusCode = statusCode;
        this.authorityCode = authorityCode;
    }
}
