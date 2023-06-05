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
    String id;

    @Length(min = 3, max = 20)
    @NotBlank
    String password;

    @NotBlank
    @Email
    String email;

    String name;

}
