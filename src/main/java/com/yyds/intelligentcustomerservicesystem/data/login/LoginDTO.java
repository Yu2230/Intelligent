package com.yyds.intelligentcustomerservicesystem.data.login;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginDTO {
    private String email;
    private String password;
    private int role;
}
