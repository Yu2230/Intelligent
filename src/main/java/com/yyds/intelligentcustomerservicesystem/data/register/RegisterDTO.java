package com.yyds.intelligentcustomerservicesystem.data.register;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class RegisterDTO {
    @Email(message = "email is incorrect")
    private String email;
    @Size(max = 6, min = 6, message = "code is incorrect")
    private String code;
    @Size(max = 12,min = 8, message = "password is incorrect")
    private String password;
    @NotNull
    private String name;
    @NotNull
    private int sex;
}