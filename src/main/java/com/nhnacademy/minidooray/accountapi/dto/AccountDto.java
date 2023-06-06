package com.nhnacademy.minidooray.accountapi.dto;

import java.time.LocalDate;

public interface AccountDto {
    String getAccountId();

    String getPassword();

    String getEmail();

    String getName();

    LocalDate getCreatedAt();

    StatusCodeDto getStatus();

    AuthorityCodeDto getAuthority();
}
