package com.nhnacademy.minidooray.accountapi.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AccountRegisterRequest {

    @NotBlank
    String accountId;

    @Length(min = 60, max = 60)
    @NotBlank
    String password;

    @NotBlank
    @Email
    String email;

    String name;

}
