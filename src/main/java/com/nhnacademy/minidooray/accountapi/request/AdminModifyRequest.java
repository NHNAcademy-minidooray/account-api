package com.nhnacademy.minidooray.accountapi.request;

import com.nhnacademy.minidooray.accountapi.dto.StatusCodeDto;
import com.nhnacademy.minidooray.accountapi.entity.StatusCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AdminModifyRequest {

    @NotBlank
    String accountId;
}
