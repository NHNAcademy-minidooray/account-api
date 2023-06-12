package com.nhnacademy.minidooray.accountapi.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AccountModifyRequest {

    @Length(min = 6, max = 6)
    @NotBlank
    String password;

    @NotBlank
    String email;

    @NotBlank
    String name;

    @Builder
    public AccountModifyRequest(String password, String email, String name) {
        this.password = password;
        this.email = email;
        this.name = name;
    }
}
