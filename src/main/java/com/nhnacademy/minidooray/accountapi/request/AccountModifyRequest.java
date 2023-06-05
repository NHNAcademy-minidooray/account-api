package com.nhnacademy.minidooray.accountapi.request;

import com.nhnacademy.minidooray.accountapi.entity.StatusCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class AccountModifyRequest {

    @Length(min = 3, max = 20)
    @NotBlank
    String password;

    @NotBlank
    String email;

    @NotBlank
    String name;
}
