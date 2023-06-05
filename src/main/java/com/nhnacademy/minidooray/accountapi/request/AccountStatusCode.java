package com.nhnacademy.minidooray.accountapi.request;

import com.nhnacademy.minidooray.accountapi.entity.StatusCode;

import javax.validation.constraints.NotBlank;

public class AccountStatusCode {
    @NotBlank
    private StatusCode statusCode;
}
