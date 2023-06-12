package com.nhnacademy.minidooray.accountapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AccountDto {

    String accountId;

    String password;

    String email;

    String name;

    LocalDate createdAt;

    Integer statusCode;

    Integer authorityCode;

    @Builder
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
