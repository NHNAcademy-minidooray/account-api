package com.nhnacademy.minidooray.accountapi.dto;

import java.time.LocalDate;

public interface AccountDto {
    String getId();

    String getPassword();

    String getEmail();

    String getName();

    LocalDate getCreatedAt();

    StatusCodeDto getStatus();

}
